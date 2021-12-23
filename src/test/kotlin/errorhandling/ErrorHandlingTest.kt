package errorhandling

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.SignalType
import reactor.test.StepVerifier


class ErrorHandlingTest {

    @Test
    fun happyFlowInFlux(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.just("4"))

        StepVerifier.create(flux.log())
            .expectNext("1","2","3","4")
            .verifyComplete()
    }

    @Test
    fun errorInFlux(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.error(RuntimeException(" Runtime Exception Occurred")))

        StepVerifier.create(flux.log())
            .expectNext("1","2","3")
            .expectError()
            .verify()
    }

    @Test
    fun doOnErrorSample(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.error(RuntimeException(" Runtime Exception Occurred")))
            .doOnError{
                println("Logging the error for upward stream!!")
            }

        StepVerifier.create(flux.log())
            .expectNext("1","2","3")
            .expectError()
            .verify()
    }

    @Test
    fun onErrorReturnSample(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.error(RuntimeException(" Runtime Exception Occurred")))
            .onErrorReturn("Some Error Occurred")
            .concatWith(Flux.just("4"))


        StepVerifier.create(flux.log())
            .expectNext("1","2","3")
            .expectNext("Some Error Occurred")
            .expectNext("4")
            .verifyComplete()
    }

    //same subscriber context
    //gives fallback publisher
    @Test
    fun onErrorResumeSample(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.error(RuntimeException(" Runtime Exception Occurred")))
            .onErrorResume{ it ->
                println("Yes Error has occured but we can not stop Exception is - $it")
                Flux.just("4")
            }

        StepVerifier.create(flux.log())
            .expectNext("1","2","3")
            .expectNext("4")
            .verifyComplete()
    }

    @Test
    fun onErrorMapSample(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.error(RuntimeException("Runtime Exception Occurred")))
            .onErrorMap{
                println("Mapping the error to Custom Exception from ${it}")
               when(it){
                   is RuntimeException -> SomeOtherException("A Custom Exception thrown")
                   else -> it
               }
            }

        StepVerifier.create(flux.log())
            .expectNext("1","2","3")
            .expectErrorMessage("A Custom Exception thrown")
            .verify()
    }

    @Test
    fun doFinallySample(){
        val flux = Flux.just("1","2","3")
            .concatWith(Flux.error(RuntimeException("Runtime Exception Occurred")))
            .doFinally{
                println("Executing block after error has occured")
                when (it) {
                    SignalType.ON_ERROR -> println("Error occured")
                    SignalType.CANCEL -> println("Cancel operation")
                    SignalType.ON_COMPLETE -> println("Completed operation")
                }
            }

        StepVerifier.create(flux.log())
            .expectNext("1","2","3")
            .expectError()
            .verify()
    }

}

class SomeOtherException(errorMessage: String) : Throwable(errorMessage) {

}

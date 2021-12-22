# Reactive Programming

- [x] Introduction To Reactive programming 
  - [x] Drawbacks of Imperative Programming
  - [x] How Reactive Programming fits  in the picture
  - [x] Concerns of Reactive Systems
- [ ] Reactive Stream Specification
- [x] Error Handling in Reactive Streams



## Introduction To Reactive Programming 

Drawbacks of Imperative Programming -:
1. One Thread for One Request model is not scalable 
2. Does not meet modern demands like high availability and low response time during high load
3. Synchronous thread execution gets blocked for eg during I/O operations which  causes increase in response time 
4. Overwhelming the server or client for eg increased number of requests can overwhelm server and large amount of response data can overwhelm client

How Reactive Programming fits  in the picture -:

1. It is a programming which involves building reactive systems working on asynchronous data streams 
2. Can be used to build asynchronous, non-blocking, event-driven applications
3. Require less no of threads(low memory utilisation) to handle large no of concurrent requests
4. Has concept of backpressure to control data flow from source to consumer to maintain stability in system
5. Uses functional style approach thus data streams can be transformed ie passed to other streams, filter, map, merge etc
6. Reactive Systems are expected to be responsive in timely manner(even in failure scenarios, during varying load) and are message driven


Concerns of Reactive Systems -:
1. Data Consistency
2. Separation of Concerns 
3. Choice of Messaging System and it's implementation
4. Failure Management

## Error Handling in Reactive Streams

- After error signal is thrown publisher stops emitting events

**doOnError()**

1. do some reaction when error occurs like logging,
2. propagates error to upper layer of the application
3. onError() signal thrown to the subscriber
4. Imagine as a peek into a sequence 


**onErrorReturn()**

1.  return a static/fallback value when error occurs
2.  error signal is not given to the subscriber when error occurs rather the static value is returned
3.  onComplete() signal thrown at the end of publishing

**onErrorResume**

1. used when alternative execution needs to be performed when error occurs
2.  a new publisher can start emitting events when error occurs ie from alternative source
3.  error signal is not given to the subscriber rather the new publisher events can be subscribed by subscriber

**onErrorMap**

1. when we want to translate the exception into another business or custom exception  based on the type of exception occurred
2. error signal is given here

**doFinally**
1. similar to finally of "try catch finally"
2.  used when file or resources needs to be closed when error occurs
3.  execute a piece of code when error occurs based on the type of Signal (ON_COMPLETE, ON_ERROR, CANCEL)

 
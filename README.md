# Reactive Programming

- [ ] Error Handling in Reactive Streams

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

 
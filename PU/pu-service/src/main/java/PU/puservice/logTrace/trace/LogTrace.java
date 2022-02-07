package PU.puservice.logTrace.trace;


import PU.puservice.logTrace.traceId.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}

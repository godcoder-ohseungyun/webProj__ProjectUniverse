package PU.puservice.aspect.trace;


import PU.puservice.aspect.traceId.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}

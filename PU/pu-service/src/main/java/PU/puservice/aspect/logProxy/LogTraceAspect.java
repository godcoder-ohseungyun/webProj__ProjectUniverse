package PU.puservice.aspect.logProxy;

import PU.puservice.aspect.trace.LogTrace;
import PU.puservice.aspect.traceId.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * traceId -> trace -> logProxy
 * 로그 추적기를 적용하는 어드바이저 입니다.
 * -자바 리플랙션,빈후처리기,동적프록시,프록시 팩토리
 */

@Component //컴포넌트 스캔: 빈 등록
@Aspect
@Slf4j
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    
    //AOP 자체가 AOP의 대상이 되어 빈등록 과정에서 Stackoverflow 발생하는 문제 해결: !execution(* PU.puservice.aspect..*.*(..)) 대상에서 제외
    @Around("execution(* PU.puservice..*.*(..)) && !execution(* PU.puservice.aspect..*.*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}

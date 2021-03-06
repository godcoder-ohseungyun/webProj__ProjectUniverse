package PU.puservice.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 모든 RestController에서 발생시키는 예외를 핸들링 합니다.
 * - 자바 제공 예외 클래스가 아닌 사용자 지정 예외를 핸들링합니다.
 * - ExResult라는 예외 처리 전용 반환 객체를 사용합니다.
 */
@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExResult> AccessDeniedEx(AccessDeniedException e, HttpServletRequest request) {
        log.error("[exceptionHandle] AccessDeniedException" + e);
        ExResult exResult = new ExResult(new Date(),"Access-Denied", e.getMessage(),request.getRequestURI());

        return new ResponseEntity<>(exResult,e.getStatusCode());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExResult> UserNotFoundEx(UserNotFoundException e, HttpServletRequest request) {
        log.error("[exceptionHandle] UserNotFoundException", e);
        ExResult exResult = new ExResult(new Date(),"User-Not-Found", e.getMessage(),request.getRequestURI());

        return new ResponseEntity<>(exResult,e.getStatusCode());
    }

}

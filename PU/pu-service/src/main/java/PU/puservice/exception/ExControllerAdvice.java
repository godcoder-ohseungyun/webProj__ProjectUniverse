package PU.puservice.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExResult> illegalExHandle(AccessDeniedException e, HttpServletRequest request) {
        log.error("[exceptionHandle] ex" + e.getStatusCode() + e.getMessage() );
        ExResult exResult = new ExResult(new Date(),"USER-EX", e.getMessage(),request.getRequestURI());

        return new ResponseEntity<>(exResult,e.getStatusCode());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExResult> illegalExHandle(UserNotFoundException e, HttpServletRequest request) {
        log.error("[exceptionHandle] ex", e);
        ExResult exResult = new ExResult(new Date(),"USER-EX", e.getMessage(),request.getRequestURI());

        return new ResponseEntity<>(exResult,e.getStatusCode());
    }

}

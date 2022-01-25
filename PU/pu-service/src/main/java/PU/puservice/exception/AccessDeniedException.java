package PU.puservice.exception;


import org.springframework.http.HttpStatus;

/**
 * 사용자의 접근 권한이 없는 경우 발생
 */
public class AccessDeniedException extends RuntimeException{

    private HttpStatus status;

    public AccessDeniedException(String msg,HttpStatus status){
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatusCode(){
        return status;
    }

}

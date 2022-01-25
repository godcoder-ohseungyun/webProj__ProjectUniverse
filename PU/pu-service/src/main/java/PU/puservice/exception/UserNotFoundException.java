package PU.puservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    private HttpStatus status;

    public UserNotFoundException(String msg,HttpStatus status){
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatusCode(){
        return status;
    }
}

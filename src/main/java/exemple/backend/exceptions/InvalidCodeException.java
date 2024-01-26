package exemple.backend.exceptions;

public class InvalidCodeException extends RuntimeException{
    public InvalidCodeException(String exception){
        super(exception);
    }
}

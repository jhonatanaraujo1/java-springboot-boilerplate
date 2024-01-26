package exemple.backend.exceptions;

public class EmailAlreadyException extends RuntimeException{
    public EmailAlreadyException(String exception){
        super(exception);
    }
}

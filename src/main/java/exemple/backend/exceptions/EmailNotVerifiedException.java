package exemple.backend.exceptions;

public class EmailNotVerifiedException extends RuntimeException{
    public EmailNotVerifiedException(String exception){
        super(exception);
    }
}

package exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String s){
        super(String.format("%s must contain at least 8 characters, one uppercase, no spaces",s));
    }
}

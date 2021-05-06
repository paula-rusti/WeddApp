package exceptions;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(){
        super("Invalid Phone Number.Must contains only 10 digits!");
    }
}

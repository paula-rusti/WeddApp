package exceptions;

public class CredentialsAreNullException extends RuntimeException {


    public CredentialsAreNullException() {
        super("All fields are mandatory!");
    }


}

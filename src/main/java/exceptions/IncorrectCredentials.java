package exceptions;

public class IncorrectCredentials extends Exception{

    public IncorrectCredentials()
    {
        super("Username or password are incorrect");
    }
}

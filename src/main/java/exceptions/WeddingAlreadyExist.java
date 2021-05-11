package exceptions;

public class WeddingAlreadyExist extends Exception{
    private final String username;

    public WeddingAlreadyExist(String username) {
        super(String.format("The organizer with the username %s already created a wedding!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

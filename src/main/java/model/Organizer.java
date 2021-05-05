package model;

public class Organizer extends User
{
    public Organizer(){ }

    public Organizer(String username, String name, String surname, String tel, String email, String password)
    {
        super(username, name, surname, tel, email, password, "organizer");
    }
}


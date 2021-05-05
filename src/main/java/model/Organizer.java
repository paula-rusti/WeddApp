package model;

public class Organizer extends User
{
    public Organizer(){ }

    public Organizer(String username, String name, String surname, String tel, String email, String password, String role)
    {
        super(username, name, surname, tel, email, password, role);
    }
}


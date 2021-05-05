package model;

public class Guest extends User
{
    public Guest(){}
    public Guest(String username, String name, String surname, String tel, String email, String password)
    {
        super(username, name, surname, tel, email, password, "guest");
    }

}
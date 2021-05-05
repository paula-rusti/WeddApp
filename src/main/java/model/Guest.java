package model;

public class Guest extends User
{
    public Guest(){}
    public Guest(String username, String name, String surname, String tel, String email, String password, String role)
    {
        super(username, name, surname, tel, email, password, role);
    }

}
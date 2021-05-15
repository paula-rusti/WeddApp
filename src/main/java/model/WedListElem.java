package model;

public class WedListElem {
    private String name;
    private String owner;       //this will be modified externally
    private int price;
    private String username; //the username to which the user belongs to



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public WedListElem(){}

    public WedListElem(String username, String name, int price)
    {
        this.username=username;
        this.name=name;
        this.owner=null;
        this.price=price;
    }
}

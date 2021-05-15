package model;

public class WedListElem {
    private String name;
    private String owner;
    private int price;

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

    public WedListElem(String name, String owner, int price)
    {
        this.name=name;
        this.owner=owner;
        this.price=price;
    }
}

package model;

public class Date
{
    public int day, month, year;

    public Date(){}

    public Date(int d, int m, int y)
    {
        day=d;
        month=m;
        year=y;
    }

    public String toString(){
        return day+"/"+month+"/"+year;
    }
}

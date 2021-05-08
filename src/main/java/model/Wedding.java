package model;

import java.util.ArrayList;

public class Wedding
{
    private Date eventDate;
    private String location;
    private int budget;
    private int maxInvites;
    private ArrayList<Task> taskList;
    private String username; //used to associate a wedding to a user

    Wedding(){}

    public Wedding(Date eventDate, String location, int budget, int maxInvites, String username)
    {
        this.eventDate = eventDate;
        this.location = location;
        this.budget = budget;
        this.maxInvites = maxInvites;
        this.username = username;
        taskList=new ArrayList<>();     //when a wedd in screated the tasklist is empty and has to be filled later
    }

    public void addTask(Task t)
    {
        taskList.add(t);
    }

    public int getMaxInvites() {
        return maxInvites;
    }

    public void setMaxInvites(int maxInvites) {
        this.maxInvites = maxInvites;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package model;


public class Task
{
    private String name;
    private String deadline;
    private String description;
    private String status="To Do";

    Task(){}

    public Task(String name, String deadline, String description) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
      //  this.stage="To Do";

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString() {

        return name+" "+description+" "+deadline;
    }
}

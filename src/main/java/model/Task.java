package model;

public class Task
{
    private Date deadline;
    private String description;

    Task(){}

    public Task(Date deadline, String description) {
        this.deadline = deadline;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}

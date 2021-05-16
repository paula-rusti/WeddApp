package model;

public class Task
{
    private String name;
    public  Date deadline;
    private String description;
    private String taskStatus;
    Task(){}

    public Task(String name, Date deadline, String description) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        //progress=new ComboBox();
       this.taskStatus="Not started";

    }
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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

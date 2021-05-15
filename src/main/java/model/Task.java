package model;

import javafx.scene.control.ChoiceBox;

public class Task
{
    private String name;
    private Date deadline;
    private String description;
    private ChoiceBox<String> stage;

    Task(){}

    public Task(String name, Date deadline, String description) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.stage.setValue("To Do");
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

    public ChoiceBox getStage() {
        return stage;
    }

    public void setStage(ChoiceBox stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

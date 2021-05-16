package Controllers;

import Main.App;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jsonUtils.FileWriter;
import model.Date;
import model.Task;
import model.Wedding;
import sceneUtils.SceneManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTaskController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField description;

    @FXML
    private ChoiceBox<Integer> day;
    @FXML
    private ChoiceBox<Integer> month;
    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        month.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
        year.setItems(FXCollections.observableArrayList(2021,2022,2023,2024,2025));

        backButton.setOnAction(e->backButtonClicked());
        saveButton.setOnAction(e->saveButtonClicked());

    }

    private void saveButtonClicked() {
        handleSaveTask();
        ManageTaskListController manageTaskListController = SceneManager.getInstance().getController(SceneManager.SceneType.TASK_LIST);
        manageTaskListController.setUser(App.getUserLoggedIn());
    }

    private void handleSaveTask() {
        String username= App.getUserLoggedIn().getUsername();
        System.out.println("add user"+username);
        Date temp = new Date(day.getValue(), month.getValue(), year.getValue());
        String date=""+day.getValue()+"."+month.getValue()+"."+year.getValue();

        //FileWriter.wedMap.get(username).addTask(new Task(name.getText(),date,description.getText()));
        FileWriter.addTask(username,new Task(name.getText(),date,description.getText()));

        System.out.println(FileWriter.weddings);
        FileWriter.persistWed();

    }

    private void backButtonClicked() {

        App.getI().changeSceneOnMainStage(SceneManager.SceneType.TASK_LIST);
    }
}

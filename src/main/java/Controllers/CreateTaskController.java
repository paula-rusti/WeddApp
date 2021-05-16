package Controllers;

import Main.App;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import jsonUtils.FileWriter;
import model.Date;
import model.Task;
import model.Wedding;
import sceneUtils.SceneManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateTaskController implements Initializable {

    @FXML
    private TextField taskname;
    @FXML
    private TextArea description;

    @FXML
    private DatePicker date;

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
        LocalDate l=date.getValue();
        //FileWriter.wedMap.get(username).addTask(new Task(name.getText(),date,description.getText()));
        if(l==null || taskname.getText().equals(""))
            return;
        FileWriter.addTask(username,new Task(taskname.getText(),new Date(l.getDayOfMonth(),l.getMonthValue(),l.getYear()),description.getText()));

        System.out.println(FileWriter.weddings);
        FileWriter.persistWed();

    }

    private void backButtonClicked() {

        App.getI().changeSceneOnMainStage(SceneManager.SceneType.TASK_LIST);
    }
}

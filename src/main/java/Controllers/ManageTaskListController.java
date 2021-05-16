package Controllers;

import Main.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jsonUtils.FileWriter;
import model.Task;
import model.User;
import sceneUtils.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


public class ManageTaskListController implements Initializable
    {
        private String username;
        @FXML
        public TableView<Task> table;
        @FXML
        private TableColumn<Task,String> task;
        @FXML
        private TableColumn<Task,ComboBox> progress;
        @FXML
        private TableColumn<Task,String> description;
        @FXML
        private TableColumn<Task, DatePicker> deadline;

        @FXML Label deletemessage;
        @FXML
        private Button addButton;
        @FXML
        private Button back;
        @FXML
        private Button deleteButton;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
            back.setOnAction(e -> backButtonClicked());
            addButton.setOnAction(e->addButtonClicked());
            deleteButton.setOnAction(e-> {
                deleteButtonClicked();
            });
            task.setCellValueFactory(new PropertyValueFactory<Task,String>("Name"));
            progress.setCellValueFactory(new PropertyValueFactory<Task,ComboBox>("TaskStatus"));
            deadline.setCellValueFactory(new PropertyValueFactory<Task,DatePicker>("Deadline"));
            description.setCellValueFactory(new PropertyValueFactory<Task,String>("Description"));
            table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        }



        private void deleteButtonClicked()  {
            ObservableList<Task> selectedRow, allTasks;
            allTasks=table.getItems();
            selectedRow = table.getSelectionModel().getSelectedItems();
            if(selectedRow.isEmpty())
                deletemessage.setText("Select a row to delete");
            allTasks.remove(selectedRow.get(0));
            ArrayList<Task> list = new ArrayList<>(allTasks);
            FileWriter.wedMap.get(username).setTaskList(list);
            FileWriter.deleteTask(list, username);
            FileWriter.persistWed();


        }

        public void setUser(User u){
            if(u==null)
                return;
            username=u.getUsername();
            ObservableList<Task> it = FXCollections.observableArrayList();
            List<Task> l= FileWriter.wedMap.get(username).getTaskList();
            if(l!=null) {
                for (Task t : l)
                    it.add(t);

            }

            System.out.println("username"+username);
            table.setItems(it);
        }


        private void addButtonClicked() {
           App.getI().changeSceneOnMainStage(SceneManager.SceneType.ADD_TASK);
        }

        private void backButtonClicked() {App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_WED); }

    }

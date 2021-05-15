package Controllers;

import Main.App;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;


    public class ManageTaskListController implements Initializable
    {
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


        }

        private void backButtonClicked() {App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_WED); }
    }

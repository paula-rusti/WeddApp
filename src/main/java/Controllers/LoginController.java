package Controllers;

import Main.App;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private JFXButton backButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private JFXComboBox role;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        backButton.setOnAction(e -> backButtonClicked());
        role.getItems().addAll("Guest", "Organizer");

    }

    public void backButtonClicked()
    {
        username.clear();
        password.clear();
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.REGISTER);
    }
}

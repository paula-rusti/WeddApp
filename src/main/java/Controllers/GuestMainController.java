package Controllers;

import Main.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GuestMainController implements Initializable
{
    @FXML
    private Button invitationsButton;
    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invitationsButton.setOnAction(e -> invitationsButtonClicked());
        logoutButton.setOnAction(e -> logoutButtonClicked());
    }

    private void invitationsButtonClicked() //guest clicked on see invites button
    {
        invitationsButton.setOnAction(e->{

            App.getI().changeSceneOnMainStage(SceneManager.SceneType.INVITES);

        });
    }

    private void logoutButtonClicked()
    {
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.LOGIN);
        App.setUserLoggedIn(null);

        LoginController log = SceneManager.getInstance().getController(SceneManager.SceneType.LOGIN);
        log.reset();
    }
}

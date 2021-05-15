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
    private Button eventsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button accButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invitationsButton.setOnAction(e -> invitationsButtonClicked());
        eventsButton.setOnAction(e -> eventsButtonClicked());
        logoutButton.setOnAction(e -> logoutButtonClicked());
        accButton.setOnAction(e -> accButtonClicked());
    }

    private void invitationsButtonClicked()
    {
        invitationsButton.setOnAction(e->{

            App.getI().changeSceneOnMainStage(SceneManager.SceneType.INVITES);
            InvitesListController invitesListController = SceneManager.getInstance().getController(SceneManager.SceneType.INVITES);
            if ( App.getUserLoggedIn().getRole().equals("organizer") )
            {
                invitesListController.initOrgList();
            }
            else
            {
                invitesListController.initGuestList();
            }
        });
    }

    private void eventsButtonClicked()
    {

    }

    private void logoutButtonClicked()
    {

    }

    private void accButtonClicked()
    {

    }
}

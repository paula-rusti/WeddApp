package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

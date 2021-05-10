package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class OrgNoWedController implements Initializable
{
    @FXML
    private Button manageButton;
    @FXML
    private Button backButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void setNameLabel(User u){
        if(u==null)
            return;
        nameLabel.setText(u.getName());
    }
}

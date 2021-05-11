package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

//TODO make this fxml look good

public class OrgNoWedController implements Initializable
{
    @FXML
    private Button manageButton;
    @FXML
    private Button backButton;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void setNameLabels(User u){
        if(u==null)
            return;
        name.setText(u.getName());
        surname.setText(u.getSurname());
    }
}

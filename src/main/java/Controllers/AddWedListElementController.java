package Controllers;

import Main.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import jsonUtils.FileWriter;
import model.WedListElem;
import sceneUtils.SceneManager;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddWedListElementController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    @FXML
    private Button createButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setOnAction(e->createButtonClicked());
    }

    public void createButtonClicked()
    {
        if(nameField.getText().equals("") || priceField.getText().equals(""))
            return;

        WedListElem temp = new WedListElem(App.getUserLoggedIn().getUsername(), nameField.getText(), new Integer(priceField.getText()));
        FileWriter.addWedList(temp);
        FileWriter.persistWedList();

        WedListController wedListController = SceneManager.getInstance().getController(SceneManager.SceneType.WED_LIST);
        wedListController.reloadList();
    }
}

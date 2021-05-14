package Controllers;

import Main.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;
import sceneUtils.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//TODO make this fxml look good

public class OrgNoWedController implements Initializable
{
    @FXML
    private Button manageButton;
    @FXML
    private Button logout;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setOnAction(e->createButtonClicked());
        logout.setOnAction(e->logoutButtonClicked());
    }

    public void setNameLabels(User u){
        if(u==null)
            return;
        name.setText(u.getName());
        surname.setText(u.getSurname());
    }

    private void createButtonClicked()
    {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Main/createWed.fxml"));

        Parent settingsRoot = null;
        try {
            settingsRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        stage.setTitle("Wedding Details");
        Scene scene = new Scene(settingsRoot, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButtonClicked()
    {
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.LOGIN);
        App.setUserLoggedIn(null);

        LoginController log = SceneManager.getInstance().getController(SceneManager.SceneType.LOGIN);
        log.reset();
    }
}

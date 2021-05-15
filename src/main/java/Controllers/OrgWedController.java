package Controllers;

import Main.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.User;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class OrgWedController implements Initializable {
    @FXML
    private Text name;
    @FXML
    private Text surname;
    @FXML
    private Button accButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button wedListButton;
    @FXML
    private Button taskListButton;
    @FXML
    private Button invitesListButton;
    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            detailsButton.setOnAction(e -> detailsButtonClicked());
            logoutButton.setOnAction(e->logoutButtonClicked());
            wedListButton.setOnAction(e -> {
                App.getI().changeSceneOnMainStage(SceneManager.SceneType.WED_LIST);
            });
    }

    public void setNameLabels(User u){
        if(u==null)
            return;
        name.setText(u.getName());
        surname.setText(u.getSurname());
    }

    private void detailsButtonClicked()
    {
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.WED_DETAILS);
    }

    private void logoutButtonClicked()
    {
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.LOGIN);
        App.setUserLoggedIn(null);

        LoginController log = SceneManager.getInstance().getController(SceneManager.SceneType.LOGIN);
        log.reset();
    }
}

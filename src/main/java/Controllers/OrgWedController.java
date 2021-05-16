package Controllers;

import Main.App;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Date;
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
    private JFXButton detailsButton;
    @FXML
    private JFXButton weddListButton;
    @FXML
    private JFXButton taskListButton;
    @FXML
    private JFXButton invitesListButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label wedDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            detailsButton.setOnAction(e -> detailsButtonClicked());
            logoutButton.setOnAction(e->logoutButtonClicked());
            weddListButton.setOnAction(e -> {
                App.getI().changeSceneOnMainStage(SceneManager.SceneType.WED_LIST);
            });
            invitesListButton.setOnAction(e->{

                App.getI().changeSceneOnMainStage(SceneManager.SceneType.INVITES);
                InvitesListController invitesListController = SceneManager.getInstance().getController(SceneManager.SceneType.INVITES);

            });
            taskListButton.setOnAction(e->taskListButtonClicked());
    }

    private void taskListButtonClicked() { App.getI().changeSceneOnMainStage(SceneManager.SceneType.TASK_LIST); }

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

    public void setWedDate(Date t)
    {
        wedDate.setText(t.toString());
    }
}

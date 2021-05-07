package Controllers;

import Main.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable
{
    @FXML
    private Button primaryButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        primaryButton.setOnAction(e -> primaryButtonClicked());
    }

    public void primaryButtonClicked()
    {
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.REGISTER);
    }
}

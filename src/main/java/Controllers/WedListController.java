package Controllers;

import Main.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import jsonUtils.FileWriter;
import listUtils.TestList;
import listUtils.WedList;
import model.WedListElem;
import model.Wedding;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class WedListController implements Initializable {

    private WedList list;

    @FXML
    private VBox vbox;
    @FXML
    private Button backButton;
    @FXML
    private Button addButton;

    public ObservableList<WedListElem> getList()
    {
        return list.getList();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        list = new WedList(FileWriter.wedList, vbox);
        backButton.setOnAction(e->{
            App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_WED);
        });
        addButton.setOnAction(e -> addButtonClicked());
    }

    public void reloadList(){
        list = new WedList(FileWriter.wedList, vbox);
    }

    private void addButtonClicked()
    {

    }
}

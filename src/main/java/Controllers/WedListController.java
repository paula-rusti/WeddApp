package Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import jsonUtils.FileWriter;
import listUtils.TestList;
import model.Wedding;

import java.net.URL;
import java.util.ResourceBundle;

public class WedListController implements Initializable {
    private TestList list;

    @FXML
    private VBox vbox;

    public ObservableList<Wedding> getList()
    {
        return list.getList();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = new TestList(FileWriter.weddings, vbox);
    }

    public void reloadList(){
        list = new TestList(FileWriter.weddings, vbox);
    }
}

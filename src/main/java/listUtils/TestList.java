package listUtils;

import Controllers.WedListController;
import Main.App;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import jsonUtils.FileWriter;
import model.Wedding;
import sceneUtils.SceneManager;

import java.io.File;
import java.util.List;

public class TestList extends ListBaseClass<Wedding>{
    public TestList(List<Wedding> l, Pane p)
    {
        super(l ,p );

    }

    @Override
    protected Pair<Parent, Wedding> createElement(Wedding x) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(400);
        Label label = new Label(x.getLocation());
        Button delete = new Button("delete");

        hBox.getChildren().add(label);
        hBox.getChildren().add(delete);

        delete.setOnAction(e -> {
            System.out.println("del pressed");
            WedListController cont = SceneManager.getInstance().getController(SceneManager.SceneType.WED_LIST);
            System.out.println(cont.getList());
            cont.getList().remove(x);
            System.out.println(cont.getList());
            FileWriter.weddings.remove(x);
            FileWriter.persistWed();
            cont.reloadList();
            App.getI().changeSceneOnMainStage(SceneManager.SceneType.WED_LIST);

        });
        return new Pair<>(hBox, x);
    }
}

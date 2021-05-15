package listUtils;

import Controllers.WedListController;
import Main.App;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import jsonUtils.FileWriter;
import model.WedListElem;
import model.Wedding;
import sceneUtils.SceneManager;

import java.util.List;

public class WedList extends ListBaseClass<WedListElem> {

    public WedList(List<WedListElem> l, Pane p)
    {
        super(l, p);
    }

    @Override
    protected Pair<Parent, WedListElem> createElement(WedListElem x) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(400);
        Label label = new Label(x.getName());
        Button delete = new Button("delete");

        hBox.getChildren().add(label);
        hBox.getChildren().add(delete);

        delete.setOnAction(e -> {
            System.out.println("del pressed");
            WedListController cont = SceneManager.getInstance().getController(SceneManager.SceneType.WED_LIST);

            System.out.println(cont.getList());
            cont.getList().remove(x);
            System.out.println(cont.getList());

            FileWriter.wedList.remove(x);
            FileWriter.persistWedList();
            cont.reloadList();
            App.getI().changeSceneOnMainStage(SceneManager.SceneType.WED_LIST);

        });
        return new Pair<>(hBox, x);
    }
}

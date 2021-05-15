package listUtils;

import Controllers.WedListController;
import Main.App;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import jsonUtils.FileWriter;
import model.Invitation;
import model.WedListElem;
import sceneUtils.SceneManager;

import java.util.List;

public class InvitesList extends ListBaseClass<Invitation>
{
    public InvitesList(List<Invitation> l, Pane p)
    {
        super(l, p);
    }

    @Override
    protected Pair<Parent, Invitation> createElement(Invitation x) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(400);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        Label source = new Label("Name: "+ x.getSource());
        Label dest = new Label("Name: "+ x.getDest());
        Button manage = new Button("delete");

        hBox.getChildren().add(source);
        hBox.getChildren().addAll(dest);
        hBox.getChildren().add(manage);

        manage.setOnAction(e -> manageButtonPressed());
        return new Pair<>(hBox, x);
    }

    private void manageButtonPressed()
    {
        System.out.println("manage invite pressed!!!");
    }
}

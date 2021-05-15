package listUtils;

import Controllers.InvitesListController;
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

public class OrgInvitesList extends ListBaseClass<Invitation>
{
    public OrgInvitesList(List<Invitation> l, Pane p)
    {
        super(l, p);
    }

    @Override
    protected Pair<Parent, Invitation> createElement(Invitation x) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(400);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Label source = new Label("source: "+ x.getSource());
        Label dest = new Label("dest: "+ x.getDest());
        Button delete = new Button("delete");

        hBox.getChildren().add(source);
        hBox.getChildren().addAll(dest);
        hBox.getChildren().add(delete);


        delete.setOnAction(e -> {
            System.out.println("manage invite pressed!!!");
            InvitesListController invitesListController = SceneManager.getInstance().getController(SceneManager.SceneType.INVITES);
            invitesListController.getListOrg().remove(x);
            FileWriter.invites.remove(x);
            FileWriter.persistInvites();
            invitesListController.reloadListOrg();

        });
        return new Pair<>(hBox, x);
    }

}

package listUtils;

import Controllers.InvitesListController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import jsonUtils.FileWriter;
import model.Invitation;
import sceneUtils.SceneManager;

import java.util.List;

public class GuestInvitesList extends ListBaseClass<Invitation> {
    public GuestInvitesList(List<Invitation> l, Pane p)
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
        ChoiceBox<String> answer = new ChoiceBox();
        answer.setValue("Your answer");
        answer.getItems().addAll("accept", "reject");
        Button update = new Button("update status");

        hBox.getChildren().add(source);
        hBox.getChildren().addAll(dest);
        hBox.getChildren().add(answer);
        hBox.getChildren().add(update);

        update.setOnAction(e->{
            System.out.println("update status pressed!!!");
            InvitesListController invitesListController = SceneManager.getInstance().getController(SceneManager.SceneType.INVITES);

            for ( var temp : FileWriter.invites)
            {
                if(temp.getSource().equals(x.getSource()) && temp.getDest().equals(x.getDest()))
                {
                    if(answer.getValue().equals("accept"))
                    {
                        temp.setStatus(Invitation.STATUS.ACCEPTED);
                    }
                    else
                        temp.setStatus(Invitation.STATUS.REJECTEd);
                }
            }

            FileWriter.persistInvites();
            invitesListController.reloadListGuest();

        });
        
        return new Pair<>(hBox, x);
    }
}

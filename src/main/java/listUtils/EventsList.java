package listUtils;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import model.Wedding;

import java.util.List;

public class EventsList extends ListBaseClass<Wedding> {
    public EventsList(List<Wedding> l, Pane p)
    {
        super(l, p);
    }

    @Override
    protected Pair<Parent, Wedding> createElement(Wedding x) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(400);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Label eventName = new Label("Event Name: "+ x.getUsername()+"'s "+"wedding");
        Label eventDate = new Label("Event Date: "+x.getEventDate());

        Button seeDetails = new Button("see details");

        hBox.getChildren().add(eventName);
        hBox.getChildren().addAll(eventDate);
        hBox.getChildren().add(seeDetails);

        seeDetails.setOnAction(e->{
            //pop up
            System.out.println("see details pressed");
        });

        return new Pair<>(hBox, x);
    }
}

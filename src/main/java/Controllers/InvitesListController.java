package Controllers;

import Main.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import jsonUtils.FileWriter;
import listUtils.InvitesList;
import listUtils.WedList;
import model.Invitation;
import model.WedListElem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvitesListController implements Initializable {
    @FXML
    private VBox vbox;
    @FXML
    private Button inviteButton;
    @FXML
    private Label message;
    @FXML
    private TextField userField;

    private InvitesList list;

    public ObservableList<Invitation> getList()
    {
        return list.getList();
    }

    public void reloadList(){
        list = new InvitesList(FileWriter.invites, vbox);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = new InvitesList(FileWriter.invites, vbox);
        inviteButton.setOnAction(e->inviteButtonClicked());
    }

    private void inviteButtonClicked()
    {
        String username = userField.getText();
        String organizer = App.getUserLoggedIn().getUsername();

        //search if this user is a registered guest
        if(FileWriter.userMap.get(username)!=null)
        {
            if(!FileWriter.userMap.get(username).getRole().equals("guest"))
            {
                System.out.println(username + " is " + FileWriter.userMap.get(username).getRole());
            }
            else    //the user is a registered guest
            {
                System.out.println("guest!!!");
                for ( var temp:FileWriter.invites)
                {
                    if(temp.getSource().equals(organizer))
                    {
                        System.out.println("The user "+username+" already has an invitation from "+organizer);
                        return;
                    }
                }
                System.out.println("we can send the invite");
                FileWriter.addInvite(new Invitation(organizer, username));  //update list
                FileWriter.persistInvites(); //write the invites in file
            }
        }
        reloadList();

    }
}

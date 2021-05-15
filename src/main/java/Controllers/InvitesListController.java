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
import listUtils.GuestInvitesList;
import listUtils.OrgInvitesList;
import model.Invitation;
import sceneUtils.SceneManager;

import java.net.URL;
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
    @FXML
    private Button backButton;

    private OrgInvitesList listOrg;
    private GuestInvitesList listGuest;

    public ObservableList<Invitation> getListOrg()
    {
        return listOrg.getList();
    }

    public ObservableList<Invitation> getListGuest(){ return listGuest.getList();}

    public void reloadListOrg(){
        listOrg = new OrgInvitesList(FileWriter.invites, vbox);
    }
    public void reloadListGuest(){
        listGuest = new GuestInvitesList(FileWriter.invites, vbox);
    }

    public void initOrgList()
    {
        listOrg = new OrgInvitesList(FileWriter.invites, vbox);
    }

    public void initGuestList()
    {
        listGuest = new GuestInvitesList(FileWriter.invites, vbox);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //list = new OrgInvitesList(FileWriter.invites, vbox);
        inviteButton.setOnAction(e->inviteButtonClicked());
        backButton.setOnAction(e->{
            if(App.getUserLoggedIn().getRole().equals("organizer"))
                App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_WED);
            else
                App.getI().changeSceneOnMainStage(SceneManager.SceneType.GUEST_MAIN);
        });
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
        reloadListOrg();
    }

    public void makeInvisible() //reuse this scene for the guest invites
    {
        inviteButton.setVisible(false);
        message.setVisible(false);
        userField.setVisible(false);
    }
}

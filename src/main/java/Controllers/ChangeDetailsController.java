package Controllers;

import Main.App;
import exceptions.WeddingAlreadyExist;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jsonUtils.FileWriter;
import model.Date;
import model.User;
import model.Wedding;
import sceneUtils.SceneManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ChangeDetailsController implements Initializable {

    @FXML
    private TextField budget;
    @FXML
    private TextField location;
    @FXML
    private TextField maxInvites;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button save;
    @FXML
    private Button back;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save.setOnAction(e->saveButtonClicked());
        back.setOnAction(e->backButtonClicked());
    }

    private void saveButtonClicked()  //when clicked the wedding must get written in the file
    {
        try{
            handleChangeWedd();
        }catch (WeddingAlreadyExist e)
        {
            System.out.println(e.getMessage());
        }

    }

    private void backButtonClicked()
    {
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_WED);
    }

    public void loadDetails(User u)
    {
        System.out.println("!!!!!!!!!!");

        Wedding temp = FileWriter.wedMap.get(u.getUsername());

        budget.setText(temp.getBudget()+"");
        location.setText(temp.getLocation()+"");
        maxInvites.setText(temp.getMaxInvites()+"");

    }

    private void handleChangeWedd() throws WeddingAlreadyExist
    {
        //first we need to get the username of the user that is logged in at the moment
        String username = App.getUserLoggedIn().getUsername();

        //delete the old wedding from the weddings list
        Iterator it = FileWriter.weddings.iterator();
        while (it.hasNext())
        {
            Wedding temp = (Wedding) it.next();
            if(temp.getUsername().equals(username))
                it.remove();
        }

        //create the new wedding
        LocalDate localDate = datePicker.getValue();
        Date temp = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        
        int bud=Integer.parseInt(budget.getText());
        int mI=Integer.parseInt(maxInvites.getText());

        FileWriter.addWedd(new Wedding(temp, location.getText(), bud, mI, username));   //add the wedding to the list
        FileWriter.persistWed();    //write it in the file
    }

}

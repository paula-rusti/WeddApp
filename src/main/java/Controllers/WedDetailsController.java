package Controllers;

import Main.App;
import exceptions.WeddingAlreadyExist;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import jsonUtils.FileWriter;
import model.Date;
import model.Wedding;

import java.net.URL;
import java.util.ResourceBundle;

public class WedDetailsController implements Initializable {

    @FXML
    private TextField budget;
    @FXML
    private TextField location;
    @FXML
    private TextField maxInvites;

    @FXML
    private ChoiceBox<Integer> day;
    @FXML
    private ChoiceBox<Integer> month;
    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private Button create;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        month.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
        year.setItems(FXCollections.observableArrayList(2021,2022,2023,2024,2025));

        day.setTooltip(new Tooltip("select day"));

        create.setOnAction(e->createButtonClicked());
    }

    private void createButtonClicked()  //when clicked the wedding must get written in the file
    {
        try{
            handleCreateWedd();
        }catch (WeddingAlreadyExist e)
        {
            System.out.println(e.getMessage());
        }

    }

    private void handleCreateWedd() throws WeddingAlreadyExist
    {
        //first we need to get the username of the user that is logged in at the moment
        String username = App.getUserLoggedIn().getUsername();

        //next we look to see if it has created a wedding or not
        if(FileWriter.wedMap.get(username)!=null)
            throw new WeddingAlreadyExist(username);    //if it did, we stop execution

        //else we create the object that will be stored in the file
        Date temp = new Date(day.getValue(), month.getValue(), year.getValue());
        int bud=Integer.parseInt(budget.getText());
        int mI=Integer.parseInt(maxInvites.getText());

        FileWriter.addWedd(new Wedding(temp, location.getText(), bud, mI, username));   //add the wedding to the list
        FileWriter.persistWed();    //write it in the file
    }
}

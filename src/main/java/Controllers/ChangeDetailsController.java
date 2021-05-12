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

import java.net.URL;
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
    private ChoiceBox<Integer> day;
    @FXML
    private ChoiceBox<Integer> month;
    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private Button save;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        month.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
        year.setItems(FXCollections.observableArrayList(2021,2022,2023,2024,2025));

        day.setTooltip(new Tooltip("select day"));

        save.setOnAction(e->saveButtonClicked());
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

    public void loadDetails(User u)
    {
        System.out.println("!!!!!!!!!!");

        Wedding temp = FileWriter.wedMap.get(u.getUsername());

        budget.setText(temp.getBudget()+"");
        location.setText(temp.getLocation()+"");
        maxInvites.setText(temp.getMaxInvites()+"");
        day.setValue(temp.getEventDate().day);
        month.setValue(temp.getEventDate().month);
        year.setValue(temp.getEventDate().year);
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
        Date temp = new Date(day.getValue(), month.getValue(), year.getValue());
        int bud=Integer.parseInt(budget.getText());
        int mI=Integer.parseInt(maxInvites.getText());

        FileWriter.addWedd(new Wedding(temp, location.getText(), bud, mI, username));   //add the wedding to the list
        FileWriter.persistWed();    //write it in the file
    }

}

package Controllers;

import Main.App;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import exceptions.CredentialsAreNullException;
import exceptions.InvalidPhoneNumberException;
import exceptions.InvalidCredentialsException;
import exceptions.UsernameAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import model.Guest;
import model.Organizer;
import sceneUtils.SceneManager;
import jsonUtils.FileWriter;



public class RegisterController implements Initializable
{
    public Label message;
    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private JFXComboBox role;
    @FXML
    private JFXButton regButton;
    @FXML
    private JFXButton backButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        role.getItems().addAll("Guest", "Organizer");
        backButton.setOnAction(e -> {
            App.getI().changeSceneOnMainStage(SceneManager.SceneType.PRIMARY);
            username.clear();
            name.clear();
            surname.clear();
            tel.clear();
            email.clear();
            password.clear();
            role.getItems().clear();
            message.setText("");
        });
        regButton.setOnAction(e-> {
            try {
                handleRegistration();
                message.setText("Account created successfully");
            }catch (CredentialsAreNullException ex1){
                message.setText(ex1.getMessage());
            } catch (UsernameAlreadyExistsException exception) {
                message.setText(exception.getMessage());
            }catch (InvalidPhoneNumberException e2) {
                message.setText(e2.getMessage());
            }
            catch (InvalidCredentialsException e3) {
                message.setText(e3.getMessage());
            }
        });

    }
    public void handleRegistration() throws UsernameAlreadyExistsException,CredentialsAreNullException,InvalidPhoneNumberException, InvalidCredentialsException {
        if(role.getValue()==null)
            throw new CredentialsAreNullException();
        String s= (String)role.getValue();
            if (s.equals("Guest"))
                FileWriter.addUser(new Guest(username.getText(), name.getText(), surname.getText(), tel.getText(), email.getText(), password.getText()));
            else
                FileWriter.addUser(new Organizer(username.getText(), name.getText(), surname.getText(), tel.getText(), email.getText(), password.getText()));

    }
}


package Controllers;

import Main.App;
import Validation.RegisterValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import exceptions.CredentialsAreNullException;
import exceptions.IncorrectCredentials;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import jsonUtils.FileWriter;
import model.User;
import sceneUtils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private JFXComboBox role;
    @FXML
    private Label message;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        registerButton.setOnAction(e -> registerButtonClicked());
        role.getItems().addAll("Guest", "Organizer");

        loginButton.setOnAction(e -> loginButtonClicked());

    }

    public void reset()
    {
        username.clear();
        password.clear();
        message.setText("");
    }

    public void registerButtonClicked()
    {
        reset();
        App.getI().changeSceneOnMainStage(SceneManager.SceneType.REGISTER);
    }

    public void loginButtonClicked()
    {
        try {
            handleLogin();
            message.setText("Login Successful!");
            User temp = FileWriter.userMap.get(username.getText());

            App.setUserLoggedIn(temp);      //sets the user logged in to be the one that logged in now

            //redirect behavior
            String s = (String)role.getValue();
            if(s.equals("Guest"))
                App.getI().changeSceneOnMainStage(SceneManager.SceneType.GUEST_MAIN);
            else
            {
                //we need to see if the organizer has created a wedding or not
                if(FileWriter.wedMap.get(username.getText())==null)
                {
                    System.out.println("no wed for username!!!!"+(username.getText()));
                    App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_NO_WED);
                }
                else
                {
                    System.out.println("wed found for username");
                    App.getI().changeSceneOnMainStage(SceneManager.SceneType.ORG_WED);
                }
            }

        }catch (CredentialsAreNullException | IncorrectCredentials e)
        {
            message.setText(e.getMessage());
        }
    }

    public void handleLogin() throws CredentialsAreNullException, IncorrectCredentials
    {
        if (role.getValue()==null || password.getText()==null || username.getText()==null)
            throw new CredentialsAreNullException();
        else
        {
            String s = (String)role.getValue();
            if(s.equals("Guest"))
            {
                //try validating in the guests list
                //System.out.println("here");
                for(var guest: FileWriter.guests)
                {
                    //System.out.println(guest.getUsername());
                    if(guest.getUsername().equals(username.getText())) //if a user with that username is present in the file
                    {
                        System.out.println("guest username found!!!!!!!!");
                        if(guest.getPassword().equals(RegisterValidation.encodePassword(guest.getUsername(), password.getText())))
                        {
                            //System.out.println("Login success");
                        }
                        else
                        {
                            //System.out.println("but no correct pass");
                            throw new IncorrectCredentials();
                        }
                        return;
                    }
                }
                //System.out.println("username not in file");
                throw new IncorrectCredentials();
            }
            if(s.equals("Organizer"))
            {
                //try validating in the guests list
                //System.out.println("here");
                for(var org: FileWriter.organizers)
                {
                    //System.out.println(org.getUsername());
                    if(org.getUsername().equals(username.getText())) //if a user with that username is present in the file
                    {
                        System.out.println("org username found");
                        if(org.getPassword().equals(RegisterValidation.encodePassword(org.getUsername(), password.getText())))
                        {
                            //System.out.println("Login success");
                        }
                        else
                        {
                            //System.out.println("but no correct pass");
                            throw new IncorrectCredentials();
                        }
                        return;
                    }
                }
                //System.out.println("username not in file");
                throw new IncorrectCredentials();
            }
            //throw new IncorrectCredentials();
        }
    }
}

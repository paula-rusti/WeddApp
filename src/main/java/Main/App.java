package Main;

import Controllers.OrgNoWedController;
import javafx.application.Application;
import javafx.stage.Stage;
import jsonUtils.FileWriter;
import model.*;
import sceneUtils.SceneManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class App extends Application {

    private Stage stage;
    private static App instance;
    public static void main(String[] args) { launch(args); }

    private static User userLoggedIn=null;

    public static User getUserLoggedIn() {
        return userLoggedIn;
    }

    public static void setUserLoggedIn(User userLoggedIn) {

        App.userLoggedIn = userLoggedIn;
        OrgNoWedController noWedController = SceneManager.getInstance().getController(SceneManager.SceneType.ORG_NO_WED);

        noWedController.setNameLabel(userLoggedIn);     //!!!!!!!!!!!!!!!!!!!
    }


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        instance = this;

        //load users from file in the arrays to have them globally available;
        FileWriter.loadDataFromFile();


        //from now use FileWriter.list instead

        //iterate over hash map
//        Iterator it = FileWriter.userMap.entrySet().iterator();
//        while (it.hasNext())
//        {
//            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey() + "=" + pair.getValue());
//            it.remove();
//        }

        // Initialize all scenes
        SceneManager.getInstance();

        //Choose first appearing scene
        stage.setScene(SceneManager.getInstance().getScene(SceneManager.SceneType.LOGIN));

        stage.setResizable(true);
        stage.setTitle("WeddApp");

        stage.show();
    }

    public static App getI() {
        return instance;
    }

    public void changeSceneOnMainStage(SceneManager.SceneType sceneType)
    {
        stage.setScene(SceneManager.getInstance().getScene(sceneType));
    }

}
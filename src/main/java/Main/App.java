package Main;

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

//    public static List<Organizer> organizers;
//    public static List<Guest> guests;
//    public static List<Wedding> weddings;

    public static Map<String, User> userMap=new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        instance = this;

        //load users from file in the arrays to have them globally available;
        FileWriter.loadDataFromFile();
//        organizers=FileWriter.getOrganizers();
//        guests=FileWriter.getGuests();
//        weddings=FileWriter.getWeddings();

        userMap=FileWriter.getUserMap();
        //iterate over hash map
        Iterator it = userMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + "=" + pair.getValue());
            it.remove();
        }

        // Initialize all scenes
        SceneManager.getInstance();

        //Choose first appearing scene
        stage.setScene(SceneManager.getInstance().getScene(SceneManager.SceneType.PRIMARY));

        stage.setResizable(true);
        stage.setTitle("WeddApp");

        stage.show();

        FileWriter.loadUsersFromFile();
        FileWriter.addUser(new Organizer("a", "a", "a", "a", "a", "a"));    //edit the list in memory
        FileWriter.persistUsers();  //write it to the file
    }

    public static App getI() {
        return instance;
    }

    public void changeSceneOnMainStage(SceneManager.SceneType sceneType)
    {
        stage.setScene(SceneManager.getInstance().getScene(sceneType));
    }

}
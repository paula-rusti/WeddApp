package sceneUtils;

import Main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;
import java.util.EnumMap;

public class SceneManager
{
    public enum SceneType
    {
        LOGIN,
        REGISTER,
        ORG_NO_WED,
        ORG_WED,
        GUEST_MAIN,
        WED_DETAILS,
        WED_LIST,
        INVITES
        TASK_LIST,
        ADD_TASK
    }

    private static SceneManager instance = null;
    private EnumMap<SceneType, Pair<Scene, Object>> sceneMap;      //map the enum(key) to a pair of Scene and Controller(Object)

    private SceneManager()
    {
        instance = this;
        sceneMap = new EnumMap<>(SceneType.class);

        // Add lines to have each enum covered
        sceneMap.put(SceneType.LOGIN, loadScene("/Main/login.fxml"));
        sceneMap.put(SceneType.REGISTER, loadScene("/Main/register.fxml"));
        sceneMap.put(SceneType.ORG_NO_WED, loadScene("/Main/orgNoWed.fxml"));
        sceneMap.put(SceneType.ORG_WED, loadScene("/Main/orgWed.fxml"));
        sceneMap.put(SceneType.GUEST_MAIN, loadScene("/Main/guestMain.fxml"));
        sceneMap.put(SceneType.WED_DETAILS, loadScene("/Main/changeDetails.fxml"));
        sceneMap.put(SceneType.WED_LIST, loadScene("/Main/WedList.fxml"));
        sceneMap.put(SceneType.INVITES, loadScene("/Main/invitesList.fxml"));
        sceneMap.put(SceneType.TASK_LIST, loadScene("/Main/manageTaskList.fxml"));
        sceneMap.put(SceneType.ADD_TASK, loadScene("/Main/createTask.fxml"));

        // Check that all enums have a loaded scene
        for(SceneType s : SceneType.values())
        {
            if (!sceneMap.containsKey(s)) {
                throw new RuntimeException("Program does not load scene " + s.name());
            }
        }
    }

    public static SceneManager getInstance()
    {
        if(instance == null)
        {
            instance = new SceneManager();
        }
        return instance;
    }

    public Scene getScene(SceneType sceneKey)
    {
        return sceneMap.get(sceneKey).getKey();
    }

    public <T> T getController(SceneType sceneKey)
    {
        return (T)sceneMap.get(sceneKey).getValue();
    }

    public static Pair<Scene, Object> loadScene(String fxml)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(fxml));

        Parent root;
        try
        {
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Location " + fxml + " not loaded properly(FXML URL:" + loader.getLocation() + ")");
        }
        return new Pair<>(new Scene(root), loader.getController());
    }
}

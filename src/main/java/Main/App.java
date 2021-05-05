package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import sceneUtils.SceneManager;

import java.io.IOException;

public class App extends Application {

    private Stage stage;
    private static App instance;
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        instance = this;

        // Initialize all scenes
        SceneManager.getInstance();

        //Choose first appearing scene
        stage.setScene(SceneManager.getInstance().getScene(SceneManager.SceneType.PRIMARY));

        stage.setResizable(false);
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
package Main;

import javafx.application.Platform;

public class EntryPoint {
    // For jar file
    public static void main(String[] args) {
        Platform.startup(()->{});
        App.main(args);
    }
}

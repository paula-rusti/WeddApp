module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens Main to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens sceneUtils to javafx.fxml;

    exports Main;
    exports Controllers;
    exports sceneUtils;
}
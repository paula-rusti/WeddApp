module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires commons.io;
    requires com.jfoenix;

    opens Main to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens sceneUtils to javafx.fxml;
    opens jsonUtils to com.fasterxml.jackson.databind;
    opens model to com.fasterxml.jackson.databind;

    exports Main;
    exports Controllers;
    exports sceneUtils;
    exports jsonUtils;
    exports model to com.fasterxml.jackson.databind;
    exports exceptions;
}
module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens Main to javafx.fxml;
    exports Main;
}
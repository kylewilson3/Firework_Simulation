module com.example.animation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animation to javafx.fxml;
    exports com.example.animation;
}
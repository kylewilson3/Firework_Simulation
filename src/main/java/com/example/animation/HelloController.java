/**
 * Hello Controller Class
 * @author: IntelliJ
 * Leftover from IntelliJ project creation
 */

package com.example.animation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
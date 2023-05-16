package com.example.finalsprojectitecc04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField username;
    @FXML
    public void SignUp(ActionEvent e) throws IOException {
        SceneControll.registerPage(e);
    }
    @FXML
    public void LogIn(ActionEvent e) throws IOException {
        System.out.println("Logged in");
    }
}
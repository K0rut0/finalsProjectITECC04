package com.example.finalsprojectitecc04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUp {
    @FXML
    private TextField username;
    @FXML
    private PasswordField PrimaryPass;
    @FXML
    private PasswordField ConfirmPass;

    @FXML
    public void signUp(ActionEvent e){
        String user = username.getText();
        String password = PrimaryPass.getText();
        String confirmation = ConfirmPass.getText();
    }
}

package com.example.finalsprojectitecc04;
import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignController {
    static Connection connection;
    public static void connect(Connection conn){
        connection = conn;
    }
    @FXML
    private TextField username;
    @FXML
    private PasswordField primaryPass;
    @FXML
    private PasswordField confirmPass;

    public SignController() throws SQLException {

    }

    public boolean checkValidity(){
        String a = primaryPass.getText();
        String b = confirmPass.getText();
        int n = a.compareTo(b);
        if(n == 0){
            return true;
        } else {
            return false;
        }
    }
   // public boolean checkUser(){

    //}
    @FXML
    public void signUp() throws IOException {
        try(PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO user_credentials(username, pass) VALUES (?, ?)
                """)) {

                statement.setString(1, username.getText());
                statement.setString(2, primaryPass.getText());
                int ins = statement.executeUpdate();
                System.out.print("done");
                username.setText("");
                primaryPass.setText("");
                confirmPass.setText("");
                SuccessWindow.displaySuccess();
        } catch (SQLException e) {
            SuccessWindow.displayError("Username already in use!");
            username.setText("");
            username.setPromptText("Username already in use");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void backToLogin() throws IOException {
        SceneControl.changeToLogin();
    }


}

package com.example.finalsprojectitecc04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    Connection LoginConnection = DriverManager.getConnection(
            "jdbc:postgresql://db.zmebaqpxjlkacjajfoxn.supabase.co:6543/postgres",
            "postgres", "ITECC04[]*04"
    );

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public HelloController() throws SQLException {
    }

    @FXML
    public void SignUp(ActionEvent e) throws IOException {
        SignController.connect(LoginConnection);
        SceneControl.registerPage(e);
    }
    @FXML
    public void LogIn(ActionEvent e) throws IOException{
        try(PreparedStatement statement = LoginConnection.prepareStatement("""
                SELECT pass FROM user_credentials WHERE username=?
                """)){
            statement.setString(1, username.getText());
            ResultSet exec = statement.executeQuery();
            String pass = "";
            while(exec.next()){
                pass = exec.getString("pass");
                //System.out.println(pass);
            }
            if(pass.compareTo(password.getText()) == 0){
                MainPageController.set(username.getText());
                MainPageController.connect(LoginConnection);
                SceneControl.loggedIn(e, username.getText());
            }
        } catch (SQLException sql){
            SuccessWindow.displayError("Error, no such account");
            throw new RuntimeException(sql);
        }

    }
}
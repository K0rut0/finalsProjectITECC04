package com.example.finalsprojectitecc04;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignController {
    Connection connection = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/test",
            "root", "Kill[]Switch*>[]"
    );
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
        String b = primaryPass.getText();
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
    public void signUp(){
        try(PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO user_credentials(username, password) VALUES (?, ?)
                """)) {

                statement.setString(1, username.getText());
                statement.setString(2, primaryPass.getText());
                int ins = statement.executeUpdate();
                System.out.print("done");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

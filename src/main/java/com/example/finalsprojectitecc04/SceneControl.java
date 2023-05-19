package com.example.finalsprojectitecc04;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class SceneControl {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public static void changeToSign() throws IOException{
        root = FXMLLoader.load(SceneControl.class.getResource("SignUp.fxml"));
    }
    public static  void changeToLogin() throws IOException{
        root = FXMLLoader.load(SceneControl.class.getResource("hello-view.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void loggedIn(ActionEvent e, String user) throws IOException{
        root = FXMLLoader.load(SceneControl.class.getResource("MainPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void registerPage(ActionEvent e) throws IOException {
        SceneControl.changeToSign();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

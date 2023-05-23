package com.example.finalsprojectitecc04;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

public class SuccessWindow {
    @FXML
    private Button okButton;
    @FXML
    private static Text mainMessage;
    public void cancel(){
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
    public static void displaySuccess() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Success!");
        stage.setMinWidth(800);
        Parent root = FXMLLoader.load(SuccessWindow.class.getResource("SuccessWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public static void displayError(String message) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error!");
        mainMessage.setText(message);
        stage.setMinWidth(800);
        Parent root = FXMLLoader.load(SuccessWindow.class.getResource("SuccessWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }


}

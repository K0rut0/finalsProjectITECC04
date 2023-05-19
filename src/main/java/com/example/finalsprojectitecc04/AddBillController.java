package com.example.finalsprojectitecc04;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.chrono.Chronology;

public class AddBillController {
    static Connection connection;
    public static void connect(Connection conn){
        connection = conn;
    }
    private static String user;
    public static void set(String us){
        user = us;
    }
    @FXML
    private DatePicker date;
    @FXML
    private TextField billName;
    @FXML
    private TextField accNumber;
    @FXML
    private TextField billAmount;
    @FXML
    private Button closeButton;
    @FXML
    public void cancel(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void addBill(){
        String name = billName.getText();
        String accId = accNumber.getText();
        String amount = billAmount.getText();
        Date dueDate = Date.valueOf(date.getValue());
        try(PreparedStatement statement = connection.prepareStatement("""
                INSERT INTO  user_bills(belongs_to, bill_name, bill_amount, bill_due, account_number) VALUES (?, ?, ?, ?, ?)""")) {
            statement.setString(1, user);
            statement.setString(2, name);
            try{
                int n = Integer.parseInt(amount);
                statement.setInt(3, n);
            } catch(SQLException a){
                billAmount.setText("");
                billAmount.setPromptText("Enter a valid integer");
                throw new RuntimeException(a);

            }

            statement.setDate(4, dueDate);
            statement.setString(5, accId);
            int insert = statement.executeUpdate();
            System.out.println("done");

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

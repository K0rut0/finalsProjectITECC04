package com.example.finalsprojectitecc04;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayBillController {
    static Connection connection;
    public static void connect(Connection conn){
        connection = conn;
    }
    static String username;
    public static void setUser(String user){
        username = user;
    }
    @FXML
    private TextField billName;
    @FXML
    private TextField accountNumber;
    @FXML TextField amountToPay;
    @FXML
    private Button close;
    public void cancel(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void payBill(){
        int amount = 0;
        try(PreparedStatement statement = connection.prepareStatement("""
                 SELECT bill_amount FROM user_bills WHERE belongs_to=?""")){
            statement.setString(1, username);
            ResultSet billCost = statement.executeQuery();
            while(billCost.next()){
                amount = billCost.getInt("bill_amount");
            }
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
        try(PreparedStatement statement = connection.prepareStatement("""
                UPDATE user_bills SET bill_amount = ? WHERE (belongs_to = ? AND  bill_name = ? AND account_number = ?)""")){
            statement.setInt(1, (amount-Integer.parseInt(amountToPay.getText())));
            statement.setString(2, username);
            statement.setString(3, billName.getText());
            statement.setString(4, accountNumber.getText());
            int execute = statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}

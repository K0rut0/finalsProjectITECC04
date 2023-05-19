package com.example.finalsprojectitecc04;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainPageController {
    static Connection connection;
    public static void connect(Connection conn){
        connection = conn;
    }

    @FXML
    private Text welcomeMessage;
    @FXML
    private TableView table;
    @FXML
    private Text billTotal;
    private static String userName;
    public static void set(String user){
        userName = user;
    }
    public void setWelcomeMessage() throws IOException {
        welcomeMessage.setText("Welcome " + userName);
        System.out.println(welcomeMessage);
    }
    public void updateTotal(ArrayList<Integer> bills){
        int sum = 0;
        for(int i = 0; i<bills.size(); i++){
            sum+=bills.get(i);
        }
        billTotal.setText("PHP " + sum);

    }
    public void getBills(){
        try(PreparedStatement statement = connection.prepareStatement("""
                SELECT * FROM user_bills WHERE belongs_to=?
                """)){
            statement.setString(1, userName);
            ResultSet exec = statement.executeQuery();
            ArrayList<Integer> billVals = new ArrayList<>();
            ArrayList<String> billNames = new ArrayList<>();
            ArrayList<String> accNumbers = new ArrayList<>();
            ArrayList<Date> dueDates= new ArrayList<>();
            while(exec.next()){
                billVals.add(exec.getInt("bill_amount"));
                billNames.add(exec.getString("bill_name"));
                accNumbers.add(exec.getString("account_number"));
                dueDates.add((exec.getDate("bill_due")));
                //System.out.println(pass);
            }
            System.out.println(dueDates);
            updateTotal(billVals);
        } catch (SQLException sql){
            throw new RuntimeException(sql);
        }
    }
    public void addBill() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Bill");
        stage.setMinWidth(800);
        Parent root = FXMLLoader.load(getClass().getResource("AddBill.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AddBillController.set(userName);
        AddBillController.connect(connection);
        stage.showAndWait();
    }
    public void payBill() throws IOException{
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pay Bill");
        stage.setMinWidth(800);
        Parent root = FXMLLoader.load(getClass().getResource("PayBill.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        PayBillController.setUser(userName);
        PayBillController.connect(connection);
        stage.showAndWait();
    }
    @FXML
    public void initialize() throws IOException {
        setWelcomeMessage();
        getBills();
    }

}

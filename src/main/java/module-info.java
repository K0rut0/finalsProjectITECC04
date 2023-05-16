module com.example.finalsprojectitecc04 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires waffle.jna;

    opens com.example.finalsprojectitecc04 to javafx.fxml;
    exports com.example.finalsprojectitecc04;
}
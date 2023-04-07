module com.example.memorialplangui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.memorialplangui to javafx.fxml;
    exports com.example.memorialplangui;
}
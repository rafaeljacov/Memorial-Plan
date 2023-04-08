package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalController implements Initializable {

    @FXML
    private ChoiceBox<String> gender, category;
    @FXML
    private Label lotPrice;
    private  String[] genderItems = {"Male", "Female"};
    private String[] categoryItems = {"Regular", "Inner Walk", "Walk", "Inner Drive", "Drive"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genderItems);
        category.getItems().addAll(categoryItems);
        category.setOnAction(this::getLotPrice);
    }

    public void getLotPrice(ActionEvent e) {
        switch (category.getValue()) {
            case "Regular" -> lotPrice.setText("Lot Price: ₱ 13,000");
            case "Inner Walk" -> lotPrice.setText("Lot Price: ₱ 16,300");
            case "Walk" -> lotPrice.setText("Lot Price: ₱ 17,950");
            case "Inner Drive" -> lotPrice.setText("Lot Price: ₱ 19,600");
            case "Drive" -> lotPrice.setText("Lot Price: ₱ 21,250");
        }

    }
    public void next(ActionEvent e) throws IOException {
        String value = category.getValue();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("plan.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}

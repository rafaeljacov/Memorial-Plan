package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalController implements Initializable {

    @FXML
    public ChoiceBox<String> gender, category;
    @FXML
    private Label lotPrice, errorMessage;
    @FXML
    public TextField name, age, address, email;
    private final String[] genderItems = {"Male", "Female"};
    private final String[] categoryItems = {"Regular", "Inner Walk", "Walk", "Inner Drive", "Drive"};

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
        if (name.getText().strip().equals("") || email.getText().strip().equals("") || address.getText().strip().equals("") || gender.getValue() == null || age.getText().strip().equals("") || category.getValue() == null) {
            errorMessage.setText("All fields must be completed before proceeding");
            errorMessage.setVisible(true);
        } else if (!email.getText().strip().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            errorMessage.setText("Invalid email input");
            errorMessage.setVisible(true);
        } else if (!ageIsValid()) {
            // Do nothing
        } else {
            Details.name = name.getText().strip();
            Details.age = Integer.parseInt(age.getText());
            Details.address = address.getText().strip();
            Details.gender = gender.getValue();
            Details.email = email.getText().strip();
            Details.category = category.getValue();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("plan.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
    }


    public void numbersOnly(KeyEvent keyEvent) {
        ageIsValid();
    }

    private boolean ageIsValid() {
        if (!age.getText().strip().matches("^[0-9]*$") || age.getText().contains(" ")){
            errorMessage.setText("Invalid age input");
            errorMessage.setVisible(true);
            return false;
        } else {
            errorMessage.setVisible(false);
            return true;
        }
    }
}

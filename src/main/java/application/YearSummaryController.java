package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class YearSummaryController {
    @FXML
    private ImageView home;
    @FXML
    public Label category, plan, lotPrice, vat, sellPrice, downPay, balance, intRate, interest, contract, yearly;


    public void hoverIn(MouseEvent event) {
        home.setOpacity(1);
    }

    public void goHome(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("personal.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void hoverOut(MouseEvent event) {
        home.setOpacity(0.5);
    }
}

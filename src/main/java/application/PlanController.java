package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlanController {
    @FXML
    private Rectangle indicator1, indicator2;
    @FXML
    private Button yearButton, nonYearButton;
    @FXML
    private Pane yearPane, nonYearPane;

    public void handleYearButton(ActionEvent e) {
        indicator1.setVisible(true);
        indicator2.setVisible(false);

        yearButton.getStyleClass().add("toggle");
        nonYearButton.getStyleClass().remove("toggle");
    }

    public void handleNonYearButton(ActionEvent e) {
        indicator1.setVisible(false);
        indicator2.setVisible(true);

        yearButton.getStyleClass().remove("default");
        yearButton.getStyleClass().remove("toggle");
        nonYearButton.getStyleClass().add("toggle");
    }

}

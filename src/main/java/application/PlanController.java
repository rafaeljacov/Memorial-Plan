package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

public class PlanController {
    @FXML
    private Rectangle indicator1, indicator2;
    @FXML
    private Button yearButton, nonYearButton;
    @FXML
    private Pane yearPane, nonYearPane, card1,card2,card3, cardSpot, card30, card60;
    @FXML
    private Label label1,label2,label3, intRate1,intRate2,intRate3, percent1,percent2,percent3, labelSpot, label30, label60, discountSpot, discount30, discount60, percentSpot, percent30, percent60;
    @FXML
    private Circle cardCircle1, cardCircle2, cardCircle3, cardCircleSpot, cardCircle30, cardCircle60;
    @FXML
    private Label errorMessage;
    private String planSelected;

    public void handleYearButton(ActionEvent e) {
        if (!yearPane.isVisible()) {
            planSelected = null;
            indicator1.setVisible(true);
            indicator2.setVisible(false);

            yearButton.getStyleClass().add("toggle");
            nonYearButton.getStyleClass().remove("toggle");

            yearPane.setVisible(true);
            nonYearPane.setVisible(false);

            resetStylesNonYear();
        }

    }

    public void handleNonYearButton(ActionEvent e) {
        if (!nonYearPane.isVisible()) {
            planSelected = null;
            indicator1.setVisible(false);
            indicator2.setVisible(true);

            yearButton.getStyleClass().remove("default");
            yearButton.getStyleClass().remove("toggle");
            nonYearButton.getStyleClass().add("toggle");

            yearPane.setVisible(false);
            nonYearPane.setVisible(true);

            resetStylesYear();
        }
    }


    public void cardClick1(MouseEvent mouseEvent) {
        errorMessage.setVisible(false);
        planSelected = "One Year";
        resetStylesYear();

        label1.getStyleClass().add("label-select-fill");
        intRate1.getStyleClass().add("label-select-fill");
        percent1.getStyleClass().add("label-select-fill");
        card1.getStyleClass().add("card-toggle");
        cardCircle1.setVisible(true);
    }

    public void cardClick2(MouseEvent mouseEvent) {
        errorMessage.setVisible(false);
        planSelected = "Two Years";
        resetStylesYear();

        label2.getStyleClass().add("label-select-fill");
        intRate2.getStyleClass().add("label-select-fill");
        percent2.getStyleClass().add("label-select-fill");
        card2.getStyleClass().add("card-toggle");
        cardCircle2.setVisible(true);
    }

    public void cardClick3(MouseEvent mouseEvent) {
        errorMessage.setVisible(false);
        planSelected = "Three Years";
        resetStylesYear();

        label3.getStyleClass().add("label-select-fill");
        intRate3.getStyleClass().add("label-select-fill");
        percent3.getStyleClass().add("label-select-fill");
        card3.getStyleClass().add("card-toggle");
        cardCircle3.setVisible(true);
    }

    public void cardClickSpot(MouseEvent mouseEvent) {
        errorMessage.setVisible(false);
        planSelected = "Spot Cash";
        resetStylesNonYear();

        labelSpot.getStyleClass().add("label-select-fill");
        discountSpot.getStyleClass().add("label-select-fill");
        percentSpot.getStyleClass().add("label-select-fill");
        cardSpot.getStyleClass().add("card-toggle");
        cardCircleSpot.setVisible(true);
    }

    public void cardClick30(MouseEvent mouseEvent) {
        errorMessage.setVisible(false);
        planSelected = "30 Days";
        resetStylesNonYear();

        label30.getStyleClass().add("label-select-fill");
        discount30.getStyleClass().add("label-select-fill");
        percent30.getStyleClass().add("label-select-fill");
        card30.getStyleClass().add("card-toggle");
        cardCircle30.setVisible(true);
    }

    public void cardClick60(MouseEvent mouseEvent) {
        errorMessage.setVisible(false);
        planSelected = "60 Days";
        resetStylesNonYear();

        label60.getStyleClass().add("label-select-fill");
        discount60.getStyleClass().add("label-select-fill");
        percent60.getStyleClass().add("label-select-fill");
        card60.getStyleClass().add("card-toggle");
        cardCircle60.setVisible(true);
    }


    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("personal.fxml"));
        Scene scene = new Scene(loader.load());
        PersonalController controller = loader.getController();

        controller.name.setText(Details.name);
        controller.email.setText(Details.email);
        controller.age.setText(Integer.toString(Details.age));
        controller.address.setText(Details.address);
        controller.gender.setValue(Details.gender);
        controller.category.setValue(Details.category);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void confirm(ActionEvent event) throws IOException {
        if (planSelected == null) {
            errorMessage.setVisible(true);
        } else {
            Details.planSelected = planSelected;

            switch (planSelected) {
                case "One Year", "Two Years", "Three Years" -> displayYearSummary(event);
                case "Spot Cash", "30 Days", "60 Days" -> displayNonYearSummary(event);
            }
        }
    }
    private void displayYearSummary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("yearSummary.fxml"));
        Scene scene = new Scene(loader.load());
        YearSummaryController controller = loader.getController();

        controller.category.setText(Details.category);
        controller.plan.setText(Details.planSelected);

        Summary summary = new Summary();

        controller.lotPrice.setText(formatNum(summary.lotPrice));
        controller.vat.setText(formatNum(summary.vat));
        controller.sellPrice.setText(formatNum(summary.sellPrice));
        controller.downPay.setText(formatNum(summary.downPay));
        controller.balance.setText(formatNum(summary.balance));
        controller.intRate.setText("(" + (int)(summary.intRate * 100) + "%)");
        controller.interest.setText(formatNum(summary.interest));
        controller.contract.setText(formatNum(summary.contract));
        controller.yearly.setText(formatNum(summary.yearly));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    private void displayNonYearSummary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("nonYearSummary.fxml"));
        Scene scene = new Scene(loader.load());
        NonYearSummaryController controller = loader.getController();

        controller.category.setText(Details.category);
        controller.plan.setText(Details.planSelected);

        Summary summary = new Summary();

        controller.lotPrice.setText(formatNum(summary.lotPrice));
        controller.vat.setText(formatNum(summary.vat));
        controller.sellPrice.setText(formatNum(summary.sellPrice));
        controller.discountRate.setText("(" + (int)(summary.discountRate * 100) + "%)");
        controller.discount.setText(formatNum(summary.discount));
        controller.total.setText(formatNum(summary.total));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    private void resetStylesYear() {
        label1.getStyleClass().remove("label-select-fill");
        intRate1.getStyleClass().remove("label-select-fill");
        percent1.getStyleClass().remove("label-select-fill");
        card1.getStyleClass().remove("card-toggle");

        label2.getStyleClass().remove("label-select-fill");
        intRate2.getStyleClass().remove("label-select-fill");
        percent2.getStyleClass().remove("label-select-fill");
        card2.getStyleClass().remove("card-toggle");

        label3.getStyleClass().remove("label-select-fill");
        intRate3.getStyleClass().remove("label-select-fill");
        percent3.getStyleClass().remove("label-select-fill");
        card3.getStyleClass().remove("card-toggle");

        cardCircle1.setVisible(false);
        cardCircle2.setVisible(false);
        cardCircle3.setVisible(false);
    }
    private void resetStylesNonYear() {
        labelSpot.getStyleClass().remove("label-select-fill");
        discountSpot.getStyleClass().remove("label-select-fill");
        percentSpot.getStyleClass().remove("label-select-fill");
        cardSpot.getStyleClass().remove("card-toggle");

        label30.getStyleClass().remove("label-select-fill");
        discount30.getStyleClass().remove("label-select-fill");
        percent30.getStyleClass().remove("label-select-fill");
        card30.getStyleClass().remove("card-toggle");

        label60.getStyleClass().remove("label-select-fill");
        discount60.getStyleClass().remove("label-select-fill");
        percent60.getStyleClass().remove("label-select-fill");
        card60.getStyleClass().remove("card-toggle");

        cardCircleSpot.setVisible(false);
        cardCircle30.setVisible(false);
        cardCircle60.setVisible(false);
    }

    private String formatNum(int num) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        String peso = "₱ ";
        return peso + formatter.format(num);
    }
}

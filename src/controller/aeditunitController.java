package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class aeditunitController {

    @FXML
    Button dashboardButton, reportsButton, UpdateButton, backButton;

    @FXML
    TextField search_tf;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        System.out.println("Dashboard button clicked");
        changeScene(event, "/view/adashboardscansandreports.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked");
        changeScene(event, "/view/areports.fxml");
    }

    public void goToUpdatedUnit(ActionEvent event) throws IOException {
        System.out.println("Update Information button clicked");
        changeScene(event, "/view/ainventoryunitdetails.fxml");
    }

    public void backToUnitInformation(ActionEvent event) throws IOException {
        System.out.println("Back button clicked");
        changeScene(event, "/view/ainventoryunitdetails.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

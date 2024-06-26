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

public class aaddunitController {

    @FXML
    Button dashboardButton, reportsButton, addModelButton, backButton;

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

    public void addunit(ActionEvent event) throws IOException {
        System.out.println("Add Unit button clicked");
        changeScene(event, "/view/ainventory.fxml");
    }

    public void backToAddModel(ActionEvent event) throws IOException {
        System.out.println("Back button clicked");
        changeScene(event, "/view/ainventoryaddmodel.fxml");            
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

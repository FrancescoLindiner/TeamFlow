package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SplashScreenErrore {

    @FXML
    void buttonRiprova(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/fxmlLogin.fxml"));
        Scene scene = new Scene(parent, 810, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setTitle("Login");
        window.setScene(scene);
        window.show();
    }
}
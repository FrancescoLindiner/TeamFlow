package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class PannelloRecuperaPassword {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    void buttonRiprova(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/RecuperaPassword.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/fxmlLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
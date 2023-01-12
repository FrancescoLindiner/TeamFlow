package main.java.com.tf.teamflowcode.GestineImpiegati.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AggiungiImpiegato {

    @FXML
    private Button buttonConferma;

    @FXML
    private Button buttonVaiIndietro;

    @FXML
    void buttonConferma(ActionEvent event) {

    }

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
        
        Parent parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/GestioneImpiegati.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Gestione Impiegati");
        stage.show();
    }

}

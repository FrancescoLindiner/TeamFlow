package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestineImpiegati.Control.GestioneImpiegati;
import main.java.com.tf.teamflowcode.GestineImpiegati.Interface.RicercaImpiegati;
import javafx.scene.Node;

public class PannelloConfermaRimuovi {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/RicercaImpiegati.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Modifica Impiegato");
        stage.show();
    }

    @FXML
    public void buttonRimuovi(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/GestioneImpiegati.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Gestione Impiegati");
        stage.show();
        GestioneImpiegati gestioneImpiegati = new GestioneImpiegati();
        RicercaImpiegati ricercaImpiegati = new RicercaImpiegati();
        gestioneImpiegati.rimuoviImpiegato(ricercaImpiegati.impiegato);
        gestioneImpiegati.list.clear();
    }
}
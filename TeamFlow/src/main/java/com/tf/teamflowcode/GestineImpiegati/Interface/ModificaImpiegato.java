package main.java.com.tf.teamflowcode.GestineImpiegati.Interface;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.Entity.Dipendente;
import main.java.com.tf.teamflowcode.GestineImpiegati.Control.GestioneImpiegati;
import javafx.scene.Node;

public class ModificaImpiegato {

    @FXML
    private TextField textFiledGrado;

    @FXML
    private TextField textFiledEmail;

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        RicercaImpiegati ricercaImpiegati = new RicercaImpiegati();
        GestioneImpiegati gestioneImpiegati = new GestioneImpiegati();
        ModificaImpiegato modificaImpiegato = new ModificaImpiegato();
        ObservableList<Dipendente> impiegato = ricercaImpiegati.impiegato;
        if (textFiledGrado.getText().equals("")) {
            gestioneImpiegati.modificaImpiegatoEmail(impiegato, textFiledEmail.getText());
        } else if (textFiledEmail.getText().equals("")) {
            gestioneImpiegati.modificaImpiegatoGrado(impiegato, textFiledGrado.getText());
        } else {
            gestioneImpiegati.modificaImpiegato(impiegato, textFiledGrado.getText(), textFiledEmail.getText());
        }
        gestioneImpiegati.list.clear();
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/GestioneImpiegati.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ricerca Impiegati");
        stage.show();
    }

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/RicercaImpiegati.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ricerca Impiegati");
        stage.show();
    }
}
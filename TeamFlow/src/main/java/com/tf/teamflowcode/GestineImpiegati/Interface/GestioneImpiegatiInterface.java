package main.java.com.tf.teamflowcode.GestineImpiegati.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestineImpiegati.Control.GestioneImpiegati;
import javafx.scene.Node;

public class GestioneImpiegatiInterface {

    @FXML
    private Button buttonVaiIndietro;

    @FXML
    private Button buttonAggiungiImpiegato;

    @FXML
    private TextField textFieldCognome;

    @FXML
    private TextField textFieldMatricola;

    @FXML
    private TextField textFieldNome;

    RicercaImpiegati ricercaImpiegati = new RicercaImpiegati();

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
        Parent splashScreen = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleAmministratore.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(splashScreen, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Interfaccia Principale - Amministratore");
        stage.show();
    }

    @FXML
    void buttonAggiungiImpiegati(ActionEvent event) throws IOException {
        Parent splashScreen = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/AggiungiImpiegato.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(splashScreen, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Aggiungi Impiegato");
        stage.show();
    }

    @FXML
    void buttonCerca(ActionEvent event) throws IOException {
        GestioneImpiegati gestioneImpiegati = new GestioneImpiegati();

        if (textFieldNome.getText().isEmpty() && textFieldCognome.getText().isEmpty()) {
            gestioneImpiegati.cercaImpiegatoPerMatricola(textFieldMatricola.getText());
        } else if (textFieldCognome.getText().isEmpty() && textFieldMatricola.getText().isEmpty()) {
            gestioneImpiegati.cercaImpiegatoPerNome(textFieldNome.getText());
        } else if (textFieldMatricola.getText().isEmpty() && textFieldNome.getText().isEmpty()) {
            gestioneImpiegati.cercaImpiegatoPerCognome(textFieldCognome.getText());
        } else if (textFieldNome.getText().isEmpty()) {
            gestioneImpiegati.cercaImpiegatoPerCognomeEMatricola(textFieldCognome.getText(),
                    textFieldMatricola.getText());
        } else if (textFieldCognome.getText().isEmpty()) {
            gestioneImpiegati.cercaImpiegatoPerNomeEMatricola(textFieldNome.getText(), textFieldMatricola.getText());
        } else if (textFieldMatricola.getText().isEmpty()) {
            gestioneImpiegati.cercaImpiegatoPerNomeECognome(textFieldNome.getText(), textFieldCognome.getText());
        } else {
            gestioneImpiegati.cercaImpiegato(textFieldNome.getText(), textFieldCognome.getText(),
                    textFieldMatricola.getText());
        }

        Parent splashScreen = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/RicercaImpiegati.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(splashScreen, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Risultati Ricerca");
        stage.show();
    }

}
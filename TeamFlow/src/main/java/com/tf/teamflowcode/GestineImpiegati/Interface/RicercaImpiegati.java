package main.java.com.tf.teamflowcode.GestineImpiegati.Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.Entity.Dipendente;
import main.java.com.tf.teamflowcode.GestineImpiegati.Control.GestioneImpiegati;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.Node;

public class RicercaImpiegati implements Initializable {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML 
    private ListView<Dipendente> myListView;

    public static ObservableList<Dipendente> impiegato;

    @FXML
    void buttonModifica(ActionEvent event) throws IOException {
        impiegato = myListView.getSelectionModel().getSelectedItems();
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/ModificaImpiegato.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Modifica Impiegato");
        stage.show();
    }

    @FXML
    void buttonRimuovi(ActionEvent event) throws IOException {
        impiegato = myListView.getSelectionModel().getSelectedItems();
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/PannelloConfermaRimuovi.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Rimuovi Impiegato");
        stage.show();
    }

    @FXML
    void vaiindietro(ActionEvent event) throws IOException {
        GestioneImpiegati gestioneImpiegati = new GestioneImpiegati();
        gestioneImpiegati.list.clear();
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneImpiegati/fxml/GestioneImpiegati.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Gestione Impiegati");
        stage.show();
    }

    ObservableList<Dipendente> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GestioneImpiegati gestioneImpiegati = new GestioneImpiegati();
        for (Dipendente d : gestioneImpiegati.list) {
            list.add(d);
        }
        myListView.setItems(list);
    }
}
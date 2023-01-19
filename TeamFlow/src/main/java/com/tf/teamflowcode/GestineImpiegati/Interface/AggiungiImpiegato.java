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
import main.java.com.tf.teamflowcode.GestineImpiegati.Control.AggiungiControl;
import javafx.scene.Node;

public class AggiungiImpiegato {

    @FXML
    private Button buttonConferma;

    @FXML
    private Button buttonVaiIndietro;

    @FXML
    private TextField textFieldCognome;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldGrado;

    @FXML
    private TextField textFiledNome;

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        AggiungiControl aggiungiControl = new AggiungiControl();
        if (!textFiledNome.getText().equals("") && !textFieldCognome.getText().equals("")
                && !textFieldEmail.getText().equals("") && !textFieldGrado.getText().equals("")) {
            aggiungiControl.aggiungiImpiegato(textFiledNome.getText(), textFieldCognome.getText(),
                    textFieldGrado.getText(), textFieldEmail.getText(), event);
        }
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

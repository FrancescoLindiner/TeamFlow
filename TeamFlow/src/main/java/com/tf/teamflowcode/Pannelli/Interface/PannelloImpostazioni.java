package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class PannelloImpostazioni {

    @FXML
    private Button buttonIndietro;

    @FXML
    private Button buttonModificaPassword;

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
    void modificaPassword(ActionEvent event) throws IOException {
        Parent splashScreen = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/ModificaPassword.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(splashScreen, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Modifica Password");
        stage.show();
    }

}

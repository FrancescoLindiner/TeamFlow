package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class InterfacciaPrincipaleAmministratore implements Initializable {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private Button buttonImpostazioni;

    @FXML
    private Label label1;

    @FXML
    private Label labelData;

    @FXML
    private Label nomeUtente;

    public void setData() {
        DateFormat Date = DateFormat.getDateInstance();

        Calendar cals = Calendar.getInstance();

        String currentDate = Date.format(cals.getTime());

        labelData.setText(currentDate);
        label1.setText("Turno del " + currentDate);
    }

    @FXML
    void buttonImpostazioni(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/impostazioni.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Impostazioni");
        stage.show();
    }

    @FXML
    void apriGestioneImpiegati(ActionEvent event) throws IOException {
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

    @FXML
    private AnchorPane pane;

    @FXML
    void buttonLogout(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/Logout.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    // Parent parent2 =
    // FXMLLoader.load(getClass().getResource("../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/fxmlLogin.fxml"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }
}
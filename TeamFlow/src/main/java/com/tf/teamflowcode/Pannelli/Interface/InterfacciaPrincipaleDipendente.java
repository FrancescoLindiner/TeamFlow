package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Control.ControlStipendi;
import javafx.scene.Node;

public class InterfacciaPrincipaleDipendente implements Initializable {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private Button buttonImpostazioni;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label nomeUtente;

    AccountControl accountControl = new AccountControl();

    public void setData() {
        AccountControl accountControl = new AccountControl();

        DateFormat Date = DateFormat.getDateInstance();

        Calendar cals = Calendar.getInstance();

        String currentDate = Date.format(cals.getTime());

        label1.setText("Turno del " + currentDate);

        label2.setText("Ciao " + accountControl.returnNome());
    }

    public void setNomeUtente() {

        nomeUtente.setText(accountControl.returnNome() + " " + accountControl.returnCognome());
    }

    @FXML
    void buttonImpostazioni(ActionEvent event) throws IOException {
        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/impostazioniAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Impostazioni");
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/impostazioniImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Impostazioni");
            stage.show();
        }
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
    void buttonLogout(ActionEvent event) throws IOException {
        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/LogoutAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/LogoutImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    @FXML
    void buttonTurni(ActionEvent event) throws IOException, ParseException {

        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneOrariEStipendi/fxml/Turni.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Turni");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
        setNomeUtente();
    }

    @FXML
    void mostraStipendi(ActionEvent event) throws IOException {

        DateFormat data = new SimpleDateFormat("dd");
        Date date = new Date();
        String dataDiOggi = data.format(date);

        DateFormat ora = new SimpleDateFormat("HH:mm");
        Date ora2 = new Date();
        String oraDiOggi = ora.format(ora2);

        if (dataDiOggi.equals("27") && oraDiOggi.equals("10:00")) {
            ControlStipendi controlStipendi = new ControlStipendi();
            controlStipendi.generaStipendi();
        }

        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestioneOrariEStipendi/fxml/StipendioAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Stipendio");
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestioneOrariEStipendi/fxml/StipendioImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Stipendio");
            stage.show();
        }

    }

    @FXML
    void buttonRichiediPermesso(ActionEvent event) throws IOException {
        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/RichiediPermessoAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Richiedi Permesso");
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/RichiediPermessoImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Richiedi Permesso");
            stage.show();
        }
    }

    @FXML
    void buttonFirma(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/InterfacciaFirma.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Firma");
        stage.show();
    }

    @FXML
    void buttonFirmaRemoto(ActionEvent event) throws IOException {
        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/FirmaDaRemotoAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Firma Da Remoto");
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/FirmaDaRemotoImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Firma Da Remoto");
            stage.show();
        }

    }

    @FXML
    void buttonComunicaAssenza(ActionEvent event) throws IOException {
        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/AssenzaMalattiaAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Assenza Malattia");
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/AssenzaMalattiaImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Assenza Malattia");
            stage.show();
        }

    }

    @FXML
    void buttonNotifiche(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/SezioneNotifiche.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sezione notifiche");
        stage.show();
    }

}
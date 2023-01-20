package main.java.com.tf.teamflowcode.GestionePresenze.Interface;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import main.java.com.tf.teamflowcode.GestionePresenze.Control.RimpiazzaImpiegato;
import main.java.com.tf.teamflowcode.Pannelli.Control.ControlAssenza;
import javafx.scene.Node;

public class ComunicaAssenza {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private DatePicker datePicker;

    ControlAssenza controlAssenza = new ControlAssenza();
    AccountControl accountControl = new AccountControl();
    RimpiazzaImpiegato rimpiazzaImpiegato = new RimpiazzaImpiegato();

    DateFormat giorno = new SimpleDateFormat("dd");
    Date giorno2 = new Date();
    String dataDiOggiGiorno = giorno.format(giorno2);
    int giornoInteger = Integer.parseInt(dataDiOggiGiorno);

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        LocalDate localDate = datePicker.getValue();
        if (Integer.parseInt(localDate.toString().substring(8, 10)) <= giornoInteger) {
            if (accountControl.returnRuolo().equals("Amministratore")) {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreAssenzaAmministratore.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Errore");
                stage.show();
                return;
            } else {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreAssenzaImpiegato.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Errore");
                stage.show();
                return;
            }
        }
        if (controlAssenza.verificaTurno(localDate.toString())) {
            rimpiazzaImpiegato.rimpiazzaAmministratoreGiornoIntero(localDate.toString(),
                    accountControl.returnMatricola(),
                    accountControl.returnNome() + " " + accountControl.returnCognome());
            if (accountControl.returnRuolo().equals("Amministratore")) {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaAssenzaAmministratore.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Conferma");
                stage.show();
            } else {
                rimpiazzaImpiegato.rimpiazzaImpiegatoGiornoIntero(localDate.toString(),
                        accountControl.returnMatricola(),
                        accountControl.returnNome() + " " + accountControl.returnCognome());
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaAssenzaImpiegato.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Conferma");
                stage.show();
            }
        } else {
            if (accountControl.returnRuolo().equals("Amministratore")) {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreAssenzaAmministratore.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Errore");
                stage.show();
            } else {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreAssenzaImpiegato.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Errore");
                stage.show();
            }
        }
    }

    @FXML
    void vaiIndietro(ActionEvent event) throws IOException {
        AccountControl accountControl = new AccountControl();
        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Interfaccia Principale - Amministratore");
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Interfaccia Principale - Impiegato");
            stage.show();
        }
    }
}
package main.java.com.tf.teamflowcode.GestionePresenze.Interface;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import main.java.com.tf.teamflowcode.GestionePresenze.Control.RichiediPermessoControl;
import javafx.scene.Node;

public class ModuloRichiediPermesso {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField oraPicker;

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

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        RichiediPermessoControl richiediPermessoControl = new RichiediPermessoControl();
        LocalDate localDate = datePicker.getValue();
        try {
            if (oraPicker.getText().equals("")) {
                if (richiediPermessoControl.controllaTurno(localDate.toString())
                        && richiediPermessoControl.controlla24HTurno(localDate.toString())) {
                    if (richiediPermessoControl.inserisciPermessoGiornoIntero(localDate.toString())) {
                        AccountControl accountControl = new AccountControl();
                        if (accountControl.returnRuolo().equals("Amministratore")) {
                            parent = FXMLLoader.load(getClass()
                                    .getResource(
                                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaPermessoAmministratore.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(parent, 810, 500);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } else {
                            parent = FXMLLoader.load(getClass()
                                    .getResource(
                                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaPermessoImpiegato.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(parent, 810, 500);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        }
                    }
                } else {
                    AccountControl accountControl = new AccountControl();
                    if (accountControl.returnRuolo().equals("Amministratore")) {
                        parent = FXMLLoader.load(getClass()
                                .getResource(
                                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrorePermessoAmministratore.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(parent, 810, 500);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } else {
                        parent = FXMLLoader.load(getClass()
                                .getResource(
                                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrorePermessoImpiegato.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(parent, 810, 500);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    }
                }
            } else {
                if (richiediPermessoControl.controllaTurno(localDate.toString())
                        && richiediPermessoControl.controlla24HOra(localDate.toString(),
                                oraPicker.getText().substring(0, 5))
                        &&
                        richiediPermessoControl.controllaOrari(localDate.toString(), oraPicker.getText())) {
                    if (richiediPermessoControl.inserisciPermessoOre(localDate.toString(),
                            oraPicker.getText().substring(0, 5),
                            oraPicker.getText().substring(6, 11))) {
                        AccountControl accountControl = new AccountControl();
                        if (accountControl.returnRuolo().equals("Amministratore")) {
                            parent = FXMLLoader.load(getClass()
                                    .getResource(
                                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaPermessoAmministratore.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(parent, 810, 500);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } else {
                            parent = FXMLLoader.load(getClass()
                                    .getResource(
                                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaPermessoImpiegato.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(parent, 810, 500);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        }
                    }
                } else {
                    AccountControl accountControl = new AccountControl();
                    if (accountControl.returnRuolo().equals("Amministratore")) {
                        parent = FXMLLoader.load(getClass()
                                .getResource(
                                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrorePermessoAmministratore.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(parent, 810, 500);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } else {
                        parent = FXMLLoader.load(getClass()
                                .getResource(
                                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrorePermessoImpiegato.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(parent, 810, 500);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrorePermessoImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (NullPointerException e) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrorePermessoImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

    }
}
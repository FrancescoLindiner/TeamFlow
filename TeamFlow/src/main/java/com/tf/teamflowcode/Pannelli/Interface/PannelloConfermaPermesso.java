package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import javafx.scene.Node;

public class PannelloConfermaPermesso {

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        AccountControl accountControl = new AccountControl();
        if (accountControl.returnRuolo().equals("Amministratore")) {
            Parent parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleAmministratore.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Interfaccia Principale - Amministratore");
            stage.show();
        } else {
            Parent parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleImpiegato.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Interfaccia Principale - Amministratore");
            stage.show();
        }
    }

    @FXML
    void buttonRiprova(ActionEvent event) throws IOException {
        AccountControl accountControl = new AccountControl();
        if (accountControl.returnRuolo().equals("Amministratore")) {
            Parent parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/RichiediPermessoAmministratore.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Interfaccia Principale - Amministratore");
            stage.show();
        } else {
            Parent parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/GestionePresenze/fxml/RichiediPermessoImpiegato.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Interfaccia Principale - Amministratore");
            stage.show();
        }
    }
}

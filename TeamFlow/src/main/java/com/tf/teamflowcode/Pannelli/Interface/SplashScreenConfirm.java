package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.Entity.Dipendente;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import javafx.scene.Node;

public class SplashScreenConfirm {

    AccountControl accountControl = new AccountControl();
    Parent parent;
    Scene scene;
    Stage window;

    @FXML
    void confermaButton(ActionEvent event) throws IOException {

        if (accountControl.returnRuolo().equals("Amministratore")) {
            parent = FXMLLoader.load(getClass().getResource(
                    "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleAmministratore.fxml"));
            scene = new Scene(parent, 810, 500);
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.centerOnScreen();

            window.setTitle("Interfaccia Principale - Amministratore");
            window.getIcons().add(new Image(
                    "C:\\Users\\frali\\Desktop\\TeamFlow\\TeamFlow\\src\\main\\resources\\com\\tf\\teamflowcode\\logo.png"));
            window.setScene(scene);
            window.show();
        } else {
            parent = FXMLLoader.load(getClass().getResource(
                    "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleImpiegato.fxml"));
            scene = new Scene(parent, 810, 500);
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.centerOnScreen();

            window.setTitle("Interfaccia Principale - Impiegato");
            window.setScene(scene);
            window.show();
        }

    }

}

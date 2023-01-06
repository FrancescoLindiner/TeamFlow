package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SplashScreenConfirm {
    @FXML
    void confermaButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleAmministratore.fxml"));
        Scene scene2 = new Scene(root, 810, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.centerOnScreen();

        window.setTitle("Interfaccia Principale - Amministratore");
        window.getIcons().add(new Image(
                "C:\\Users\\frali\\Desktop\\TeamFlow\\TeamFlow\\src\\main\\resources\\com\\tf\\teamflowcode\\logo.png"));
        window.setScene(scene2);
        window.show();
    }

}

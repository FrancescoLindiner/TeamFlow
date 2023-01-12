package main.java.com.tf.teamflowcode.GestioneAccount.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RecuperaPassword {

    @FXML
    private TextField email;

    @FXML
    void buttonConferma(ActionEvent event) {

    }

    @FXML
    void vaiIndietro(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/fxmlLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

package main.java.com.tf.teamflowcode.GestioneAccount.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.RecuperoControl;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RecuperaPassword {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private TextField email;

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        RecuperoControl recuperoControl = new RecuperoControl();
        if (recuperoControl.controllaEmail(email.getText())) {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaRecuperaPassword.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreRecuperaPassword.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    @FXML
    void vaiIndietro(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/fxmlLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SezioneNotifiche {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private ListView<String> listView;

    @FXML
    void buttonAccetta(ActionEvent event) {

    }

    @FXML
    void buttonRifiuta(ActionEvent event) {

    }

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/InterfacciaPrincipaleAmministratore.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Assenza Malattia");
        stage.show();
    }

}

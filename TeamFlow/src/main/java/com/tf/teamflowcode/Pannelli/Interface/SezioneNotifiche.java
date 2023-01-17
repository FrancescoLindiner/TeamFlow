package main.java.com.tf.teamflowcode.Pannelli.Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.Entity.Permesso;
import main.java.com.tf.teamflowcode.Pannelli.Control.ControlSezioneNotifiche;
import javafx.scene.Node;

public class SezioneNotifiche implements Initializable {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private ListView<Permesso> listView;

    ControlSezioneNotifiche controlSezioneNotifiche = new ControlSezioneNotifiche();

    ObservableList<Permesso> permessoSelezionato;
    ObservableList<Permesso> listaPermessi = controlSezioneNotifiche.setNotifiche();

    @FXML
    void buttonAccetta(ActionEvent event) throws IOException {
        permessoSelezionato = listView.getSelectionModel().getSelectedItems();
        // viene invocato il caso d'uso rimpiazza impiegato
        controlSezioneNotifiche.eliminaRichiesta(permessoSelezionato);
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/SezioneNotifiche.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sezione Notifiche");
        stage.show();
    }

    @FXML
    void buttonRifiuta(ActionEvent event) throws IOException {
        permessoSelezionato = listView.getSelectionModel().getSelectedItems();
        controlSezioneNotifiche.eliminaRichiesta(permessoSelezionato);
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/SezioneNotifiche.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sezione Notifiche");
        stage.show();
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
        stage.setTitle("Interfaccia Principale - Amministratore");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listView.setItems(listaPermessi);
    }

}

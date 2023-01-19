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
import main.java.com.tf.teamflowcode.GestionePresenze.Control.RimpiazzaImpiegato;
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

        RimpiazzaImpiegato rimpiazzaImpiegato = new RimpiazzaImpiegato();
        // rimpiazzaImpiegato.rimpiazzaAmministratore(permessoSelezionato.get(0).getData_p());

        boolean isNotte = rimpiazzaImpiegato.controllaNotte(permessoSelezionato.get(0).getData_p(),
                permessoSelezionato.get(0).getP_matricola());

        if (isNotte) {

            rimpiazzaImpiegato.rimpiazzaImpiegatoNotte(permessoSelezionato.get(0).getData_p(),
                    permessoSelezionato.get(0).getP_matricola(), permessoSelezionato.get(0).getNomeECognome());

        } else if (permessoSelezionato.get(0).getOra_fine_turno().equals("-")) {

            rimpiazzaImpiegato.rimpiazzaImpiegatoGiornoIntero(permessoSelezionato.get(0).getData_p(),
                    Integer.toString(permessoSelezionato.get(0).getP_matricola()),
                    permessoSelezionato.get(0).getNomeECognome());

        } else {
            rimpiazzaImpiegato.rimpiazzaImpiegatoOre(permessoSelezionato.get(0).getData_p(),
                    permessoSelezionato.get(0).getOra_inizio_turno(),
                    permessoSelezionato.get(0).getOra_fine_turno(), Integer.toString(permessoSelezionato.get(0).getP_matricola()),
                    permessoSelezionato.get(0).getNomeECognome());
        }

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

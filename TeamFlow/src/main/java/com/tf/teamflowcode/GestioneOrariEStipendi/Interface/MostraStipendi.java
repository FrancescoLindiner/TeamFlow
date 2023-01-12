package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Interface;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Control.ControlStipendi;
import javafx.scene.Node;

public class MostraStipendi implements Initializable{

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private Label labelStipendio;

    @FXML
    private Label labelData;

    public void setInterfacciaStipendio(){
        ControlStipendi c = new ControlStipendi();
        AccountControl accountControl = new AccountControl();
        List<String> lista = c.getStipendio(accountControl.returnMatricola());

        String stipendio = lista.get(0);
        String anno = lista.get(1);
        String mese = lista.get(2);
        
        labelStipendio.setText(stipendio);
        labelData.setText(mese + " - " + anno);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInterfacciaStipendio();
    }
}
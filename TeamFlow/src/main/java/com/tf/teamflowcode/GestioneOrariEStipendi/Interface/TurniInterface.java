package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import javafx.scene.Node;

public class TurniInterface implements Initializable {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private TableView<Turno> tableView;

    @FXML
    private TableColumn<Turno, String> DATA;

    @FXML
    private TableColumn<Turno, String> ORAFINE;

    @FXML
    private TableColumn<Turno, String> ORAINIZIO;

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
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

    public ObservableList<Turno> addRow() {
        ObservableList<Turno> list = FXCollections.observableArrayList();
        list.add(new Turno("11/01/2023", "12:00", "18:00"));
        list.add(new Turno("12/01/2023", "14:00", "20:00"));
        list.add(new Turno("13/01/2023", "15:00", "21:00"));
        list.add(new Turno("15/01/2023", "11:00", "17:00"));
        list.add(new Turno("16/01/2023", "19:00", "00:00"));
        list.add(new Turno("17/01/2023", "14:00", "18:00"));

        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DATA.setCellValueFactory(new PropertyValueFactory<Turno, String>("date"));
        ORAINIZIO.setCellValueFactory(new PropertyValueFactory<Turno, String>("oraInizio"));
        ORAFINE.setCellValueFactory(new PropertyValueFactory<Turno, String>("oraFine"));
        tableView.setItems(addRow());
    }
}
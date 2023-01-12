package main.java.com.tf.teamflowcode.GestioneAccount.Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.GestorePassword;
import javafx.scene.Node;

public class ModificaPassword {

    Parent pannelloImpostazioni;
    Stage stage;
    Scene scene;

    @FXML
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private PasswordField password3;

    @FXML
    private TextField passwordText1;

    @FXML
    private TextField passwordText2;

    @FXML
    private TextField passwordText3;

    AccountControl accountControl = new AccountControl();

    @FXML
    void buttonVaiIndietro(ActionEvent event) throws IOException {
        if (accountControl.returnRuolo().equals("Amministratore")) {
            pannelloImpostazioni = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/impostazioniAmministratore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(pannelloImpostazioni, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Impostazioni");
            stage.show();
        } else {
            pannelloImpostazioni = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/impostazioniImpiegato.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(pannelloImpostazioni, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Impostazioni");
            stage.show();
        }
    }

    @FXML
    void showPassword1(ActionEvent event) {
        if (checkBox1.isSelected()) {
            passwordText1.setText(password1.getText());
            passwordText1.setVisible(true);
            password1.setVisible(false);
            return;
        }
        password1.setText(passwordText1.getText());
        password1.setVisible(true);
        passwordText1.setVisible(false);
    }

    @FXML
    void showPassword2(ActionEvent event) {
        if (checkBox2.isSelected()) {
            passwordText2.setText(password2.getText());
            passwordText2.setVisible(true);
            password2.setVisible(false);
            return;
        }
        password2.setText(passwordText1.getText());
        password2.setVisible(true);
        passwordText2.setVisible(false);
    }

    @FXML
    void showPassword3(ActionEvent event) {
        if (checkBox3.isSelected()) {
            passwordText3.setText(password3.getText());
            passwordText3.setVisible(true);
            password3.setVisible(false);
            return;
        }
        password3.setText(passwordText1.getText());
        password3.setVisible(true);
        passwordText3.setVisible(false);
    }

    @FXML
    void buttonConferma(ActionEvent event) throws IOException {
        GestorePassword gestorePassword = new GestorePassword();
        if(gestorePassword.controllaPassword(password1.getText(), password2.getText(), password3.getText(),
                passwordText1.getText(), passwordText2.getText(), passwordText3.getText(), event)){
                    if(gestorePassword.controllaPasswordQuery(password1.getText(), passwordText1.getText(), event)){
                        gestorePassword.modificaPassword(password2.getText(), event);
                    }
                }
    }
}
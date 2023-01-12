package main.java.com.tf.teamflowcode.GestioneAccount.Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;
import javafx.scene.Node;

public class ModuloLogin {

    Parent parent;
    Stage stage;
    Scene scene;

    @FXML
    private TextField passwordText;

    @FXML
    private CheckBox checkBox;

    @FXML
    private PasswordField password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFiled;

    @FXML
    private Button loginButton;

    @FXML
    private Button recuperaPasswordButton;

    @FXML
    void login(ActionEvent event) throws IOException, InterruptedException {
        AccountControl accountControl = new AccountControl();
        boolean isCorrect = accountControl.controllaDatiLogin(textFiled.getText(), password.getText());
        if (!isCorrect || textFiled.getText()=="" || password.getText()=="") {
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/SplashScreenErrore.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
            parent = FXMLLoader.load(getClass()
                    .getResource(
                            "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/splahScreenConfirm.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(parent, 810, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

    }

    @FXML
    void showPassword(ActionEvent event) {
        if (checkBox.isSelected()) {
            passwordText.setText(password.getText());
            passwordText.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(passwordText.getText());
        password.setVisible(true);
        passwordText.setVisible(false);
    }

    @FXML
    void recuperaPassword(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass()
                .getResource(
                        "../../../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/RecuperaPassword.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 810, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Recupera Password");
        stage.show();
    }

    @FXML
    void initialize() {
        assert loginButton != null
                : "fx:id=\"loginButton\" was not injected: check your FXML file 'fxmlLogin.fxml'.";
        assert recuperaPasswordButton != null
                : "fx:id=\"recuperaPasswordButton\" was not injected: check your FXML file 'fxmlLogin.fxml'.";

    }

}

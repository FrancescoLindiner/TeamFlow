package main.java.com.tf.teamflowcode.GestioneAccount.Control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import main.java.com.tf.teamflowcode.GestioneAccount.Interface.ModificaPassword;

public class GestorePassword {

    ModificaPassword modificaPassword = new ModificaPassword();
    AccountControl accountControl = new AccountControl();

    Parent parent;
    Stage stage;
    Scene scene;

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public boolean controllaPassword(String password1, String password2, String password3, String passwordText1,
            String passwordText2, String passwordText3, ActionEvent event) throws IOException {
        if (passwordText1 == "" || passwordText2 == "" || passwordText3 == "") {
            if (!(password2.equals(password3)) || password1.equals(password2) || password1.equals(password3)
                    || !(passwordText2.equals(passwordText3))) {
                if (accountControl.returnRuolo().equals("Amministratore")) {
                    parent = FXMLLoader.load(getClass()
                            .getResource(
                                    "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreModificaPwAmministratore.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(parent, 810, 500);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Errore");
                    stage.show();
                    return false;
                } else {
                    parent = FXMLLoader.load(getClass()
                            .getResource(
                                    "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreModificaPwImpiegato.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(parent, 810, 500);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Errore");
                    stage.show();
                    return false;
                }
            } else {
                return true;
            }
        } else {
            if (!(passwordText2.equals(passwordText3)) || passwordText1.equals(passwordText2)
                    || passwordText1.equals(passwordText3)) {
                if (accountControl.returnRuolo().equals("Amministratore")) {
                    parent = FXMLLoader.load(getClass()
                            .getResource(
                                    "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreModificaPwAmministratore.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(parent, 810, 500);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Errore");
                    stage.show();
                    return false;
                } else {
                    parent = FXMLLoader.load(getClass()
                            .getResource(
                                    "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErroreModificaPwImpiegato.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(parent, 810, 500);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Errore");
                    stage.show();
                    return false;
                }
            } else {
                System.out.println("Password giuste");
                return true;
            }
        }
    }

    public boolean controllaPasswordQuery(String password, String passwordText, ActionEvent event) throws IOException {

        Statement stmt = null;
        Connection conn = null;

        String passwordDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT password FROM dipendente WHERE matricola=" + accountControl.returnMatricola() + ";";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                passwordDB = rs.getString("password");
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (password.equals(passwordDB) || passwordText.equals(passwordDB)) {
            return true;
        } else {
            if (accountControl.returnRuolo().equals("Amministratore")) {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrore2ModificaPwAmministratore.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Errore");
                stage.show();
                return false;
            } else {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloErrore2ModificaPwImpiegato.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Errore");
                stage.show();
                return false;
            }
        }
    }

    public void modificaPassword(String text, ActionEvent event) throws IOException {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "UPDATE dipendente SET password='" + text + "' WHERE matricola="
                    + accountControl.returnMatricola() + ";";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            if (accountControl.returnRuolo().equals("Amministratore")) {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaModificaPwAmministratore.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Password Modificata");
                stage.show();
            } else {
                parent = FXMLLoader.load(getClass()
                        .getResource(
                                "../../../../../../resources/com/tf/teamflowcode/Pannelli/fxml/PannelloConfermaModificaPwImpiegato.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(parent, 810, 500);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Password Modificata");
                stage.show();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

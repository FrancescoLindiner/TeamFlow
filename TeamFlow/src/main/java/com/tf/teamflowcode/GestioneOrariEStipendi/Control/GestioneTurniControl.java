package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.tf.teamflowcode.Entity.Turno;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;

public class GestioneTurniControl {

    AccountControl accountControl = new AccountControl();

    public ObservableList<Turno>  prendiTurni() {
        
        final String DRIVER = "com.mysql.cj.jdbc.Driver";

        Statement stmt = null;
        Connection conn = null;

        String data = "";
        String descrizione = "";
        String ora_inizio = "";
        String ora_fine = "";

        ObservableList<Turno> list = FXCollections.observableArrayList();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT data, descrizione, ora_inizio, ora_fine FROM turno WHERE t_matricola=" + accountControl.returnMatricola() + ";";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data = rs.getString("data");
                descrizione = rs.getString("descrizione");
                ora_inizio = rs.getString("ora_inizio");
                ora_fine = rs.getString("ora_fine");
                list.add(new Turno(data, descrizione, ora_inizio, ora_fine));
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
        return list;
    }
}
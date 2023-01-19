package main.java.com.tf.teamflowcode.Pannelli.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.tf.teamflowcode.Entity.Permesso;

public class ControlSezioneNotifiche {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public ObservableList<Permesso> setNotifiche() {
        Statement stmt = null;
        Connection conn = null;

        ObservableList<Permesso> listPermessi = FXCollections.observableArrayList();

        int matricola = 0;
        int id_permesso = 0;
        String data_p = "";
        String ora_inizio_turno = "";
        String ora_fine_turno = "";
        String motivazione = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT id_permesso, p_matricola, data_p, ora_inizio_turno, ora_fine_turno, motivazione FROM permesso";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricola = rs.getInt("p_matricola");
                String nomeECognome = getNomeDipendente(matricola);
                id_permesso = rs.getInt("id_permesso");
                data_p = rs.getString("data_p");
                ora_inizio_turno = rs.getString("ora_inizio_turno");
                ora_fine_turno = rs.getString("ora_fine_turno");
                motivazione = rs.getString("motivazione");
                listPermessi.add(new Permesso(id_permesso, matricola, data_p, ora_inizio_turno, ora_fine_turno,
                        motivazione, nomeECognome));
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
        return listPermessi;
    }

    private String getNomeDipendente(int matricola) {
        Statement stmt = null;
        Connection conn = null;

        String nome = "";
        String cognome = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT nome, cognome FROM dipendente WHERE matricola=" + matricola + ";";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                nome = rs.getString("nome");
                cognome = rs.getString("cognome");
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
        return nome + " " + cognome;
    }

    public void eliminaRichiesta(ObservableList<Permesso> permessoSelezionato) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "DELETE FROM permesso WHERE id_permesso=" + permessoSelezionato.get(0).getId_permesso() + ";";

            System.out.println("Deleting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

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
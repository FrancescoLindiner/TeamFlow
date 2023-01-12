package main.java.com.tf.teamflowcode.GestioneAccount.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import main.java.com.tf.teamflowcode.Entity.Dipendente;

public class AccountControl {
    final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private String emailDB = "";
    private String passowrdDB = "";
    static Dipendente dipendente;

    public void setEmailDB(String emailDB) {
        this.emailDB = emailDB;
    }

    public void setPassowrdDB(String passowrdDB) {
        this.passowrdDB = passowrdDB;
    }

    public String getEmailDB() {
        return emailDB;
    }

    public String getPassowrdDB() {
        return passowrdDB;
    }

    public boolean controllaDatiLogin(String email, String password) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT email, password FROM dipendente WHERE email='" + email + "' AND password='" + password
                    + "';";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                emailDB = rs.getNString("email");
                passowrdDB = rs.getNString("password");
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
        if (emailDB.equals(email) && passowrdDB.equals(password)) {
            dipendente = getInformazioniDipendente(this.emailDB, this.passowrdDB);
            return true;
        } else {
            return false;
        }
    }
    
    public String returnRuolo(){
        return dipendente.getTipologia();
    }

    public String returnNome(){
        return dipendente.getNome();
    }

    public String returnMatricola(){
        return dipendente.getMatricola();
    }

    public String returnCognome(){
        return dipendente.getCognome();
    }

    public Dipendente getInformazioniDipendente(String email, String password) {
        Statement stmt = null;
        Connection conn = null;
        String matricolaDB = "";
        String nomeDB = "";
        String cognomeDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT matricola, nome, cognome, email, tipologia FROM dipendente WHERE email='" + email + "' AND password='"
                    + password
                    + "';";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getNString("tipologia");
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
        return new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB);
    }

}
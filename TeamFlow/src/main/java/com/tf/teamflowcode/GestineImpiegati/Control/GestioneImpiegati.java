package main.java.com.tf.teamflowcode.GestineImpiegati.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import main.java.com.tf.teamflowcode.Entity.Dipendente;
import main.java.com.tf.teamflowcode.GestineImpiegati.Interface.RicercaImpiegati;

public class GestioneImpiegati {

    public static List<Dipendente> list = new ArrayList<>();

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    RicercaImpiegati ricercaImpiegati = new RicercaImpiegati();

    public void cercaImpiegato(String nome, String cognome, String matricola) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE nome='" + nome
                    + "' AND cognome='" + cognome + "' AND matricola='" + matricola + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void cercaImpiegatoPerCognomeEMatricola(String cognome, String matricola) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE cognome='" + cognome + "' AND matricola='" + matricola + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void cercaImpiegatoPerNomeEMatricola(String nome, String matricola) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE nome='" + nome + "' AND matricola='" + matricola + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void cercaImpiegatoPerNomeECognome(String nome, String cognome) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE nome='" + nome + "' AND cognome='" + cognome + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void cercaImpiegatoPerMatricola(String matricola) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE matricola='" + matricola + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void cercaImpiegatoPerNome(String nome) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE nome='" + nome + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void cercaImpiegatoPerCognome(String cognome) {
        Statement stmt = null;
        Connection conn = null;

        String nomeDB = "";
        String cognomeDB = "";
        String matricolaDB = "";
        String emailDB = "";
        String tipologiaDB = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT * FROM dipendente WHERE cognome='" + cognome + "';";

            System.out.println("Cheching record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricolaDB = rs.getString("matricola");
                nomeDB = rs.getString("nome");
                cognomeDB = rs.getString("cognome");
                emailDB = rs.getString("email");
                tipologiaDB = rs.getString("tipologia");
                list.add(new Dipendente(matricolaDB, nomeDB, cognomeDB, emailDB, tipologiaDB));
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

    public void modificaImpiegatoEmail(ObservableList<Dipendente> impiegato, String email) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "UPDATE dipendente SET email='" + email + "' WHERE matricola="
                    + impiegato.get(0).getMatricola();

            System.out.println("Updating record into the table...");

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

    public void modificaImpiegatoGrado(ObservableList<Dipendente> impiegato, String grado) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "UPDATE dipendente SET tipologia='" + grado + "' WHERE matricola="
                    + impiegato.get(0).getMatricola();

            System.out.println("Updating record into the table...");

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

    public void modificaImpiegato(ObservableList<Dipendente> impiegato, String grado, String email) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "UPDATE dipendente SET tipologia='" + grado + "', email='"
                    + email + "' WHERE matricola=" + impiegato.get(0).getMatricola();

            System.out.println("Updating record into the table...");

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

    public void rimuoviImpiegato(ObservableList<Dipendente> impiegato) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "DELETE FROM dipendente WHERE matricola=" + impiegato.get(0).getMatricola() + ";";

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
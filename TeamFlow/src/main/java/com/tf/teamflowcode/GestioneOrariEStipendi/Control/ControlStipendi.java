package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.tf.teamflowcode.Entity.Dipendente;
import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;

public class ControlStipendi {
    final String DRIVER = "com.mysql.cj.jdbc.Driver";
    AccountControl accountControl = new AccountControl();

    String matricola = accountControl.returnMatricola();

    public List<String> getStipendio(String matricola) {
        Statement stmt = null;
        Connection conn = null;
        double stipendio = 0.0;
        int anno = 0;
        int mese = 0;
        List<String> lista = new ArrayList<>();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT importo, anno_s, mese_s FROM stipendio WHERE s_matricola='" + matricola
                    + "' ORDER BY mese_s DESC, anno_s DESC;";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                stipendio = rs.getDouble("importo");
                anno = rs.getInt("anno_s");
                mese = rs.getInt("mese_s");
            }
            lista.add(Double.toString(stipendio));
            lista.add(Integer.toString(anno));
            lista.add(Integer.toString(mese));

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
        return lista;
    }

    public ObservableList<String> returnListaStipendi() {
        Statement stmt = null;
        Connection conn = null;

        ObservableList<String> listaStipendi = FXCollections.observableArrayList();
        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT importo, anno_s, mese_s, ore_straordinario FROM stipendio WHERE s_matricola="
                    + accountControl.returnMatricola() + ";";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double stipendio = rs.getDouble("importo");
                int anno = rs.getInt("anno_s");
                int mese = rs.getInt("mese_s");
                int ore_straordinario = rs.getInt("ore_straordinario");

                listaStipendi.add(stipendio + " €" + ", Data: " + anno + "-" + mese + ", Ore straordinario: "
                        + ore_straordinario);
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
        return listaStipendi;
    }

    public void generaStipendi() {
        List<Dipendente> listaDipendenti = returnListaDipendenti();

        for (Dipendente dipendente : listaDipendenti) {
            int presenze = prendiPresenze(dipendente.getMatricola());
            int oreStraordinario = prediOreStraordinario(dipendente.getMatricola());

            aggiornaStipendio(dipendente.getTipologia(), presenze, oreStraordinario, dipendente.getMatricola());
        }
    }

    private void aggiornaStipendio(String tipologia, int presenze, int oreStraordinario, String matricola) {
        
        int importoStipendio = 0;

        if (tipologia.equals("Amministratore")) {
            importoStipendio = 2500;
        } else {
            importoStipendio = 2000;
        }
        if (presenze >= 13) {
            importoStipendio += 150;
        }
        importoStipendio += oreStraordinario * 28;

        Statement stmt = null;
        Connection conn = null;

        DateFormat anno = new SimpleDateFormat("yyyy");
        Date anno2 = new Date();
        String dataDiOggiAnno = anno.format(anno2);

        DateFormat mese = new SimpleDateFormat("MM");
        Date mese2 = new Date();
        String dataDiOggimese = mese.format(mese2);

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "UPDATE stipendio SET importo=" + importoStipendio + " WHERE anno_s=" + dataDiOggiAnno
                    + " AND mese_s=" + dataDiOggimese + " AND s_matricola=" + matricola + ";";

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

    private int prediOreStraordinario(String matricola2) {
        Statement stmt = null;
        Connection conn = null;

        DateFormat anno = new SimpleDateFormat("yyyy");
        Date anno2 = new Date();
        String dataDiOggiAnno = anno.format(anno2);

        DateFormat mese = new SimpleDateFormat("MM");
        Date mese2 = new Date();
        String dataDiOggimese = mese.format(mese2);

        int ore = 0;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT ore_straordinario FROM stipendio WHERE s_matricola=" + matricola2 + " AND anno_s='"
                    + dataDiOggiAnno + "' AND mese_s='" + dataDiOggimese + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ore += rs.getInt("ore_straordinario");
            }
            System.out.println("Ore straordinario" + ore);

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
        return ore;
    }

    private int prendiPresenze(String matricola) {
        Statement stmt = null;
        Connection conn = null;

        int ore = 0;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT count(presenza) FROM turno WHERE presenza=" + true + " AND t_matricola=" + matricola
                    + ";";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                ore = rs.getInt("count(presenza)");
            }
            System.out.println(ore);

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
        return ore;
    }

    private List<Dipendente> returnListaDipendenti() {
        Statement stmt = null;
        Connection conn = null;

        List<Dipendente> lista = new ArrayList<>();

        String matricola = "";
        String nome = "";
        String cognome = "";
        String email = "";
        String tipologia = "";

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT matricola, nome, cognome, email, tipologia FROM dipendente";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricola = rs.getString("matricola");
                nome = rs.getString("nome");
                cognome = rs.getString("cognome");
                email = rs.getString("email");
                tipologia = rs.getString("tipologia");

                lista.add(new Dipendente(matricola, nome, cognome, email, tipologia));
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
        return lista;
    }
}
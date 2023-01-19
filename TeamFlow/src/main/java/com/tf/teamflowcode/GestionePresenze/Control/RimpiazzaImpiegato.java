package main.java.com.tf.teamflowcode.GestionePresenze.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.com.tf.teamflowcode.Entity.Dipendente;

public class RimpiazzaImpiegato {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public void rimpiazzaImpiegatoGiornoIntero(String data, String matricola, String nomeECognome) {
        Dipendente dipendente;

        String giorno = data.substring(8, 10);
        int giornoInt = Integer.parseInt(giorno);
        int giornoPrecedente = giornoInt - 1;
        String stringGiornoPrecedente = Integer.toString(giornoPrecedente);
        String dataPrecedente = data.replace(giorno, stringGiornoPrecedente);

        if (dataPrecedente.equals("2023-01-01")) {
            dataPrecedente = "2022-12-31";
        } else if (dataPrecedente.equals("2023-02-01")) {
            dataPrecedente = "2023-01-31";
        } else if (dataPrecedente.equals("2023-03-01")) {
            dataPrecedente = "2023-02-27";
        } else if (dataPrecedente.equals("2023-04-01")) {
            dataPrecedente = "2023-03-31";
        } else if (dataPrecedente.equals("2023-05-01")) {
            dataPrecedente = "2023-04-30";
        } else if (dataPrecedente.equals("2023-06-01")) {
            dataPrecedente = "2023-05-31";
        } else if (dataPrecedente.equals("2023-07-01")) {
            dataPrecedente = "2023-06-30";
        } else if (dataPrecedente.equals("2023-08-01")) {
            dataPrecedente = "2023-07-31";
        } else if (dataPrecedente.equals("2023-09-01")) {
            dataPrecedente = "2023-08-31";
        } else if (dataPrecedente.equals("2023-10-01")) {
            dataPrecedente = "2023-09-30";
        } else if (dataPrecedente.equals("2023-11-01")) {
            dataPrecedente = "2023-10-31";
        } else if (dataPrecedente.equals("2023-12-01")) {
            dataPrecedente = "2023-11-30";
        }
        String matricolaImpiegato = "";
        String ora_inizio_turno_mattina = prendiOraInizio(matricola, "mattina");
        String ora_fine_turno_mattina = prendiOraFine(matricola, "mattina");
        String ora_inizio_turno_pomeriggio = prendiOraInizio(matricola, "pomeriggio");
        String ora_fine_turno_pomeriggio = prendiOraFine(matricola, "pomeriggio");
        try {
            do {
                dipendente = prendiImpiegato(getRuolo(matricola), nomeECognome);
                matricolaImpiegato = dipendente.getMatricola();
            } while (!controllaGiornoLibero(data, matricolaImpiegato).equals("libero")
                    && controllaNotte(dataPrecedente, Integer.parseInt(matricolaImpiegato)));
        } catch (NullPointerException e) {
            dipendente = prendiImpiegato(getRuolo(matricola), nomeECognome);
            assegnaStraordinarioMattina(ora_inizio_turno_mattina, ora_fine_turno_mattina, data,
                    dipendente.getMatricola(), matricola);
            dipendente = prendiAltroImpiegato(getRuolo(matricola), nomeECognome, dipendente.getNome(),
                    dipendente.getCognome());
            assegneStraordinarioPomeriggio(ora_inizio_turno_pomeriggio, ora_fine_turno_pomeriggio, data,
                    dipendente.getMatricola());
            return;
        }

        if (ora_inizio_turno_mattina.equals(null)) {
            System.out.println("GIORNO LIBERO");
            return;
        }

        aggiornaOrari(dipendente.getMatricola(), data, ora_inizio_turno_mattina, ora_fine_turno_mattina,
                ora_inizio_turno_pomeriggio, ora_fine_turno_pomeriggio);

        String giornoLibero = prendiGiornoLibero(matricola, data);

        ora_inizio_turno_mattina = prendiOraInizio(dipendente.getMatricola(), "mattina");
        ora_fine_turno_mattina = prendiOraFine(dipendente.getMatricola(), "mattina");
        ora_inizio_turno_pomeriggio = prendiOraInizio(dipendente.getMatricola(), "pomeriggio");
        ora_fine_turno_pomeriggio = prendiOraFine(dipendente.getMatricola(), "pomeriggio");

        aggiornaOrari2(matricola, giornoLibero, ora_inizio_turno_mattina,
                ora_fine_turno_mattina,
                ora_inizio_turno_pomeriggio, ora_fine_turno_pomeriggio);
    }

    private Dipendente prendiAltroImpiegato(String ruolo, String nomeECognome, String nome, String cognome) {
        Statement stmt = null;
        Connection conn = null;

        String[] nomeECognomeArray = nomeECognome.split(" ");
        String nomeImpiegato = nomeECognomeArray[0];
        String cognomeImpiegato = nomeECognomeArray[1];

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT matricola, nome, cognome, email, tipologia FROM dipendente WHERE tipologia!='Amministratore' AND tipologia<='"
                    + ruolo + "'AND nome!='" + nomeImpiegato + "' AND cognome!='" + cognomeImpiegato
                    + "' AND nome!='" + nome + "' AND cognome!='" + cognome + "' ORDER BY RAND() LIMIT 1;";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String matricola = rs.getString("matricola");
                String nome2 = rs.getString("nome");
                String cognome2 = rs.getString("cognome");
                String email = rs.getString("email");
                String tipologia = rs.getString("tipologia");
                return new Dipendente(matricola, nome2, cognome2, email, tipologia);
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
        return null;
    }

    private void assegneStraordinarioPomeriggio(String ora_inizio_turno_pomeriggio, String ora_fine_turno_pomeriggio,
            String data, String matricola) {
        Statement stmt = null;
        Connection conn = null;

        int ora_inizio = Integer.parseInt(ora_inizio_turno_pomeriggio.substring(0, 2));
        int ora_fine = Integer.parseInt(ora_fine_turno_pomeriggio.substring(0, 2));
        int ore_straordinario = ora_fine - ora_inizio;
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data + "', 'mattina', '" + matricola + "', '" + ora_inizio_turno_pomeriggio
                    + "', '"
                    + ora_fine_turno_pomeriggio + "', " + false + ", " + false + ", " + false + ");";

            DateFormat anno = new SimpleDateFormat("yyyy");
            Date anno2 = new Date();
            String dataDiOggiAnno = anno.format(anno2);

            DateFormat mese = new SimpleDateFormat("MM");
            Date mese2 = new Date();
            String dataDiOggimese = mese.format(mese2);

            String sql2 = "UPDATE stipendio SET ore_straordinario = ore_straordinario + " + ore_straordinario
                    + " where s_matricola='" + matricola + "' AND anno_s='" + dataDiOggiAnno + "' AND mese_s='"
                    + dataDiOggimese + "';";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql1);

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

    private void assegnaStraordinarioMattina(String ora_inizio_turno_mattina, String ora_fine_turno_mattina,
            String data, String matricola, String matricolaImpiegatoPermesso) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data + "', 'mattina', '" + matricola + "', '" + ora_inizio_turno_mattina
                    + "', '"
                    + ora_fine_turno_mattina + "', " + false + ", " + false + ", " + false + ");";

            int ora_inizio = Integer.parseInt(ora_inizio_turno_mattina.substring(0, 2));
            int ora_fine = Integer.parseInt(ora_fine_turno_mattina.substring(0, 2));
            int ore_straordinario = ora_fine - ora_inizio;

            DateFormat anno = new SimpleDateFormat("yyyy");
            Date anno2 = new Date();
            String dataDiOggiAnno = anno.format(anno2);

            DateFormat mese = new SimpleDateFormat("MM");
            Date mese2 = new Date();
            String dataDiOggimese = mese.format(mese2);

            String sql2 = "UPDATE stipendio SET ore_straordinario = ore_straordinario + " + ore_straordinario
                    + " where s_matricola='" + matricola + "' AND anno_s='" + dataDiOggiAnno + "' AND mese_s='"
                    + dataDiOggimese + "';";

            String sql3 = "DELETE FROM turno WHERE t_matricola='" + matricolaImpiegatoPermesso + "' AND data='"
                    + data + "';";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql3);

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

    public String prendiGiornoLibero(String matricola, String data_p) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "select data from turno where data>'" + data_p
                    + "' AND descrizione='libero' AND t_matricola='"
                    + matricola + "';";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql1);

            if (rs.next()) {
                String data = rs.getString("data");
                return data;
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
        return null;
    }

    private void aggiornaOrari2(String matricolaImpiegatoSelezionato, String data,
            String ora_inizio_turno_mattina, String ora_fine_turno_mattina, String ora_inizio_turno_pomeriggio,
            String ora_fine_turno_pomeriggio) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data + "', 'mattina', '" + matricolaImpiegatoSelezionato + "', '" + ora_inizio_turno_mattina
                    + "', '"
                    + ora_fine_turno_mattina + "', " + false + ", " + false + ", " + false + ");";

            String sql3 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data + "', 'pomeriggio', '" + matricolaImpiegatoSelezionato + "', '" + ora_inizio_turno_pomeriggio
                    + "', '"
                    + ora_fine_turno_pomeriggio + "', " + false + ", " + false + ", " + false + ");";

            String sql2 = "DELETE FROM turno WHERE t_matricola='" + matricolaImpiegatoSelezionato + "' AND data='"
                    + data + "';";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql3);

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

    private void aggiornaOrari(String matricolaImpiegatoSelezionato, String data,
            String ora_inizio_turno_mattina, String ora_fine_turno_mattina, String ora_inizio_turno_pomeriggio,
            String ora_fine_turno_pomeriggio) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data + "', 'mattina', '" + matricolaImpiegatoSelezionato + "', '" + ora_inizio_turno_mattina
                    + "', '"
                    + ora_fine_turno_mattina + "', " + false + ", " + false + ", " + false + ");";

            String sql3 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data + "', 'pomeriggio', '" + matricolaImpiegatoSelezionato + "', '" + ora_inizio_turno_pomeriggio
                    + "', '"
                    + ora_fine_turno_pomeriggio + "', " + false + ", " + false + ", " + false + ");";

            String sql2 = "DELETE FROM turno WHERE t_matricola='" + matricolaImpiegatoSelezionato + "' AND data='"
                    + data + "' AND descrizione='libero'";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql3);

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

    public void rimpiazzaImpiegatoOre(String data, String oraInizio, String oraFine, String matricola,
            String nomeECognome) {
        Dipendente dipendente = prendiImpiegato(getRuolo(matricola), nomeECognome);

        int ora_inizio = Integer.parseInt(oraInizio.substring(0, 2));
        int ora_fine = Integer.parseInt(oraFine.substring(0, 2));

        if (ora_inizio >= 5 && ora_fine <= 15) {
            assegnaStraordinarioMattina(oraInizio, oraFine, data,
                    dipendente.getMatricola(), matricola);
        } else {
            assegneStraordinarioPomeriggio(oraInizio, nomeECognome, data, matricola);
        }

    }

    public boolean controllaNotte(String data_p, int p_matricola) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT descrizione FROM turno WHERE data='" + data_p + "' AND t_matricola=" + p_matricola
                    + ";";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("descrizione").equals("notte")) {
                    return true;
                }
            }
            return false;

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
        return false;
    }

    public void rimpiazzaImpiegatoNotte(String data_p, int matricola, String nomeECognome) {
        // selezionare un impiegato

        String matricolaString = Integer.toString(matricola);

        String ruolo = getRuolo(matricolaString);
        Dipendente dipendenteSelezionato = prendiImpiegato(ruolo, nomeECognome);
        while (matricolaString.equals(dipendenteSelezionato.getMatricola())) {
            dipendenteSelezionato = prendiImpiegato(ruolo, nomeECognome);
        }
        // prendere le ore "normali" di questo impiegato
        String ora_inizio_turno_mattina = prendiOraInizio(dipendenteSelezionato.getMatricola(), "mattina");
        String ora_fine_turno_mattina = prendiOraFine(dipendenteSelezionato.getMatricola(), "mattina");
        String ora_inizio_turno_pomeriggio = prendiOraInizio(dipendenteSelezionato.getMatricola(), "pomeriggio");
        String ora_fine_turno_pomeriggio = prendiOraFine(dipendenteSelezionato.getMatricola(), "pomeriggio");

        if (ora_inizio_turno_mattina.equals(null)) {
            System.out.println("GIORNO LIBERO");
            return;
        }

        aggiornaTurnoNotteImpiegatoSelezionato(dipendenteSelezionato.getMatricola(), data_p); // mette la notte e il
                                                                                              // giorno successivo ed
                                                                                              // elimina i turni vecchi
        String dataNotte = prendiGiornoNotte(dipendenteSelezionato.getMatricola(), data_p);
        assegnaTurniNuovi(ora_inizio_turno_mattina, ora_fine_turno_mattina, ora_inizio_turno_pomeriggio,
                ora_fine_turno_pomeriggio, dataNotte, dipendenteSelezionato.getMatricola()); // mette i nuovi turni
                                                                                             // eliminando il turno di
        // notte vecchio

        ora_inizio_turno_mattina = prendiOraInizio(matricolaString, "mattina");
        ora_fine_turno_mattina = prendiOraFine(matricolaString, "mattina");
        ora_inizio_turno_pomeriggio = prendiOraInizio(matricolaString, "pomeriggio");
        ora_fine_turno_pomeriggio = prendiOraFine(matricolaString, "pomeriggio");
        assegnaTurniNuovi(ora_inizio_turno_mattina, ora_fine_turno_mattina, ora_inizio_turno_pomeriggio,
                ora_fine_turno_pomeriggio, data_p, matricolaString); // mette i turni nuovi ed
                                                                     // elimina il turno di notte
        aggiornaTurnoNotteImpiegatoSelezionato(matricolaString, dataNotte); // mette la notte al vecchio impiegato

    }

    private String getRuolo(String matricolaString) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT tipologia FROM dipendente WHERE matricola='" + matricolaString + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String ruolo = rs.getString("tipologia");
                return ruolo;
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
        return null;
    }

    private void assegnaTurniNuovi(String ora_inizio_turno_mattina, String ora_fine_turno_mattina,
            String ora_inizio_turno_pomeriggio, String ora_fine_turno_pomeriggio, String dataNotte, String matricola) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "DELETE FROM turno WHERE data='" + dataNotte + "' AND t_matricola='" + matricola + "';";

            String sql2 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + dataNotte + "', 'mattina', '" + matricola + "', '" + ora_inizio_turno_mattina + "', '"
                    + ora_fine_turno_mattina + "', " + false + ", " + false + ", " + false + ");";

            String sql3 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + dataNotte + "', 'pomeriggio', '" + matricola + "', '" + ora_inizio_turno_pomeriggio + "', '"
                    + ora_fine_turno_pomeriggio + "', " + false + ", " + false + ", " + false + ");";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);

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

    private String prendiGiornoNotte(String matricola, String data_p) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql1 = "select data from turno where data>'" + data_p + "' AND descrizione='notte' AND t_matricola='"
                    + matricola + "';";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql1);

            if (rs.next()) {
                String data = rs.getString("data");
                return data;
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
        return null;
    }

    private void aggiornaTurnoNotteImpiegatoSelezionato(String matricola, String data_p) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            if (controllaGiornoLibero(data_p, matricola).equals("libero")) {
                String sql1 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                        + data_p + "', 'notte', '" + matricola + "', '22:00:00', '05:00:00', " + false + ", " + false
                        + ", " + false + ");";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql1);
            } else {
                String sql1 = "UPDATE turno SET ora_inizio='22:00:00', ora_fine='05:00:00', descrizione='notte' WHERE t_matricola='"
                        + matricola
                        + "' AND data='" + data_p + "';";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql1);
            }

            String giorno = data_p.substring(8, 10);
            int giornoInt = Integer.parseInt(giorno);
            int giornoSuccessivo = giornoInt + 1;
            String stringGiornoSuccessivo = Integer.toString(giornoSuccessivo);
            String dataSuccessiva = data_p.replace(giorno, stringGiornoSuccessivo);

            if (dataSuccessiva.equals("2023-01-32")) {
                dataSuccessiva = "2023-02-01";
            } else if (dataSuccessiva.equals("2023-02-28")) {
                dataSuccessiva = "2023-03-01";
            } else if (dataSuccessiva.equals("2023-03-32")) {
                dataSuccessiva = "2023-04-01";
            } else if (dataSuccessiva.equals("2023-04-31")) {
                dataSuccessiva = "2023-05-01";
            } else if (dataSuccessiva.equals("2023-05-32")) {
                dataSuccessiva = "2023-06-01";
            } else if (dataSuccessiva.equals("2023-06-31")) {
                dataSuccessiva = "2023-07-01";
            } else if (dataSuccessiva.equals("2023-07-32")) {
                dataSuccessiva = "2023-08-01";
            } else if (dataSuccessiva.equals("2023-08-32")) {
                dataSuccessiva = "2023-09-01";
            } else if (dataSuccessiva.equals("2023-09-31")) {
                dataSuccessiva = "2023-10-01";
            } else if (dataSuccessiva.equals("2023-10-32")) {
                dataSuccessiva = "2023-11-01";
            } else if (dataSuccessiva.equals("2023-11-31")) {
                dataSuccessiva = "2023-12-01";
            } else if (dataSuccessiva.equals("2023-12-31")) {
                dataSuccessiva = "2023-01-01";
            }

            String sql2 = "DELETE FROM turno WHERE data='" + dataSuccessiva + "' AND t_matricola=" + matricola + ";";

            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);

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

    private String controllaGiornoLibero(String data_p, String matricola) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT descrizione FROM turno WHERE data='" + data_p + "' AND t_matricola='" + matricola
                    + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                if (rs.getString("descrizione").equals("libero")) {
                    return "libero";
                }
            } else {
                return null;
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
        return null;
    }

    private String prendiOraFine(String matricola, String descrizione) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT ora_fine FROM turno WHERE t_matricola='" + matricola + "' AND descrizione='"
                    + descrizione + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String ora_fine = rs.getString("ora_fine");
                return ora_fine;
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
        return null;
    }

    private String prendiOraInizio(String matricola, String descrizione) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT ora_inizio FROM turno WHERE t_matricola='" + matricola + "' AND descrizione='"
                    + descrizione + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String ora_inizio = rs.getString("ora_inizio");
                return ora_inizio;
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
        return null;
    }

    private Dipendente prendiImpiegato(String ruolo, String nomeECognome) {
        Statement stmt = null;
        Connection conn = null;

        String[] nomeECognomeArray = nomeECognome.split(" ");
        String nomeImpiegato = nomeECognomeArray[0];
        String cognomeImpiegato = nomeECognomeArray[1];

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT matricola, nome, cognome, email, tipologia FROM dipendente WHERE tipologia!='Amministratore' AND tipologia<='"
                    + ruolo + "'AND nome!='" + nomeImpiegato + "' AND cognome!='" + cognomeImpiegato
                    + "' ORDER BY RAND() LIMIT 1;";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String matricola = rs.getString("matricola");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String email = rs.getString("email");
                String tipologia = rs.getString("tipologia");
                return new Dipendente(matricola, nome, cognome, email, tipologia);
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
        return null;
    }

}

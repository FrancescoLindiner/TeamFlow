package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.com.tf.teamflowcode.Entity.Dipendente;

public class GeneraOrarioControl {

    String ora_inizioMattina = "08:00";
    String ora_fineMattina = "12:00";

    String ora_inizio2Mattina = "12:00";
    String ora_fine2Mattina = "15:00";

    String ora_inizioPome = "15:00";
    String ora_finePome = "18:00";

    String ora_inizio2Pome = "18:00";
    String ora_fine2Pome = "22:00";

    String oraNotteInizio = "22:00";
    String oraNotteFine = "06:00";

    String ora_inizio3Mattina = "05:00";
    String ora_fine3Mattina = "08:00";

    String ora_inizio3Pome = "15:00";
    String ora_fine3Pome = "18:00";

    boolean notte = false;

    public void generaOrari(int counterGiorni) {
        List<Dipendente> list = prendiImpiegati();
        for (Dipendente dipendente : list) {
            if (numeroRandom() == 1) {
                int counter = 0;
                while (counter < counterGiorni) {
                    if (counter % 7 == 0) {
                        caricaTurnoNotturno(dipendente.getMatricola(), getData(counter), oraNotteInizio, oraNotteFine);
                        counter += 2;
                    } else if (counter % 7 == 3) {
                        caricaITurni1(dipendente.getMatricola(), getData(counter), ora_inizioMattina, ora_fineMattina,
                                ora_inizioPome, ora_finePome);
                        counter++;
                        caricaGiornoLibero(dipendente.getMatricola(), getData(counter));
                        counter++;
                    } else {
                        caricaITurni1(dipendente.getMatricola(), getData(counter), ora_inizioMattina, ora_fineMattina,
                                ora_inizioPome, ora_finePome);
                        counter++;
                    }
                }
            } else if (numeroRandom() == 2) {
                int counter = 0;
                while (counter < counterGiorni) {
                    if (counter % 7 == 3) {
                        caricaTurnoNotturno(dipendente.getMatricola(), getData(counter), oraNotteInizio, oraNotteFine);
                        counter += 2;
                    } else if (counter % 7 == 5) {
                        caricaITurni2(dipendente.getMatricola(), getData(counter), ora_inizio2Mattina, ora_fine2Mattina,
                                ora_inizio2Pome, ora_fine2Pome);
                        counter++;
                        caricaGiornoLibero(dipendente.getMatricola(), getData(counter));
                        counter++;
                    } else {
                        caricaITurni2(dipendente.getMatricola(), getData(counter), ora_inizio2Mattina, ora_fine2Mattina,
                                ora_inizio2Pome, ora_fine2Pome);
                        counter++;
                    }
                }
            } else {
                int counter = 0;
                while (counter < counterGiorni) {
                    if (counter % 7 == 4) {
                        caricaTurnoNotturno(dipendente.getMatricola(), getData(counter), oraNotteInizio, oraNotteFine);
                        counter += 2;
                    } else if (counter % 7 == 2) {
                        caricaITurni3(dipendente.getMatricola(), getData(counter), ora_inizio3Mattina, ora_fine3Mattina,
                                ora_inizio3Pome, ora_fine3Pome);
                        counter++;
                        caricaGiornoLibero(dipendente.getMatricola(), getData(counter));
                        counter++;
                    } else {
                        caricaITurni3(dipendente.getMatricola(), getData(counter), ora_inizio3Mattina, ora_fine3Mattina,
                                ora_inizio3Pome, ora_fine3Pome);
                        counter++;
                    }
                }
            }
        }
    }

    private void caricaGiornoLibero(String matricola, String data) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'libero', '" + matricola + "', '-', '-', " + false
                    + ", " + false + ", " + false + ");";

            System.out.println("Inserting record into the table...");

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

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private void caricaTurnoNotturno(String matricola, String data, String oraNotteInizio2, String oraNotteFine2) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'notte', '" + matricola + "', '" + oraNotteInizio2 + "', '" + oraNotteFine2 + "', " + false
                    + ", " + false + ", " + false + ");";

            System.out.println("Inserting record into the table...");

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

    private void caricaITurni1(String matricola, String data, String ora_inizioMattina2, String ora_fineMattina2,
            String ora_inizioPome2, String ora_finePome2) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'mattina', '" + matricola + "', '" + ora_inizioMattina2 + "', '" + ora_fineMattina2 + "', "
                    + false + ", " + false + ", " + false + ");";
            ;

            String sql2 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'pomeriggio', '" + matricola + "', '" + ora_inizioPome2 + "', '" + ora_finePome2 + "', "
                    + false + ", " + false + ", " + false + ");";

            System.out.println("Inserting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
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

    private void caricaITurni2(String matricola, String data, String ora_inizioMattina2, String ora_fineMattina2,
            String ora_inizioPome2, String ora_finePome2) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'mattina', '" + matricola + "', '" + ora_inizioMattina2 + "', '" + ora_fineMattina2 + "', "
                    + false + ", " + false + ", " + false + ");";

            String sql2 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'pomeriggio', '" + matricola + "', '" + ora_inizioPome2 + "', '" + ora_finePome2 + "', "
                    + false + ", " + false + ", " + false + ");";

            System.out.println("Inserting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
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

    private void caricaITurni3(String matricola, String data, String ora_inizioMattina2, String ora_fineMattina2,
            String ora_inizioPome2, String ora_finePome2) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'mattina', '" + matricola + "', '" + ora_inizioMattina2 + "', '" + ora_fineMattina2 + "', "
                    + false + ", " + false + ", " + false + ");";

            String sql2 = "INSERT INTO turno (data, descrizione, t_matricola, ora_inizio, ora_fine, presenza, firma_ingresso, firma_uscita) VALUES ('"
                    + data
                    + "', 'pomeriggio', '" + matricola + "', '" + ora_inizioPome2 + "', '" + ora_finePome2 + "', "
                    + false + ", " + false + ", " + false + ");";

            System.out.println("Inserting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
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

    private String getData(int giorno) {
        String s;
        Date date;
        Format formatter;
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, giorno);
        date = calendar.getTime();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        s = formatter.format(date);
        return s;
    }

    public int numeroRandom() {
        Random rand = new Random();
        return rand.nextInt(3) + 1;
    }

    public List<Dipendente> prendiImpiegati() {
        final String DRIVER = "com.mysql.cj.jdbc.Driver";

        Statement stmt = null;
        Connection conn = null;

        String matricola = "";
        String nome = "";
        String cognome = "";
        String email = "";
        String tipologia = "";

        List<Dipendente> list = new ArrayList<>();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT matricola, nome, cognome, email, tipologia FROM dipendente;";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                matricola = rs.getString("matricola");
                nome = rs.getString("nome");
                cognome = rs.getString("cognome");
                email = rs.getString("email");
                tipologia = rs.getString("tipologia");
                list.add(new Dipendente(matricola, nome, cognome, email, tipologia));
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

    public void eliminaOrari() {
        final String DRIVER = "com.mysql.cj.jdbc.Driver";

        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "DELETE FROM turno";

            System.out.println("Deleting all record of the table...");

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

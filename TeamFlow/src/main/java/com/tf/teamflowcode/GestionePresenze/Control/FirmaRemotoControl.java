package main.java.com.tf.teamflowcode.GestionePresenze.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;

public class FirmaRemotoControl {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public boolean controllaFirma() {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dataDiOggi = data.format(date);

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");
            String oraInizio = prediOraInizio();
            String oraFine = prediOraFine();
            if (oraInizio==null || oraFine==null) {
                return false;
            }
            String sql = "SELECT presenza FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                    + " AND data='" + dataDiOggi + "' AND ora_inizio='" + oraInizio + "' AND ora_fine='"
                    + oraFine + "';";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();
            stmt.executeQuery(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("presenza").equals("1")) {
                    return false;
                }

            }
            return true;

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

    public boolean aggiornaPresenza() {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        DateFormat data2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dataDiOggi = data2.format(date);

        DateFormat ora = new SimpleDateFormat("HH");
        Date ora2 = new Date();
        String oraDiOggi = ora.format(ora2);
        int oraInteger = Integer.parseInt(oraDiOggi);

        DateFormat minuti = new SimpleDateFormat("mm");
        Date minutiDate = new Date();
        String minutiDiOggi = minuti.format(minutiDate);
        int minutiInteger = Integer.parseInt(minutiDiOggi);

        boolean isModificato = false;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");
            String sql = "";
            if (oraInteger >= 5 && oraInteger < 15) {
                sql = "UPDATE turno SET presenza=" + true + ", firma_ingresso= " + true + " WHERE (t_matricola="
                        + accountControl.returnMatricola()
                        + ") AND (data='" + dataDiOggi + "') AND (descrizione='mattina');";
                isModificato = true;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            } else if (oraInteger >= 15 && oraInteger < 22) {
                sql = "UPDATE turno SET presenza=" + true + ", firma_ingresso= " + true + " WHERE (t_matricola="
                        + accountControl.returnMatricola()
                        + ") AND (data='" + dataDiOggi + "') AND (descrizione='pomeriggio');";
                isModificato = true;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            } else if (oraInteger >= 22 || oraInteger < 5) {
                sql = "UPDATE turno SET presenza=" + true + ", firma_ingresso=" + true + " WHERE (t_matricola="
                        + accountControl.returnMatricola()
                        + ") AND (data='" + dataDiOggi + "') AND (descrizione='notte');";
                isModificato = true;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }

            System.out.println("Inserting record into the table...");

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
        return isModificato;
    }

    private String prediOraFine() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

        Statement stmt = conn.createStatement();

        AccountControl accountControl = new AccountControl();

        DateFormat data2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dataDiOggi = data2.format(date);

        DateFormat ora = new SimpleDateFormat("HH");
        Date ora2 = new Date();
        String oraDiOggi = ora.format(ora2);
        int oraInteger = Integer.parseInt(oraDiOggi);

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");
            String sql = "";
            if (oraInteger >= 5 && oraInteger < 15) {
                sql = "SELECT ora_fine FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                        + " AND data ='" + dataDiOggi + "' AND descrizione='mattina';";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    String oraFine = rs.getString("ora_fine");
                    return oraFine;
                }
            } else if (oraInteger >= 15 && oraInteger < 22) {
                sql = "SELECT ora_fine FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                        + " AND data ='" + dataDiOggi + "' AND descrizione='pomeriggio';";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    String oraFine = rs.getString("ora_fine");
                    return oraFine;
                }
            } else if (oraInteger >= 22) {
                sql = "SELECT ora_fine FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                        + " AND data ='" + dataDiOggi + "' AND descrizione='notte';";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    String oraFine = rs.getString("ora_fine");
                    return oraFine;
                }
            }

            System.out.println("Inserting record into the table...");

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

    private String prediOraInizio() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

        Statement stmt = conn.createStatement();

        AccountControl accountControl = new AccountControl();

        DateFormat data2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dataDiOggi = data2.format(date);

        DateFormat ora = new SimpleDateFormat("HH");
        Date ora2 = new Date();
        String oraDiOggi = ora.format(ora2);
        int oraInteger = Integer.parseInt(oraDiOggi);

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            String sql = "";
            if (oraInteger >= 5 && oraInteger < 15) {
                sql = "SELECT ora_inizio FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                        + " AND data ='" + dataDiOggi + "' AND descrizione='mattina';";
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    String oraInizio = rs.getString("ora_inizio");
                    return oraInizio;
                }
            } else if (oraInteger >= 15 && oraInteger < 22) {
                sql = "SELECT ora_inizio FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                        + " AND data ='" + dataDiOggi + "' AND descrizione='pomeriggio';";
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    String oraInizio = rs.getString("ora_inizio");
                    return oraInizio;
                }
            } else if (oraInteger >= 22) {
                sql = "SELECT ora_inizio FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                        + " AND data ='" + dataDiOggi + "' AND descrizione='notte';";
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    String oraInizio = rs.getString("ora_inizio");
                    return oraInizio;
                }

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
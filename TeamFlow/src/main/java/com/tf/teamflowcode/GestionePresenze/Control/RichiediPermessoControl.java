package main.java.com.tf.teamflowcode.GestionePresenze.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;

public class RichiediPermessoControl {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public boolean controllaTurno(String data) {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT data FROM turno WHERE t_matricola=" + accountControl.returnMatricola();

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            List<String> date = new ArrayList<>();

            while (rs.next()) {
                String dataDB = rs.getString("data");
                date.add(dataDB);
            }
            if (date.contains(data)) {
                return true;
            } else {
                return false;
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
        return false;
    }

    public boolean controllaTurnoEOra(String data, String oraInizio, String oraFine) {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT data, ora_inizio, ora_fine FROM turno WHERE t_matricola="
                    + accountControl.returnMatricola();

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String dataDB = rs.getString("data");
                String oraInizioDB = rs.getString("ora_inizio");
                String oraFineDB = rs.getString("ora_fine");
                if (dataDB.equals(data) && oraInizioDB.substring(0, 5).equals(oraInizio)
                        && oraFineDB.substring(0, 5).equals(oraFine)) {
                    return true;
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
        return false;
    }

    public boolean controlla24HTurno(String data) {
        SimpleDateFormat f = new SimpleDateFormat("MM-dd");
        String fd = f.format(new Date());
        String giornoOggi = fd.substring(3, 5);
        String meseOggi = fd.substring(0, 2);
        int giornoIntegerOggi = Integer.parseInt(giornoOggi);
        int meseIntegerOggi = Integer.parseInt(meseOggi);

        String giornoPermesso = data.substring(8, 10);
        String mesePermesso = data.substring(5, 7);
        int giornoIntegerePermesso = Integer.parseInt(giornoPermesso);
        int meseIntegerPermesso = Integer.parseInt(mesePermesso);

        if (meseIntegerPermesso > meseIntegerOggi) {
            return true;
        } else if (meseIntegerPermesso == meseIntegerOggi) {
            if (giornoIntegerePermesso == giornoIntegerOggi + 1) {
                SimpleDateFormat ora = new SimpleDateFormat("HH");
                String oraDiOggi = ora.format(new Date());
                int oraDiOggiInteger = Integer.parseInt(oraDiOggi);
                String oraInizioTurno = prendiOra(data);
                int oraInizioTurnoInteger = Integer.parseInt(oraInizioTurno.substring(0, 2));
                if (oraDiOggiInteger < oraInizioTurnoInteger) {
                    return true;
                } else {
                    return false;
                }
            } else if (giornoIntegerePermesso > giornoIntegerOggi) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean controlla24HOra(String data, String oraInizio) {
        String dataDaControllare = data.substring(5, 10);
        SimpleDateFormat dataDiOggi = new SimpleDateFormat("MM-dd");
        String dataDiOggiString = dataDiOggi.format(new Date());

        String meseStringDaControllare = dataDaControllare.substring(0, 2);
        String giornoStringDaControllare = dataDaControllare.substring(3, 5);

        int meseIntDaControllare = Integer.parseInt(meseStringDaControllare);
        int giornoIntDaControllare = Integer.parseInt(giornoStringDaControllare);

        String meseDiOggi = dataDiOggiString.substring(0, 2);
        String giornoDiOggi = dataDiOggiString.substring(3, 5);

        int meseDiOggiInt = Integer.parseInt(meseDiOggi);
        int giornoDiOggiInt = Integer.parseInt(giornoDiOggi);

        if (meseIntDaControllare > meseDiOggiInt) {
            return true;
        } else if (meseIntDaControllare == meseDiOggiInt) {
            if (giornoIntDaControllare > giornoDiOggiInt) {
                return true;
            } else if (giornoIntDaControllare == giornoDiOggiInt) {
                String subStringOraTurno = oraInizio.substring(0, 2);
                SimpleDateFormat f = new SimpleDateFormat("HH");
                String fd = f.format(new Date());

                int oraOggi = Integer.parseInt(fd);
                int oraTurnoInteger = Integer.parseInt(subStringOraTurno);

                if (oraOggi < oraTurnoInteger) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String prendiOra(String data) {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();
        List<String> ore = new ArrayList<>();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT ora_inizio FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                    + " AND data='" + data + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            String oraInizio = "";
            if (rs.next()) {
                oraInizio = rs.getString("ora_inizio");
            }
            return oraInizio;

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

    public boolean inserisciPermessoGiornoIntero(String data, String motivazione) {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO permesso (p_matricola, data_p, ora_inizio_turno, ora_fine_turno, motivazione) VALUES ("
                    + accountControl.returnMatricola() + ", '" + data + "', '-', '-', '" + motivazione + "');";

            System.out.println("Inserting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

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

    public boolean inserisciPermessoOre(String data, String oraInizio, String oraFine, String motivazione) {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "INSERT INTO permesso (p_matricola, data_p, ora_inizio_turno, ora_fine_turno, motivazione) VALUES ("
                    + accountControl.returnMatricola() + ", '" + data + "', '" + oraInizio + "', '" + oraFine
                    + "', '" + motivazione + "');";

            System.out.println("Inserting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

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

    public boolean controllaOrari(String data, String orario) {
        Statement stmt = null;
        Connection conn = null;

        String oraInizio = orario.substring(0, 5) + ":00";
        String oraFine = orario.substring(6, 11) + ":00";

        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT ora_inizio, ora_fine FROM turno WHERE t_matricola=" + accountControl.returnMatricola()
                    + " AND data='" + data + "';";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String oraInizioDB = rs.getString("ora_inizio");
                String oraFineDB = rs.getString("ora_fine");
                if (oraInizioDB.equals(oraInizio) && oraFineDB.equals(oraFine)) {
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
}

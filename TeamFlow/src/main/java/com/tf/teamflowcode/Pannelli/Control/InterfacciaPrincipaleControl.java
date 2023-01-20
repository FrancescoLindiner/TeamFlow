package main.java.com.tf.teamflowcode.Pannelli.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;

public class InterfacciaPrincipaleControl {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
    Date data2 = new Date();
    String dataDiOggi = data.format(data2);

    DateFormat ora = new SimpleDateFormat("HH");
    Date ora2 = new Date();
    String oraDiOggi = ora.format(ora2);
    int oraInteger = Integer.parseInt(oraDiOggi);

    AccountControl accountControl = new AccountControl();

    public List<String> getOrari() throws SQLException {
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

            String sql = "SELECT ora_inizio, ora_fine FROM turno WHERE t_matricola='" + accountControl.returnMatricola()
                    + "' AND data='" + dataDiOggi + "';";

            System.out.println("Checking record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            
            List<String> orari = new ArrayList<>();
            String oraInizio = "";
            String oraFine = "";

            while (rs.next()) {
                oraInizio = rs.getString("ora_inizio");
                oraFine = rs.getString("ora_fine");
                orari.add(oraInizio);
                orari.add(oraFine);
            }
            return orari;

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
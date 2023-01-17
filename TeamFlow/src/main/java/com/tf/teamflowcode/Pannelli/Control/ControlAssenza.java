package main.java.com.tf.teamflowcode.Pannelli.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import main.java.com.tf.teamflowcode.GestioneAccount.Control.AccountControl;

public class ControlAssenza {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public boolean verificaTurno(String data) {
        Statement stmt = null;
        Connection conn = null;

        AccountControl accountControl = new AccountControl();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto?", "root", "root");

            String sql = "SELECT id_turno FROM turno WHERE data='" + data + "' AND t_matricola="
                    + accountControl.returnMatricola() + ";";

            System.out.println("Deleting record into the table...");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int id_turno = 0;

            while (rs.next()) {
                id_turno = rs.getInt("id_turno");
            }
            if (id_turno == 0) {
                return false;
            } else {
                return true;
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
        return true;
    }

}
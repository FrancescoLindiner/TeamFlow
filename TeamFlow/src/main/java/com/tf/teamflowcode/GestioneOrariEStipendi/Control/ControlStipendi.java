package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Control;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            String sql = "SELECT importo, anno_s, mese_s FROM stipendio WHERE s_matricola='" + matricola + "' ORDER BY mese_s DESC, anno_s DESC;";

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
}
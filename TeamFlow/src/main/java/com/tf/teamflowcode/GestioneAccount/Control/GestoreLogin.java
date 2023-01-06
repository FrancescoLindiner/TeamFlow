package main.java.com.tf.teamflowcode.GestioneAccount.Control;

import main.java.com.tf.teamflowcode.Entity.Amministratore;

public class GestoreLogin {
    
    public static boolean controllaDati(Amministratore a, String password){
        if(a.getNome().equals("Mario") && password.equals("123"))
            return true;
        else
            return false;
    }

}
package main.java.com.tf.teamflowcode.Entity;

public class Amministratore extends Dipendente{

    private String tipologia;

    public Amministratore(String matricola, String nome, String cognome, String email, String tipologia) {
        super(matricola, nome, cognome, email, tipologia);
        this.tipologia = "Amministratore";
        
    }

}
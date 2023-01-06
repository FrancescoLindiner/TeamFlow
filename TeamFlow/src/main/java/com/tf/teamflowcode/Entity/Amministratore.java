package main.java.com.tf.teamflowcode.Entity;

public class Amministratore {
    private String nome, cognome;

    
    public Amministratore() {
    }

    public Amministratore(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
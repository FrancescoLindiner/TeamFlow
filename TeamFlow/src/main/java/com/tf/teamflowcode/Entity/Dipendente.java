package main.java.com.tf.teamflowcode.Entity;

public class Dipendente {
    private String matricola, nome, cognome, email, tipologia;

    public Dipendente(String matricola, String nome, String cognome, String email, String tipologia) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.tipologia = tipologia;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getTipologia() {
        return tipologia;
    }   

    @Override
    public String toString(){
        return getCognome() + " " + getCognome() + ", " + getMatricola() + ", " + getEmail() + ", " + getTipologia();
    }
}
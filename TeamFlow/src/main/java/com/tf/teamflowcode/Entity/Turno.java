package main.java.com.tf.teamflowcode.Entity;


public class Turno {
    
    private String date;
    private String oraInizio;
    private String oraFine;
    private String descrizione;

    public Turno(String date, String descrizione, String oraInizio, String oraFine) {
        this.date = date;
        this.descrizione = descrizione;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public String getDate() {
        return date;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public String getOraFine() {
        return oraFine;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }



    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }



    public String getDescrizione() {
        return descrizione;
    }

    
}

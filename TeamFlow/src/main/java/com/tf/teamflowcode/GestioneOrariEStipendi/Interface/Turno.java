package main.java.com.tf.teamflowcode.GestioneOrariEStipendi.Interface;


public class Turno {
    
    private String date;
    private String oraInizio;
    private String oraFine;

    public Turno(String date, String oraInizio, String oraFine) {
        this.date = date;
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

    
}

package main.java.com.tf.teamflowcode.Entity;

public class Permesso {

    private int id_permesso, p_matricola;
    private String data_p, ora_inizio_turno, ora_fine_turno, motivazione;
    private String nomeEcognome;

    public Permesso(int id_permesso, int p_matricola, String data_p, String ora_inizio_turno, String ora_fine_turno,
            String motivazione, String nomeEcognome) {
        this.id_permesso = id_permesso;
        this.p_matricola = p_matricola;
        this.data_p = data_p;
        this.ora_inizio_turno = ora_inizio_turno;
        this.ora_fine_turno = ora_fine_turno;
        this.motivazione = motivazione;
        this.nomeEcognome = nomeEcognome;
    }

    public String getNomeECognome() {
        return nomeEcognome;
    }

    public int getId_permesso() {
        return id_permesso;
    }

    public int getP_matricola() {
        return p_matricola;
    }

    public String getData_p() {
        return data_p;
    }

    public String getOra_inizio_turno() {
        return ora_inizio_turno;
    }

    public String getOra_fine_turno() {
        return ora_fine_turno;
    }

    public String getMotivazione() {
        return motivazione;
    }

    @Override
    public String toString() {
        return nomeEcognome + ", matricola: " + getP_matricola() + ", data: " + getData_p() + ",\n ora inizio turno: "
                + getOra_inizio_turno() + ", ora fine turno: " + getOra_fine_turno() + ",\n motivazione: " + getMotivazione();
    }
}
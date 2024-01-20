package modele;

import java.util.Date;

class ListeRendezVous {
    
       private Date dateRdv;

    private int numeroDose;
    private int statutRdv;
    private int nbrDoseTotal;
    private String nomVaccin;
    private String nomCentre;
    private String numeroLot;
    private String libelleStatut;
    private int nbrDoseRestant;


    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }

    public int getNumeroDose() {
        return numeroDose;
    }

    public void setNumeroDose(int numeroDose) {
        this.numeroDose = numeroDose;
    }

    public int getStatutRdv() {
        return statutRdv;
    }

    public void setStatutRdv(int statutRdv) {
        this.statutRdv = statutRdv;
    }

    public int getNbrDoseTotal() {
        return nbrDoseTotal;
    }

    public void setNbrDoseTotal(int nbrDoseTotal) {
        this.nbrDoseTotal = nbrDoseTotal;
    }

    public String getNomVaccin() {
        return nomVaccin;
    }

    public void setNomVaccin(String nomVaccin) {
        this.nomVaccin = nomVaccin;
    }

    public String getNomCentre() {
        return nomCentre;
    }

    public void setNomCentre(String nomCentre) {
        this.nomCentre = nomCentre;
    }

    public String getNumeroLot() {
        return numeroLot;
    }

    public void setNumeroLot(String numeroLot) {
        this.numeroLot = numeroLot;
    }

    public String getLibelleStatut() {
        return libelleStatut;
    }

    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }

    public int getNbrDoseRestant() {
        return nbrDoseRestant;
    }

    public void setNbrDoseRestant(int nbrDoseRestant) {
        this.nbrDoseRestant = nbrDoseRestant;
    }

 
}

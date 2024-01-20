package modele;

public class Vaccin {

    private String nom;
    private int dureeEntreDose;
    private int conditionAdmission;
    private int nbrDoseTotal;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDureeEntreDose() {
        return dureeEntreDose;
    }

    public void setDureeEntreDose(int dureeEntreDose) {
        this.dureeEntreDose = dureeEntreDose;
    }

    public int getConditionAdmission() {
        return conditionAdmission;
    }

    public void setConditionAdmission(int conditionAdmission) {
        this.conditionAdmission = conditionAdmission;
    }

    public int getNbrDoseTotal() {
        return nbrDoseTotal;
    }

    public void setNbrDoseTotal(int nbrDoseTotal) {
        this.nbrDoseTotal = nbrDoseTotal;
    }
}

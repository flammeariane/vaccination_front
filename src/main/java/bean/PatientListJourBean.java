
package bean;

import java.util.Date;
import java.util.List;

public class PatientListJourBean {
    
    private List<listPatient> listPatient;

    public List<listPatient> getListPatient() {
        return listPatient;
    }

    public void setListPatient(List<listPatient> listPatient) {
        this.listPatient = listPatient;
    }
    
    
    public static class listPatient{
    
    private Date dateRdv;
    private String prenom;
    private String nomFamille;
    private String numeroNational;
    private Date dateNaissance;
    private int numeroDose;
    private int statutRdv;
    private String numeroLot;

        public Date getDateRdv() {
            return dateRdv;
        }

        public void setDateRdv(Date dateRdv) {
            this.dateRdv = dateRdv;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getNomFamille() {
            return nomFamille;
        }

        public void setNomFamille(String nomFamille) {
            this.nomFamille = nomFamille;
        }

        public String getNumeroNational() {
            return numeroNational;
        }

        public void setNumeroNational(String numeroNational) {
            this.numeroNational = numeroNational;
        }

        public Date getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(Date dateNaissance) {
            this.dateNaissance = dateNaissance;
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

        public String getNumeroLot() {
            return numeroLot;
        }

        public void setNumeroLot(String numeroLot) {
            this.numeroLot = numeroLot;
        }
    
    
    }
   
    
}

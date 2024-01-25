
package bean;

import java.util.List;

public class VaccinationHistoryBean {
    
      private String numeroNational;
    private int codePostal;
    private String prenom;
    private String nomFamille;
    private String dateNaissance; 
    private String ville;
    private String adresse;
    private String numeroAdresse;
    private String codeSecret;
    private String email;
    private List<RendezVous> listeRendezVous;

  

    public static class RendezVous {
        private long dateRdv;
        private int numeroDose;
        private int statutRdv;
        private int nbrDoseTotal;
        private String nomVaccin;
        private String nomCentre;
        private String numeroLot;
        private String libelleStatut;
        private int nbrDoseRestant;

        public long getDateRdv() {
            return dateRdv;
        }

        public void setDateRdv(long dateRdv) {
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

    public String getNumeroNational() {
        return numeroNational;
    }

    public void setNumeroNational(String numeroNational) {
        this.numeroNational = numeroNational;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroAdresse() {
        return numeroAdresse;
    }

    public void setNumeroAdresse(String numeroAdresse) {
        this.numeroAdresse = numeroAdresse;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RendezVous> getListeRendezVous() {
        return listeRendezVous;
    }

    public void setListeRendezVous(List<RendezVous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }
    
    
}

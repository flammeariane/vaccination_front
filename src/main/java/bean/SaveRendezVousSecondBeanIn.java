package bean;

import java.util.List;

public class SaveRendezVousSecondBeanIn {

    private List<ListRecapInfoRendezVous> listRecapInfoRendezVous;
private String nom;
  private String prenom;
        private String numeroNational;
        private String dateRdv;
        private String nomVaccin;
        private String nomCentre;
        private String numeroDose;
        private String confrmationParEmail;
        private String emailConfirmation;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroNational() {
        return numeroNational;
    }

    public void setNumeroNational(String numeroNational) {
        this.numeroNational = numeroNational;
    }

    public String getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(String dateRdv) {
        this.dateRdv = dateRdv;
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

    public String getNumeroDose() {
        return numeroDose;
    }

    public void setNumeroDose(String numeroDose) {
        this.numeroDose = numeroDose;
    }

    public String getConfrmationParEmail() {
        return confrmationParEmail;
    }

    public void setConfrmationParEmail(String confrmationParEmail) {
        this.confrmationParEmail = confrmationParEmail;
    }

    public String getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(String emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

        
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ListRecapInfoRendezVous> getListRecapInfoRendezVous() {
        return listRecapInfoRendezVous;
    }

    public void setListRecapInfoRendezVou(List<ListRecapInfoRendezVous> listRecapInfoRendezVous) {
        this.listRecapInfoRendezVous = listRecapInfoRendezVous;
    }

   

    public static class ListRecapInfoRendezVous {

        private String nom;
        private String prenom;
        private String numeroNational;
        private String dateRdv;
        private String nomVaccin;
        private String nomCentre;
        private String numeroDose;
        private String confrmationParEmail;
        private String emailConfirmation;

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getNumeroNational() {
            return numeroNational;
        }

        public void setNumeroNational(String numeroNational) {
            this.numeroNational = numeroNational;
        }

        public String getDateRdv() {
            return dateRdv;
        }

        public void setDateRdv(String dateRdv) {
            this.dateRdv = dateRdv;
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

        public String getNumeroDose() {
            return numeroDose;
        }

        public void setNumeroDose(String numeroDose) {
            this.numeroDose = numeroDose;
        }

        public String getConfrmationParEmail() {
            return confrmationParEmail;
        }

        public void setConfrmationParEmail(String confrmationParEmail) {
            this.confrmationParEmail = confrmationParEmail;
        }

        public String getEmailConfirmation() {
            return emailConfirmation;
        }

        public void setEmailConfirmation(String emailConfirmation) {
            this.emailConfirmation = emailConfirmation;
        }

    }

}

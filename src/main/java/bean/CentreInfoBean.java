
package bean;

import java.util.List;

public class CentreInfoBean {
    
    private String prenom;
    private String nomFamille;
    private String numeroNational;
    private List<CentreInfo> centreInfo;

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

    public List<CentreInfo> getCentreInfo() {
        return centreInfo;
    }

    public void setCentreInfo(List<CentreInfo> centreInfo) {
        this.centreInfo = centreInfo;
    }

  

    public static class CentreInfo {
        private String nomCentre;
        private int codePostal;
        private String adresse;
        private String numero;
        private String telephone;
        private String jourSemaineOuverture;
        private String heureFermeture;
        private String heureOuverture;

        public String getNomCentre() {
            return nomCentre;
        }

        public void setNomCentre(String nomCentre) {
            this.nomCentre = nomCentre;
        }

        public int getCodePostal() {
            return codePostal;
        }

        public void setCodePostal(int codePostal) {
            this.codePostal = codePostal;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getJourSemaineOuverture() {
            return jourSemaineOuverture;
        }

        public void setJourSemaineOuverture(String jourSemaineOuverture) {
            this.jourSemaineOuverture = jourSemaineOuverture;
        }

        public String getHeureFermeture() {
            return heureFermeture;
        }

        public void setHeureFermeture(String heureFermeture) {
            this.heureFermeture = heureFermeture;
        }

        public String getHeureOuverture() {
            return heureOuverture;
        }

        public void setHeureOuverture(String heureOuverture) {
            this.heureOuverture = heureOuverture;
        }

        
    }
    
}

package bean;
import bean.VaccinationHistoryBean.RendezVous;
import java.util.List;


public class IncidentBeanIn {
    private List<Incident> listIncident;
    private Patient patient;

    public List<Incident> getListIncident() {
        return listIncident;
    }

    public void setListIncident(List<Incident> listIncident) {
        this.listIncident = listIncident;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

   

    public static class Incident {
        private long dateNaissance;
        private String numeroNational;
        private String nomVaccin;
        private String remarques;
        private long dateRdv;
        private String numeroDose;
        private int codePostal;
        private String adresse;
        private String numeroAdresse;
        private String email;
        private String nomFamille;
        private String ville;
        private String prenom;

        public long getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(long dateNaissance) {
            this.dateNaissance = dateNaissance;
        }

        public String getNumeroNational() {
            return numeroNational;
        }

        public void setNumeroNational(String numeroNational) {
            this.numeroNational = numeroNational;
        }

        public String getNomVaccin() {
            return nomVaccin;
        }

        public void setNomVaccin(String nomVaccin) {
            this.nomVaccin = nomVaccin;
        }

        public String getRemarques() {
            return remarques;
        }

        public void setRemarques(String remarques) {
            this.remarques = remarques;
        }

        public long getDateRdv() {
            return dateRdv;
        }

        public void setDateRdv(long dateRdv) {
            this.dateRdv = dateRdv;
        }

        public String getNumeroDose() {
            return numeroDose;
        }

        public void setNumeroDose(String numeroDose) {
            this.numeroDose = numeroDose;
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

        public String getNumeroAdresse() {
            return numeroAdresse;
        }

        public void setNumeroAdresse(String numeroAdresse) {
            this.numeroAdresse = numeroAdresse;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNomFamille() {
            return nomFamille;
        }

        public void setNomFamille(String nomFamille) {
            this.nomFamille = nomFamille;
        }

        public String getVille() {
            return ville;
        }

        public void setVille(String ville) {
            this.ville = ville;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

     
    }

    public static class Patient {
        private String numeroNational;
        private int codePostal;
        private String prenom;
        private String nomFamille;
        private long dateNaissance;
        private String ville;
        private String adresse;
        private String numeroAdresse;
        private String codeSecret;
        private String email;
        private List<RendezVous> listeRendezVous; 

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

        public long getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(long dateNaissance) {
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

}

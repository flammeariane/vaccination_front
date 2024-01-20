package modele;

import java.util.Date;

public class EtatVaccinal {

    private String numeroNational;
    private int codePostal;
    private String prenom;
    private String nomFamille;
    private Date dateNaissance;
    private String ville;
    private String adresse;
    private String numeroAdresse;
    private String codeSecret;
    private String email;
    private ListeRendezVous list;

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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
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

    public ListeRendezVous getList() {
        return list;
    }

    public void setList(ListeRendezVous list) {
        this.list = list;
    }

}

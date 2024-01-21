package modele;

public class MembrePersonnel {

    private String nomFamille;
    private String prenom;
    private String role;
    private int localite;
    private String numTelephone;
    private String adresseMail;
    private String password;

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLocalite() {
        return localite;
    }

    public void setLocalite(int localite) {
        this.localite = localite;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

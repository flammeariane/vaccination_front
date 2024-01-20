
package modele;

import java.util.List;


public class CentreVaccination {
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

 

 

}
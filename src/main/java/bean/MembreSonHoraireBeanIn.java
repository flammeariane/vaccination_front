
package bean;

import java.util.Date;


public class MembreSonHoraireBeanIn {
    private String prenom;
    private String nomFamille;
    private String role;
    private Date   horaireStart ;
    private Date   horaireEnd;
    private boolean presence;
    private boolean statutPlanning ;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getHoraireStart() {
        return horaireStart;
    }

    public void setHoraireStart(Date horaireStart) {
        this.horaireStart = horaireStart;
    }

    public Date getHoraireEnd() {
        return horaireEnd;
    }

    public void setHoraireEnd(Date horaireEnd) {
        this.horaireEnd = horaireEnd;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public boolean isStatutPlanning() {
        return statutPlanning;
    }

    public void setStatutPlanning(boolean statutPlanning) {
        this.statutPlanning = statutPlanning;
    }
    
    
}

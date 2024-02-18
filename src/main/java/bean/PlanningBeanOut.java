package bean;

import java.util.HashMap;
import java.util.List;

public class PlanningBeanOut {
    private String roleCentre;
    private String statutConfirmer;
    private HashMap<String, List<HoraireBean>> mapMembreHoraire;

    public HashMap<String, List<HoraireBean>> getMapMembreHoraire() {
        return mapMembreHoraire;
    }

    public void setMapMembreHoraire(HashMap<String, List<HoraireBean>> mapMembreHoraire) {
        this.mapMembreHoraire = mapMembreHoraire;
    }

    public String getRoleCentre() {
        return roleCentre;
    }

    public void setRoleCentre(String roleCentre) {
        this.roleCentre = roleCentre;
    }

    public String getStatutConfirmer() {
        return statutConfirmer;
    }

    public void setStatutConfirmer(String statutConfirmer) {
        this.statutConfirmer = statutConfirmer;
    }

}

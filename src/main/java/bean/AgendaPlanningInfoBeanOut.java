/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;


import java.sql.Time;
import java.util.Date;


public class AgendaPlanningInfoBeanOut {
    private String roleCentre; 
    private String jourSemaineOuverture; 
    private Time heureOuverture; 
    private Time heureFermeture;
    private Date dateSelected ;

    public String getRoleCentre() {
        return roleCentre;
    }

    public void setRoleCentre(String roleCentre) {
        this.roleCentre = roleCentre;
    }

    public String getJourSemaineOuverture() {
        return jourSemaineOuverture;
    }

    public void setJourSemaineOuverture(String jourSemaineOuverture) {
        this.jourSemaineOuverture = jourSemaineOuverture;
    }

    public Time getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(Time heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public Time getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(Time heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public Date getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(Date dateSelected) {
        this.dateSelected = dateSelected;
    }

}

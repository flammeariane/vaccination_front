/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.List;

public class PatientsListResponse {

    private List<RendezVous> listPatient;

    // Getters et setters
    public List<RendezVous> getListPatient() {
        return listPatient;
    }

    public void setListPatient(List<RendezVous> listPatient) {
        this.listPatient = listPatient;
    }

}

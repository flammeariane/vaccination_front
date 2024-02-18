package bean;

import java.util.ArrayList;

public class StatCentreBeanIn {

    public ArrayList<TxPresAbsePatienJour> txPresAbsePatienJour;
    public ArrayList<TxPresAbsePatientMoi> txPresAbsePatientMois;
    public ArrayList<TxPresAbsePersonnelJour> txPresAbsePersonnelJour;
    public ArrayList<TxPresAbsePersonnelMoi> txPresAbsePersonnelMois;
    public ArrayList<NbrPatientVaccineJour> nbrPatientVaccineJour;
    public ArrayList<TauxremplissageJour> tauxremplissageJour;
    public ArrayList<NbrPatientVaccineMoi> nbrPatientVaccineMois;

    public ArrayList<TxPresAbsePatienJour> getTxPresAbsePatienJour() {
        return txPresAbsePatienJour;
    }

    public void setTxPresAbsePatienJour(ArrayList<TxPresAbsePatienJour> txPresAbsePatienJour) {
        this.txPresAbsePatienJour = txPresAbsePatienJour;
    }

    public ArrayList<TxPresAbsePatientMoi> getTxPresAbsePatientMois() {
        return txPresAbsePatientMois;
    }

    public void setTxPresAbsePatientMois(ArrayList<TxPresAbsePatientMoi> txPresAbsePatientMois) {
        this.txPresAbsePatientMois = txPresAbsePatientMois;
    }

    public ArrayList<TxPresAbsePersonnelJour> getTxPresAbsePersonnelJour() {
        return txPresAbsePersonnelJour;
    }

    public void setTxPresAbsePersonnelJour(ArrayList<TxPresAbsePersonnelJour> txPresAbsePersonnelJour) {
        this.txPresAbsePersonnelJour = txPresAbsePersonnelJour;
    }

    public ArrayList<TxPresAbsePersonnelMoi> getTxPresAbsePersonnelMois() {
        return txPresAbsePersonnelMois;
    }

    public void setTxPresAbsePersonnelMois(ArrayList<TxPresAbsePersonnelMoi> txPresAbsePersonnelMois) {
        this.txPresAbsePersonnelMois = txPresAbsePersonnelMois;
    }

    public ArrayList<NbrPatientVaccineJour> getNbrPatientVaccineJour() {
        return nbrPatientVaccineJour;
    }

    public void setNbrPatientVaccineJour(ArrayList<NbrPatientVaccineJour> nbrPatientVaccineJour) {
        this.nbrPatientVaccineJour = nbrPatientVaccineJour;
    }

    public ArrayList<TauxremplissageJour> getTauxremplissageJour() {
        return tauxremplissageJour;
    }

    public void setTauxremplissageJour(ArrayList<TauxremplissageJour> tauxremplissageJour) {
        this.tauxremplissageJour = tauxremplissageJour;
    }

    public ArrayList<NbrPatientVaccineMoi> getNbrPatientVaccineMois() {
        return nbrPatientVaccineMois;
    }

    public void setNbrPatientVaccineMois(ArrayList<NbrPatientVaccineMoi> nbrPatientVaccineMois) {
        this.nbrPatientVaccineMois = nbrPatientVaccineMois;
    }
    
    
    
    

 public static class NbrPatientVaccineJour {

        public int nbr;
        public long dateRdvSsTime;

        public int getNbr() {
            return nbr;
        }

        public void setNbr(int nbr) {
            this.nbr = nbr;
        }

        public long getDateRdvSsTime() {
            return dateRdvSsTime;
        }

        public void setDateRdvSsTime(long dateRdvSsTime) {
            this.dateRdvSsTime = dateRdvSsTime;
        }
        
    }

    public static class NbrPatientVaccineMoi {

        public int year;
        public int month;
        public int nbr;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNbr() {
            return nbr;
        }

        public void setNbr(int nbr) {
            this.nbr = nbr;
        }
        
    }

    public static class TauxremplissageJour {

        public Object dateJour;
        public double tauxRemplissage;

        public Object getDateJour() {
            return dateJour;
        }

        public double getTauxRemplissage() {
            return tauxRemplissage;
        }
        
    }

    public static class TxPresAbsePatienJour {

        public double tauxPresentPatient;
        public double tauxAbsentPatient;
        public long dateRdvSsTime;

        public double getTauxPresentPatient() {
            return tauxPresentPatient;
        }

        public void setTauxPresentPatient(double tauxPresentPatient) {
            this.tauxPresentPatient = tauxPresentPatient;
        }

        public double getTauxAbsentPatient() {
            return tauxAbsentPatient;
        }

        public void setTauxAbsentPatient(double tauxAbsentPatient) {
            this.tauxAbsentPatient = tauxAbsentPatient;
        }

        public long getDateRdvSsTime() {
            return dateRdvSsTime;
        }

        public void setDateRdvSsTime(long dateRdvSsTime) {
            this.dateRdvSsTime = dateRdvSsTime;
        }
        
    }

    public static class TxPresAbsePatientMoi {

        public int year;
        public int month;
        public double tauxPresentPatient;
        public double tauxAbsentPatient;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public double getTauxPresentPatient() {
            return tauxPresentPatient;
        }

        public void setTauxPresentPatient(double tauxPresentPatient) {
            this.tauxPresentPatient = tauxPresentPatient;
        }

        public double getTauxAbsentPatient() {
            return tauxAbsentPatient;
        }

        public void setTauxAbsentPatient(double tauxAbsentPatient) {
            this.tauxAbsentPatient = tauxAbsentPatient;
        }
        
    }

    public static class TxPresAbsePersonnelJour {

        public double tauxPresentPersonnel;
        public double tauxAbsentPersonnel;
        public Object dateRdvSsTime;

        public double getTauxPresentPersonnel() {
            return tauxPresentPersonnel;
        }

        public void setTauxPresentPersonnel(double tauxPresentPersonnel) {
            this.tauxPresentPersonnel = tauxPresentPersonnel;
        }

        public double getTauxAbsentPersonnel() {
            return tauxAbsentPersonnel;
        }

        public void setTauxAbsentPersonnel(double tauxAbsentPersonnel) {
            this.tauxAbsentPersonnel = tauxAbsentPersonnel;
        }

        public Object getDateRdvSsTime() {
            return dateRdvSsTime;
        }

        public void setDateRdvSsTime(Object dateRdvSsTime) {
            this.dateRdvSsTime = dateRdvSsTime;
        }
        
        
    }

    public static class TxPresAbsePersonnelMoi {

        public int year;
        public int month;
        public double tauxPresentPersonnel;
        public double tauxAbsentPersonnel;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public double getTauxPresentPersonnel() {
            return tauxPresentPersonnel;
        }

        public void setTauxPresentPersonnel(double tauxPresentPersonnel) {
            this.tauxPresentPersonnel = tauxPresentPersonnel;
        }

        public double getTauxAbsentPersonnel() {
            return tauxAbsentPersonnel;
        }

        public void setTauxAbsentPersonnel(double tauxAbsentPersonnel) {
            this.tauxAbsentPersonnel = tauxAbsentPersonnel;
        }
        
        
    }

}

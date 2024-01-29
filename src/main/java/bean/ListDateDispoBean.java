
package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;


public class ListDateDispoBean {
    
    private List<listInfoAgenda> listInfoAgenda;

    public List<listInfoAgenda> getListInfoAgenda() {
        return listInfoAgenda;
    }

    public void setListInfoAgenda(List<listInfoAgenda> listInfoAgenda) {
        this.listInfoAgenda = listInfoAgenda;
    }

     public static class listInfoAgenda{
           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
  
     
         private Date dateAgenda;
         private int nbrMaxPlace;
         private int nbrPlacePrise;
         private int nbrPlaceRestante;

        public Date getDateAgenda() {
            return dateAgenda;
        }

        public void setDateAgenda(Date dateAgenda) {
            this.dateAgenda = dateAgenda;
        }

        public int getNbrMaxPlace() {
            return nbrMaxPlace;
        }

        public void setNbrMaxPlace(int nbrMaxPlace) {
            this.nbrMaxPlace = nbrMaxPlace;
        }

        public int getNbrPlacePrise() {
            return nbrPlacePrise;
        }

        public void setNbrPlacePrise(int nbrPlacePrise) {
            this.nbrPlacePrise = nbrPlacePrise;
        }

        public int getNbrPlaceRestante() {
            return nbrPlaceRestante;
        }

        public void setNbrPlaceRestante(int nbrPlaceRestante) {
            this.nbrPlaceRestante = nbrPlaceRestante;
        }
     
     }
    
    
}

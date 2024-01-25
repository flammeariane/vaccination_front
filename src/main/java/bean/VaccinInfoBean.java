
package bean;

import java.util.List;
import modele.Vaccin;

/**
 *
 * @author flamm
 */
public class VaccinInfoBean {
    
       private List<Vaccin> vaccin;

       public List<Vaccin> getVaccin() {
              return vaccin;
       }

       public void setVaccin(List<Vaccin> vaccin) {
              this.vaccin = vaccin;
       }
}

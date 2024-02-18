package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import modele.MembrePersonnel;
import java.util.List;

public class ListMembreBean {

    @JsonProperty("listMembrePersonnelResp")
    private List<MembrePersonnel> listMenbrePersonnelResp;

    public ListMembreBean() {
    }

    public List<MembrePersonnel> getMembers() {
        return listMenbrePersonnelResp;
    }

    public void setMembers(List<MembrePersonnel> members) {
        this.listMenbrePersonnelResp = members;
    }
}
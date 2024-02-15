package bean;

import modele.MembrePersonnel;

import java.util.List;

public class ListMembreBean {

    private List<MembrePersonnel> members;

    public ListMembreBean() {
    }

    public List<MembrePersonnel> getMembers() {
        return members;
    }

    public void setMembers(List<MembrePersonnel> members) {
        this.members = members;
    }
}

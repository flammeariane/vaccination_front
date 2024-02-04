// PersonnelOperationsFacade.java
package facade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.MembrePersonnel;

public interface PersonnelOperationsFacade {

    MembrePersonnel loginPersonnel(String adresseMail, String password) throws IOException;

    void redirectPersonnelBasedOnRole(MembrePersonnel membrePersonnel, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}

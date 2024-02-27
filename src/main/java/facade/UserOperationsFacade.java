package facade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;
import modele.Patient;

public interface UserOperationsFacade {

    void logoutUser(HttpServletRequest request,HttpSession session, HttpServletResponse response) throws IOException;

    Patient loginPatient(String numeroNational, String codeSecret) throws IOException;

    MembrePersonnel loginPersonnel(String adresseMail, String password) throws IOException;

   void redirectPersonnelBasedOnRole(MembrePersonnel membrePersonnel, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;


}

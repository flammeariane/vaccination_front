package controller.responsables;

import bean.ListCentreBean;
import bean.ListMembreBean;
import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import facade.MembrePersonnelFacade;
import facade.impl.RendezVousFacadeImpl;
import facade.impl.MembrePersonnelFacadeImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.MembrePersonnel;

import static utils.Roles.RESPONSABLE_CENTRE;
import static utils.Roles.RESPONSABLE_GENERAL;


public class ResponsableServlet extends HttpServlet {

    private MembrePersonnelFacade MembrePersonnelFacade = MembrePersonnelFacadeImpl.INSTANCE;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");

        if (membrePersonnel != null) {
            String currentRole = membrePersonnel.getRole();
            if (RESPONSABLE_GENERAL.equals(currentRole)) {
                ListCentreBean centres = MembrePersonnelFacade.getListCentre(membrePersonnel);
                request.setAttribute("centres", centres);
                request.getRequestDispatcher("dashboard_responsable_general.jsp").forward(request, response);
            } else if (RESPONSABLE_CENTRE.equals(currentRole)) {
                ListMembreBean listMembreBean = MembrePersonnelFacade.getListMembre(membrePersonnel);
                request.setAttribute("members", listMembreBean);
                request.getRequestDispatcher("dashboard_responsable_centre.jsp").forward(request, response);
            }
        } else {
            // Gestion de l'erreur
            request.setAttribute("errorMessage", "Problème de connexion ou de récupération des données");
            request.getRequestDispatcher("login_personnel_medical.jsp").forward(request, response);
        }
    }
}
// USECASE 5: Déterminer les pools de professionnels par centre => responbale general
// TODO Fahdi getListCentres
// TODO Fahdi getListMenbrePersonnel
// TODO Fahdi SavePoolPersonnelcentre

// Use case 6 : Gérer le planning des pools de professionnels =>responsable de centre
// TODO Fahdi getPoolBycenter
// TODO Fahdi getHoraire
// TODO Fahdi Save horaire for center
   
  



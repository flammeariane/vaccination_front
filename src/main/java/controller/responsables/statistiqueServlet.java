package controller.responsables;

import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import facade.MembrePersonnelFacade;
import facade.impl.MembrePersonnelFacadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;

public class statistiqueServlet extends HttpServlet {

    private MembrePersonnelFacade MembrePersonnelFacade =  MembrePersonnelFacadeImpl.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");

        StatCentreBeanOut centreRequest = new StatCentreBeanOut();
        centreRequest.setCodePostal(1000);
        centreRequest.setNomCentre("Family Care");
        centreRequest.setRole("Responsable general");

        StatCentreBeanIn stats = MembrePersonnelFacade.getStatCentre(centreRequest);
        request.setAttribute("stats", stats);

        request.getRequestDispatcher("statistique_centre.jsp").forward(request, response);

    }
}


  
    // Use case n°7 : Consulter les statistiques par centre => responsable general
    //  getListCentres
    // Le système affiche, pour le centre un graphique avec le taux d absence et presence
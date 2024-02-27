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
        centreRequest.setNomCentre(request.getParameter("nomCentre"));
        centreRequest.setRole(request.getParameter("role"));

        StatCentreBeanIn statistiquesData = MembrePersonnelFacade.getStatCentre(centreRequest);
        request.setAttribute("statistiquesData", statistiquesData);

        request.getRequestDispatcher("statistique_centre.jsp").forward(request, response);

    }
}

// Use case n°7 : Consulter les statistiques par centre => responsable general


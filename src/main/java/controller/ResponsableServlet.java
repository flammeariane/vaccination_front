package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ResponsableServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
    }
    // USECASE 5: Déterminer les pools de professionnels par centre => responbale general
    // TODO getListCentres
    // TODO getListMenbrePersonnel
    // TODO SavePoolPersonnelcentre

    // Use case 6 : Gérer le planning des pools de professionnels =>responsable de centre
    // TODO getPoolBycenter 
    // TODO getHoraire
    // TODO Save horaire for center
   
    
    // Use case n°7 : Consulter les statistiques par centre => responsable general
    // TODO getListCentres
    // Le système affiche, pour le centre un graphique avec le taux d absence et presence

}

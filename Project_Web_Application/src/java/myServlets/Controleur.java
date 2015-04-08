/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oswald
 */
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {
    
    @Resource(name = "jdbc/spectacles")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        SpectacleDAO spectacleDAO = new SpectacleDAO(ds);
        try {
            if (action == null) {
                actionAfficher(request, response, spectacleDAO);
            } else if (action.equals("ajouter")) {
                actionAjouter(request, response, spectacleDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, spectacleDAO);
            } else if (action.equals("modifier")) {
                actionModifier(request, response, spectacleDAO);
            } else if (action.equals("getSpectacle")) {
                actionGetSpectacle(request, response, spectacleDAO);
            } else if (action.equals("addS")) {
                actionAddSpectacle(request, response, spectacleDAO);
            } else {
                // ... renvoi vers une page d'erreur controleurErreur.jsp
            }
        } catch (DAOException e) {
            // ... renvoi vers une page d'erreur bdErreur.jsp
        }
    }

    /**
     * Ajout d'un ouvrage.
     */
    private void actionAjouter(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Suppression d'un ouvrage.
     */
    private void actionSupprimer(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Modification d'un ouvrage.
     */
    private void actionModifier(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response,
            SpectacleDAO spectacleDAO)
            throws DAOException, ServletException, IOException {
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        getServletContext()
                .getRequestDispatcher("/WEB-INF/afficheAffiches.jsp")
                .forward(request, response);
    }
    
    private void actionGetSpectacle(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void actionAddSpectacle(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO)
            throws ServletException, IOException, DAOException {
//        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        spectacleDAO.ajouterSpectacle(request.getParameter("nomS"),
                request.getParameter("auteurS"),
                request.getParameter("mesS"),
                request.getParameter("dureeS"));        
        this.actionAfficher(request, response, spectacleDAO);
    }
}

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
import javax.servlet.http.HttpSession;

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
     *
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        SpectacleDAO spectacleDAO = new SpectacleDAO(ds);
        UtilisateurDAO userAO = new UtilisateurDAO(ds);
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
            } else if (action.equals("verifUser")) {
                actionVerifUser(request, response, userAO);
            } else if (action.equals("actionAddUser")){
                actionAddUser(request, response, userAO);
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
                request.getParameter("dureeS"),
                request.getParameter("fileS"));
        getServletContext()
                .getRequestDispatcher("/addSpectacle.jsp")
                .forward(request, response);
    }

    private void actionVerifUser(HttpServletRequest request,
            HttpServletResponse response,
            UtilisateurDAO utilisateurDAO)
            throws DAOException, ServletException, IOException {
        String sortie = utilisateurDAO.verifUser(request.getParameter("loginU"), request.getParameter("mdpU"));
        if (sortie.equals("")) {
            getServletContext()
                    .getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("utilisateur", request.getParameter("loginU"));
            getServletContext()
                    .getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        }
    }

    private void actionAddUser(HttpServletRequest request, HttpServletResponse response, UtilisateurDAO utilisateurDAO)
            throws ServletException, IOException, DAOException {
        if(utilisateurDAO.ajouterUser(request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("email"))){
            getServletContext()
                    .getRequestDispatcher("/AccountCreationSuccess.html")
                    .forward(request, response);
        } else {
            getServletContext()
                .getRequestDispatcher("/AccountCreationFailed.html")
                .forward(request, response);
        }
    }
}

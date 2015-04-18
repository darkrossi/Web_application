/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        UtilisateurDAO userDAO = new UtilisateurDAO(ds);
        RepresentationDAO represDAO = new RepresentationDAO(ds);
        SalleDAO salleDAO = new SalleDAO(ds);
        DossierDAO dossierDAO = new DossierDAO(ds);
        try {
            if (action == null) {
                actionAfficher(request, response, spectacleDAO);
            } else if (action.equals("addS")) {
                actionAddSpectacle(request, response, spectacleDAO);
            } else if (action.equals("verifUser")) {
                actionVerifUser(request, response, userDAO);
            } else if (action.equals("addUser")) {
                actionAddUser(request, response, userDAO);
            } else if (action.equals("addRepres")) {
                actionAddRepres(request, response, represDAO);
            } else if (action.equals("addSalle")) {
                actionAddSalle(request, response, salleDAO);
            } else if (action.equals("displayAddRepres")) {
                actionDisplayAddRepres(request, response, spectacleDAO, salleDAO);
            } else if (action.equals("displayAccount")) {
                actionDisplayAccount(request, response, dossierDAO);
            } else if (action.equals("displayAddBooking")) {
                actionDisplayAddBooking(request, response, represDAO);
            } else {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("log", e.toString());
            getServletContext()
                    .getRequestDispatcher("/ErrorBdd.jsp")
                    .forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (utilisateurDAO.ajouterUser(request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("email"))) {
            request.setAttribute("bool", 1);
        } else {
            request.setAttribute("bool", 0);
        }
        getServletContext()
                .getRequestDispatcher("/LogAddUser.jsp")
                .forward(request, response);
    }

    private void actionAddRepres(HttpServletRequest request, HttpServletResponse response, RepresentationDAO represDAO)
            throws DAOException, IOException, ServletException {
        if (represDAO.ajouterRepresentation(request.getParameter("date"),
                request.getParameter("valueHeure"),
                request.getParameter("valueSpect"),
                request.getParameter("valueSalle"),
                request.getParameter("nbP"))) {
            request.setAttribute("bool", 1);
        } else {
            request.setAttribute("bool", 0);
        }
        getServletContext()
                .getRequestDispatcher("/LogAddRepresent.jsp")
                .forward(request, response);
    }

    private void actionAddSalle(HttpServletRequest request, HttpServletResponse response, SalleDAO salleDAO)
            throws DAOException, ServletException, IOException {
        if (salleDAO.ajouterSalle()) {
            request.setAttribute("bool", 1);
        } else {
            request.setAttribute("bool", 0);
        }
        getServletContext()
                .getRequestDispatcher("/LogAddSalle.jsp")
                .forward(request, response);
    }

    private void actionDisplayAddRepres(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, SalleDAO salleDAO)
            throws DAOException, ClassNotFoundException, IOException, ServletException {
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        request.setAttribute("salles", salleDAO.getListeSalles());
        getServletContext()
                .getRequestDispatcher("/addRepresent.jsp")
                .forward(request, response);
    }

    private void actionDisplayAccount(HttpServletRequest request, HttpServletResponse response, DossierDAO dossierDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("dossiers", dossierDAO.getFolders(request.getParameter("login")));
        getServletContext()
                .getRequestDispatcher("/monCompte.jsp")
                .forward(request, response);
    }

    private void actionDisplayAddBooking(HttpServletRequest request, HttpServletResponse response, RepresentationDAO represDAO) throws ServletException, IOException, DAOException {
        request.setAttribute("repres", represDAO.getRepresFromSp());
        getServletContext()
                .getRequestDispatcher("/addBooking.jsp")
                .forward(request, response);
    }
}

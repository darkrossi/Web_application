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
        AchatDAO bookingDAO = new AchatDAO(ds);
        RangDAO rangDAO = new RangDAO(ds);

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
                actionAddRepres(request, response, represDAO, spectacleDAO, salleDAO);
            } else if (action.equals("displayAddSalle")) {
                actionDisplayAddSalle(request, response, salleDAO);
            } else if (action.equals("addSalle")) {
                actionAddSalle(request, response, salleDAO);
            } else if (action.equals("displayAddRepres")) {
                actionDisplayAddRepres(request, response, spectacleDAO, salleDAO);
            } else if (action.equals("displayAccount")) {
                actionDisplayAccount(request, response, dossierDAO);
            } else if (action.equals("displayAddBooking")) {
                actionDisplayAddBooking(request, response, represDAO, 0);
            } else if (action.equals("addBooking")) {
                actionAddBooking(request, response, bookingDAO, dossierDAO, represDAO);
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
        }
    }

    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response,
            SpectacleDAO spectacleDAO)
            throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 0);
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        getServletContext()
                .getRequestDispatcher("/WEB-INF/afficheAffiches.jsp")
                .forward(request, response);
    }

    private void actionAddSpectacle(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", 1);
        if (spectacleDAO.ajouterSpectacle(request.getParameter("nomS"),
                request.getParameter("auteurS"),
                request.getParameter("mesS"),
                request.getParameter("dureeS"),
                request.getParameter("fileS"))) {
            request.setAttribute("logText", "Spectacle créé avec succès !");
        } else {
            request.setAttribute("logText", "Erreur lors de la création du spectacle..");
        }
        getServletContext()
                .getRequestDispatcher("/addSpectacle.jsp")
                .forward(request, response);
    }

    private void actionVerifUser(HttpServletRequest request,
            HttpServletResponse response,
            UtilisateurDAO utilisateurDAO)
            throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 0);
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
        request.setAttribute("logBool", 1);
        if (utilisateurDAO.ajouterUser(request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("mail"))) {
            request.setAttribute("logText", "Compte utilisateur créé avec succès !");
            HttpSession session = request.getSession(true);
            session.setAttribute("utilisateur", request.getParameter("login"));
        } else {
            request.setAttribute("logText", "Erreur lors de la création du compte utilisateur..");
        }
        getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    private void actionAddRepres(HttpServletRequest request, HttpServletResponse response,
            RepresentationDAO represDAO,
            SpectacleDAO spectDAO,
            SalleDAO salleDAO)
            throws DAOException, IOException, ServletException {

        request.setAttribute("logBool", 1);
        if (represDAO.ajouterRepresentation(request.getParameter("date"),
                request.getParameter("valueHeure"),
                request.getParameter("valueSpect"),
                request.getParameter("valueSalle"))) {
            request.setAttribute("logText", "Représentation ajoutée avec succès !");
        } else {
            request.setAttribute("logText", "Erreur lors de la création de la représentation..");
        }
        actionDisplayAddRepres(request, response, spectDAO, salleDAO);
    }

    private void actionAddSalle(HttpServletRequest request, HttpServletResponse response, SalleDAO salleDAO)
            throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 1);
        if (salleDAO.ajouterSalle(Integer.parseInt(request.getParameter("nbRa")),
                Integer.parseInt(request.getParameter("nbP")),
                Integer.parseInt(request.getParameter("catTarif")))) {
            request.setAttribute("logText", "Salle ajoutée avec succès !");
        } else {
            request.setAttribute("logText", "Erreur lors de la création de la salle..");
        }
        actionDisplayAddSalle(request, response, salleDAO);

    }

    private void actionDisplayAddRepres(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, SalleDAO salleDAO)
            throws DAOException, IOException, ServletException {
        request.setAttribute("logBool", 0);
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        request.setAttribute("salles", salleDAO.getListeSalles());
        getServletContext()
                .getRequestDispatcher("/addRepresent.jsp")
                .forward(request, response);
    }

    private void actionDisplayAccount(HttpServletRequest request, HttpServletResponse response, DossierDAO dossierDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 0);
        request.setAttribute("dossiers", dossierDAO.getFolders(request.getParameter("login")));
        getServletContext()
                .getRequestDispatcher("/monCompte.jsp")
                .forward(request, response);
    }

    private void actionDisplayAddBooking(HttpServletRequest request, HttpServletResponse response,
            RepresentationDAO represDAO,
            int logBool)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", logBool);
        request.setAttribute("repres", represDAO.getRepresFromSp());
        getServletContext()
                .getRequestDispatcher("/addBooking.jsp")
                .forward(request, response);
    }

    private void actionAddBooking(HttpServletRequest request, HttpServletResponse response,
            AchatDAO bookingDAO,
            DossierDAO dossierDAO,
            RepresentationDAO represDAO)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", 1);
        if ("null".equals(request.getParameter("login"))) {
            request.setAttribute("logText", "Vous devez créer un compte utilisateur pour pouvoir acheter des places !");
            actionDisplayAddBooking(request, response, represDAO, 1);
        } else {
            if (bookingDAO.ajouterReservation(request.getParameter("login"),
                    (String[]) request.getParameterValues("cbNR"),
                    (String[]) request.getParameterValues("nbP"))) {
                request.setAttribute("logText", "Achat effectué avec succès !");
            } else {
                request.setAttribute("logText", "Erreur lors de l'achat...");
            }
            actionDisplayAccount(request, response, dossierDAO);
        }

    }

    private void actionDisplayAddSalle(HttpServletRequest request, HttpServletResponse response, SalleDAO salleDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 0);
        request.setAttribute("salles", salleDAO.getListeSalles());
        getServletContext()
                .getRequestDispatcher("/addSalle.jsp")
                .forward(request, response);
    }

//    private void actionAddBookingFinal(HttpServletRequest request, HttpServletResponse response, RangDAO rangDAO) {
//        request.setAttribute("logBool", 0);
//        request.setAttribute("repres", rangDAO.getRangs(request.getParameter)));
//        getServletContext()
//                .getRequestDispatcher("/addBooking.jsp")
//                .forward(request, response);
//    }
}

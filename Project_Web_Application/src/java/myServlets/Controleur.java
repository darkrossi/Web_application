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
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            String action = request.getParameter("action");
            SpectacleDAO spectacleDAO = new SpectacleDAO(ds);
            UtilisateurDAO userDAO = new UtilisateurDAO(ds);
            RepresentationDAO represDAO = new RepresentationDAO(ds);
            SalleDAO salleDAO = new SalleDAO(ds);
            DossierDAO dossierDAO = new DossierDAO(ds);
            AchatDAO achatDAO = new AchatDAO(ds);
            RangDAO rangDAO = new RangDAO(ds);
            if (action == null) {
                actionAfficher(request, response, spectacleDAO);
            } else if (action.equals("addS")) {
                actionAddSpectacle(request, response, spectacleDAO);
            } else if (action.equals("addUser")) {
                actionAddUser(request, response, userDAO);
            } else if (action.equals("addRepres")) {
                actionAddRepres(request, response, represDAO, spectacleDAO, salleDAO);
            } else if (action.equals("displayAddSalle")) {
                actionDisplayAddSalle(request, response, salleDAO);
            } else if (action.equals("addSalle")) {
                actionAddSalle(request, response, salleDAO);
            } else if (action.equals("displayAddRepres")) {
                actionDisplayAddRepres(request, response, spectacleDAO, salleDAO, 0);
            } else if (action.equals("displayAccount")) {
                actionDisplayAccount(request, response, dossierDAO);
            } else if (action.equals("displayAddBooking")) {
                actionDisplayAddBooking(request, response, represDAO, 0);
            } else if (action.equals("addBooking")) {
                actionAddBooking(request, response, achatDAO, dossierDAO, represDAO);
            } else if (action.equals("displayPiecesResa")) {
                actionDisplayPiecesResa(request, response, spectacleDAO, 0);
            } else if (action.equals("displayPieces")) {
                actionDisplayPieces(request, response, spectacleDAO, represDAO);
            } else if (action.equals("displayPiecesPlaces")) {
                actionDisplayPiecesPlaces(request, response, spectacleDAO, represDAO, rangDAO);
            } else {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            }
        } catch (ServletException | IOException | DAOException ex) {
            request.setAttribute("log", ex.toString());
            try {
                getServletContext()
                        .getRequestDispatcher("/ErrorBdd.jsp")
                        .forward(request, response);
            } catch (IOException | ServletException ex1) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            String action = request.getParameter("action");

            SpectacleDAO spectacleDAO = new SpectacleDAO(ds);
            UtilisateurDAO userDAO = new UtilisateurDAO(ds);
//            RepresentationDAO represDAO = new RepresentationDAO(ds);
//            SalleDAO salleDAO = new SalleDAO(ds);
//            DossierDAO dossierDAO = new DossierDAO(ds);
//            AchatDAO achatDAO = new AchatDAO(ds);
//            RangDAO rangDAO = new RangDAO(ds);

            if (action == null) {
                actionAfficher(request, response, spectacleDAO);
            } else if (action.equals("verifUser")) {
                actionVerifUser(request, response, userDAO);
            } else {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            }
        } catch (DAOException | ServletException | IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
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
            request.setAttribute("errorLogin", 1);
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
        actionDisplayAddRepres(request, response, spectDAO, salleDAO, 1);
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

    private void actionDisplayAddRepres(HttpServletRequest request,
            HttpServletResponse response,
            SpectacleDAO spectacleDAO,
            SalleDAO salleDAO,
            int logBool)
            throws DAOException, IOException, ServletException {
        request.setAttribute("logBool", logBool);
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
            AchatDAO achatDAO,
            DossierDAO dossierDAO,
            RepresentationDAO represDAO)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", 1);
        if ("null".equals(request.getParameter("login"))) {
            request.setAttribute("logText", "Vous devez créer un compte utilisateur pour pouvoir acheter des places !");
            actionDisplayAddBooking(request, response, represDAO, 1);
        } else {
            String[] tabCbNR = (String[]) request.getParameterValues("cbNR");
            String[] tabNbP = new String[tabCbNR.length];
            for (int i = 0; i < tabCbNR.length; i++) {
                tabNbP[i] = request.getParameter("nbP" + tabCbNR[i]);
            }
            if (achatDAO.ajouterReservation(request.getParameter("login"), tabCbNR, tabNbP)) {
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

    private void actionDisplayPiecesResa(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, int logBool)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", logBool);
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        getServletContext()
                .getRequestDispatcher("/piecesResa.jsp")
                .forward(request, response);
    }

    private void actionDisplayPieces(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, RepresentationDAO represDAO)
            throws ServletException, DAOException, IOException {
        if ("null".equals(request.getParameter("login"))) {
            request.setAttribute("logText", "Vous devez créer un compte utilisateur pour pouvoir acheter des places !");
            actionDisplayPiecesResa(request, response, spectacleDAO, 1);
        } else {
            request.setAttribute("logBool", 0);
            request.setAttribute("repres", represDAO.getRepresList(Integer.parseInt(request.getParameter("NSp"))));
            request.setAttribute("spectacle", spectacleDAO.getSpectacle(Integer.parseInt(request.getParameter("NSp"))));
            getServletContext()
                    .getRequestDispatcher("/pieces.jsp")
                    .forward(request, response);
        }
    }

    private void actionDisplayPiecesPlaces(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectDAO, RepresentationDAO represDAO, RangDAO rangDAO)
            throws DAOException, ServletException, IOException {
        request.setAttribute("represPicked", represDAO.getRepres(Integer.parseInt(request.getParameter("NR"))));
        request.setAttribute("rangs", rangDAO.getRangs(Integer.parseInt(request.getParameter("NSa")), true));
        actionDisplayPieces(request, response, spectDAO, represDAO);
    }
}

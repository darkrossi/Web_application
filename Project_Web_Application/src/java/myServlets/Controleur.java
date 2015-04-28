/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import dao.*;
import java.io.IOException;
import java.text.ParseException;
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
            String action = request.getParameter("action");
            SpectacleDAO spectacleDAO = new SpectacleDAO(ds);
            UtilisateurDAO userDAO = new UtilisateurDAO(ds);
            RepresentationDAO represDAO = new RepresentationDAO(ds);
            SalleDAO salleDAO = new SalleDAO(ds);
            DossierDAO dossierDAO = new DossierDAO(ds);
            AchatDAO achatDAO = new AchatDAO(ds);
            RangDAO rangDAO = new RangDAO(ds);
            if (action == null) {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            } else if (action.equals("addS")) {
                actionAddSpectacle(request, response, spectacleDAO);
            } else if (action.equals("addUser")) {
                actionAddUser(request, response, userDAO);
            } else if (action.equals("addRepres")) {
                actionAddRepres(request, response, represDAO, spectacleDAO, salleDAO);
            } else if (action.equals("displayAddSalle")) {
                actionDisplayAddSalle(request, response, salleDAO);
            } else if (action.equals("displayAddRepres")) {
                actionDisplayAddRepres(request, response, spectacleDAO, salleDAO, 0);
            } else if (action.equals("displayAccount")) {
                actionDisplayAccount(request, response, dossierDAO);
            } else if (action.equals("displayCatalogue")) {
                actionDisplayCatalogue(request, response, spectacleDAO, 0);
            } else if (action.equals("displayResa")) {
                actionDisplayResa(request, response, spectacleDAO, represDAO, 0);
            } else if (action.equals("displayResaPlaces")) {
                actionDisplayResaPlaces(request, response, spectacleDAO, represDAO, rangDAO);
            } else if (action.equals("addSalle")) {
                actionAddSalle(request, response, salleDAO);
            } else {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            }
        } catch (ServletException | IOException | DAOException | ParseException ex) {
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
            String action = request.getParameter("action");

            SpectacleDAO spectacleDAO = new SpectacleDAO(ds);
            UtilisateurDAO userDAO = new UtilisateurDAO(ds);
            RepresentationDAO represDAO = new RepresentationDAO(ds);
            SalleDAO salleDAO = new SalleDAO(ds);
            DossierDAO dossierDAO = new DossierDAO(ds);
            AchatDAO achatDAO = new AchatDAO(ds);
//            RangDAO rangDAO = new RangDAO(ds);

            if (action == null) {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            } else if (action.equals("verifUser")) {
                actionVerifUser(request, response, userDAO);
            } else if (action.equals("filtrerCatalogue")) {
                actionFiltrerCatalogue(request, response, spectacleDAO, 0);
            } else if (action.equals("filtrerResa")) {
                actionFiltrerResa(request, response, represDAO, spectacleDAO, 0);
            } else if (action.equals("addAchat")) {
                actionAddAchat(request, response, achatDAO, spectacleDAO, represDAO);
            } else if (action.equals("confirmResa")) {
                actionConfirmResa(request, response, dossierDAO);
            } else if (action.equals("addSalle")) {
                actionAddSalle(request, response, salleDAO);
            } else {
                getServletContext()
                        .getRequestDispatcher("/ErrorRequest.jsp")
                        .forward(request, response);
            }
        } catch (DAOException | ServletException | IOException | ParseException ex) {
            request.setAttribute("log", ex.toString());
            try {
                getServletContext()
                        .getRequestDispatcher("/ErrorBdd.jsp")
                        .forward(request, response);
            } catch (ServletException | IOException ex1) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void actionAddSpectacle(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", 1);
        if (spectacleDAO.ajouterSpectacle(request.getParameter("nomS"),
                request.getParameter("auteurS"),
                request.getParameter("mesS"),
                request.getParameter("dureeS"),
                request.getParameter("infos"),
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
        if (salleDAO.ajouterSalle(request.getParameter("nomSalle"),
                Integer.parseInt(request.getParameter("nbRaP")),
                Integer.parseInt(request.getParameter("nbRaB")),
                Integer.parseInt(request.getParameter("nbRaO")),
                Integer.parseInt(request.getParameter("nbP")))) {
            request.setAttribute("logText", "Salle ajoutée avec succès !");
        } else {
            request.setAttribute("logText", "Erreur lors de la création de la salle..");
        }
        request.setAttribute("salles", salleDAO.getListeSalles());
        getServletContext()
                .getRequestDispatcher("/addSalle.jsp")
                .forward(request, response);

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
        request.setAttribute("dossiers", dossierDAO.getFolders(request.getParameter("login"), 0));
        request.setAttribute("resas", dossierDAO.getFolders(request.getParameter("login"), 1));
        getServletContext()
                .getRequestDispatcher("/monCompte.jsp")
                .forward(request, response);
    }

    private void actionAddAchat(HttpServletRequest request, HttpServletResponse response,
            AchatDAO achatDAO,
            SpectacleDAO spectDAO,
            RepresentationDAO represDAO)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", 1);
        if (achatDAO.ajouterAchat(request.getParameter("login"),
                Integer.parseInt(request.getParameter("NR")),
                Integer.parseInt(request.getParameter("NRa")),
                Integer.parseInt(request.getParameter("NP")),
                1,
                Integer.parseInt(request.getParameter("boolResa")))) {
            request.setAttribute("logText", "Achat effectué avec succès !");
        } else {
            request.setAttribute("logText", "Erreur lors de l'achat...");
        }
        request.setAttribute("repres", represDAO.getRepresList(Integer.parseInt(request.getParameter("NSp"))));
        request.setAttribute("spectacle", spectDAO.getSpectacle(Integer.parseInt(request.getParameter("NSp"))));
        getServletContext()
                .getRequestDispatcher("/reservation.jsp")
                .forward(request, response);

    }

    private void actionDisplayAddSalle(HttpServletRequest request, HttpServletResponse response, SalleDAO salleDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 0);
        request.setAttribute("salles", salleDAO.getListeSalles());
        getServletContext()
                .getRequestDispatcher("/addSalle.jsp")
                .forward(request, response);
    }

    private void actionDisplayCatalogue(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, int logBool)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", logBool);
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles());
        getServletContext()
                .getRequestDispatcher("/catalogue.jsp")
                .forward(request, response);
    }

    private void actionDisplayResa(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, RepresentationDAO represDAO, int logBool)
            throws ServletException, DAOException, IOException {
        if ("null".equals(request.getParameter("login"))) {
            request.setAttribute("logText", "Vous devez créer un compte utilisateur pour pouvoir acheter des places !");
            actionDisplayCatalogue(request, response, spectacleDAO, 1);
        } else {
            request.setAttribute("logBool", logBool);
            request.setAttribute("repres", represDAO.getRepresList(Integer.parseInt(request.getParameter("NSp"))));
            request.setAttribute("spectacle", spectacleDAO.getSpectacle(Integer.parseInt(request.getParameter("NSp"))));
            getServletContext()
                    .getRequestDispatcher("/reservation.jsp")
                    .forward(request, response);
        }
    }

    private void actionDisplayResaPlaces(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectDAO, RepresentationDAO represDAO, RangDAO rangDAO)
            throws DAOException, ServletException, IOException, ParseException {
        request.setAttribute("represPicked", represDAO.getRepres(Integer.parseInt(request.getParameter("NR"))));
        request.setAttribute("rangs", rangDAO.getRangs(Integer.parseInt(request.getParameter("NSa")), true));
        actionFiltrerResa(request, response, represDAO, spectDAO, 0);
    }

    private void actionFiltrerCatalogue(HttpServletRequest request, HttpServletResponse response, SpectacleDAO spectacleDAO, int logBool)
            throws ServletException, IOException, DAOException {
        request.setAttribute("logBool", logBool);
        request.setAttribute("spectacles", spectacleDAO.getListeSpectaclesTri(
                request.getParameter("motscles").toLowerCase(),
                request.getParameter("datepicker1"),
                request.getParameter("datepicker2"),
                request.getParameter("prixDe"),
                request.getParameter("prixA"),
                request.getParameterValues("checkGenre"),
                request.getParameterValues("checkPop")));
        getServletContext()
                .getRequestDispatcher("/catalogue.jsp")
                .forward(request, response);
    }

    private void actionFiltrerResa(HttpServletRequest request, HttpServletResponse response, RepresentationDAO represDAO, SpectacleDAO spectacleDAO, int logBool)
            throws DAOException, ServletException, IOException, ParseException {
        request.setAttribute("logBool", logBool);
        request.setAttribute("repres", represDAO.getListeRepresTri(
                Integer.parseInt(request.getParameter("NSp")),
                request.getParameter("datepicker1"),
                request.getParameter("datepicker2")));
        request.setAttribute("spectacle", spectacleDAO.getSpectacle(Integer.parseInt(request.getParameter("NSp"))));
        request.setAttribute("datepicker1", request.getParameter("datepicker1"));
        request.setAttribute("datepicker2", request.getParameter("datepicker2"));
        getServletContext()
                .getRequestDispatcher("/reservation.jsp")
                .forward(request, response);
    }

    private void actionConfirmResa(HttpServletRequest request, HttpServletResponse response, DossierDAO dossierDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("logBool", 1);
        if (dossierDAO.swapResa(Integer.parseInt(request.getParameter("ND")))) {
            request.setAttribute("logText", "Achat effectué avec succès !");
        } else {
            request.setAttribute("logText", "Pitit ploblém");
        }
        request.setAttribute("dossiers", dossierDAO.getFolders(request.getParameter("login"), 0));
        request.setAttribute("resas", dossierDAO.getFolders(request.getParameter("login"), 1));
        getServletContext()
                .getRequestDispatcher("/monCompte.jsp")
                .forward(request, response);
    }
}

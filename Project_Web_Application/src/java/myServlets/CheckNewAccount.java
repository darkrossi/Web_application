/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoel
 */
@WebServlet(name = "CheckNewAccount", urlPatterns = {"/checkNewAccount"})
public class CheckNewAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. 
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CheckNewAccount</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CheckNewAccount at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");*/
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String password = request.getParameter("password");
        String email = request.getParameter("mail");
        
        try {
            if(isFormValid(prenom, nom, password, email) && isNewUser(prenom, nom, password, email)){
                response.sendRedirect("login.html");
            } else{
                // si l'utilisateur est déjà enregistré dans la bdd
                response.sendRedirect("createCompte.html");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckNewAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckNewAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    static public boolean isFormValid(String prenom, String nom, String password, String mail){
        return true; // la vérification des champs est faite directement sur la page index.jsp
    }
    
    static public boolean isNewUser(String prenom, String nom, String password, String mail) throws ClassNotFoundException, SQLException{
        /*Class.forName("oracle.jdbc.OracleDriver");
        try (Connection Connexion = DriverManager.getConnection
                ("jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1","boedech","boedech")) { //?
            Statement State = Connexion.createStatement();
            ResultSet resultat = State.executeQuery("SELECT * FROM Users"); // ?
            while (resultat.next()) {
                if(resultat.getString("login").equals(prenom)){ // ?
                    Connexion.close();
                    return false;
                }
            }
        }*/ // à décommenter lorsque bdd opérationnelle
        return true;
    }
}

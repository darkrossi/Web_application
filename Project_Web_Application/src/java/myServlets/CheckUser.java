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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fournimi
 */
@WebServlet(name = "CheckUser", urlPatterns = {"/checkuser"})
public class CheckUser extends HttpServlet {

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
        Cookie cookie = new Cookie("utilisateur", request.getParameter("login"));
        response.addCookie(cookie);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>" + request.getParameter("mail") + "</p>");
            out.println("<h3><a href=\"index.html\">Index</a></h3>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String username = request.getParameter("mail");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        try {
            if (isLoginValid(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("utilisateur", username);
                response.sendRedirect("index.html");
            } else {
                response.sendRedirect("BadLogin.html");
            }
        } catch (ClassNotFoundException ex){
            processRequest(request, response);
        } catch (SQLException ex) {
            //processRequest(request, response);
        }
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

    static public boolean isLoginValid(String username, String password) throws ClassNotFoundException, SQLException {
        
        //devel lines
        if (username.equals("admin") && password.equals("admin")){
            return true;
        }
        
        Class.forName("oracle.jdbc.OracleDriver");
        Connection Connexion = DriverManager.getConnection("jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1", "fournimi", "fournimi");

        Statement State = Connexion.createStatement();
        ResultSet resultat = State.executeQuery("SELECT * FROM Users");

        while (resultat.next()) {
            if (resultat.getString("login").equals(username)
                    && resultat.getString("password").equals(password)) {
                Connexion.close();
                return true;
            }
        }
        Connexion.close();
        return false;
    }

}

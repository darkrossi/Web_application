/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import modele.Place;
import modele.Rang;

/**
 *
 * @author hoel
 */
@WebServlet(name = "PrintPDF", urlPatterns = {"/printPDF"})
public class PrintPDF extends HttpServlet {

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

        try {

            String login = request.getParameter("login");
            String ND = request.getParameter("ND");
            String NR = request.getParameter("NR");
            String NT = request.getParameter("NT");
            String NbP = request.getParameter("NbP");
            String NomSpectacle = request.getParameter("NomSpectacle");
            String Date = request.getParameter("Date");
            String Heure = request.getParameter("Heure");

            List<Place> places = (List<Place>) request.getAttribute("places");
            List<Rang> rangs = (List<Rang>) request.getAttribute("rangs");

            Document document = new Document(PageSize.A6);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            //définition des polices
            Font font0 = new Font(Font.COURIER);
            font0.setStyle(Font.UNDERLINE);
            font0.setStyle(Font.ITALIC);

            Font font1 = new Font(Font.COURIER);
      //font1.setStyle(Font.BOLD);

            Font font3 = new Font(Font.COURIER);
            font3.setStyle(Font.BOLD);
            font3.setStyle(Font.ITALIC);

            Font font4;
            font4 = new Font(Font.COURIER, 8, Font.BOLDITALIC);

            //paragraphe
            Paragraph paragraphNT = new Paragraph();
            paragraphNT.setFont(font0);
            paragraphNT.add("Ticket n°" + NT);
            paragraphNT.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphNT);
            document.add(Chunk.NEWLINE);

            //paragraphe
            Paragraph paragraphND = new Paragraph();
            paragraphND.setFont(font1);
            paragraphND.add("Dossier n°" + ND);
            //paragraphND.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphND);

            //paragraphe
            Paragraph paragraphNR = new Paragraph();
            paragraphNR.setFont(font1);
            paragraphNR.add("Réservation n°" + NR);
            document.add(paragraphNR);
            document.add(Chunk.NEWLINE);

            //paragraphe
            Paragraph paragraphLogin = new Paragraph();
            paragraphLogin.setFont(font1);
            paragraphLogin.add("Votre login : " + login);
            document.add(paragraphLogin);

            //paragraphe
            Paragraph paragraphNbP = new Paragraph();
            paragraphNbP.setFont(font1);
            paragraphNbP.add("Nombre de places réservées : " + NbP);
            document.add(paragraphNbP);
            document.add(Chunk.NEWLINE);

            //paragraphe
            Paragraph paragraphNomSpectacle = new Paragraph();
            paragraphNomSpectacle.setFont(font3);
            paragraphNomSpectacle.add(NomSpectacle);
            document.add(paragraphNomSpectacle);

            //paragraphe
            Paragraph paragraphDate = new Paragraph();
            paragraphDate.setFont(font1);
            paragraphDate.add(Date);
            paragraphDate.add(" à " + Heure);
            document.add(paragraphDate);
            document.add(Chunk.NEWLINE);

            Set hashNP = new HashSet();

            //affichage des places
            if (!places.isEmpty()) {
                Paragraph paragraphPlaces = new Paragraph();
                paragraphPlaces.setFont(font4);
                for (int i = 0; i < places.size(); i++) {
                    if (!hashNP.contains(places.get(i).getNP())) {
                        String NumPl = Integer.toString(places.get(i).getNumPl());
                        String NRa = Integer.toString(places.get(i).getNRa());
                        String PrixCT = Integer.toString(rangs.get(i).getPrixCT());
                        paragraphPlaces.add("Place n°" + NumPl);
                        paragraphPlaces.add(" Rang n°" + NRa);
                        paragraphPlaces.add(" Prix de la place : " + PrixCT);
                        hashNP.add(places.get(i).getNP());
                    }
                }
                document.add(paragraphPlaces);
            }

            document.close();

            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            response.setContentType("application/pdf");

            response.setContentLength(baos.size());

            ServletOutputStream out = response.getOutputStream();
            baos.writeTo(out);
            out.flush();

        } catch (DocumentException | IOException e2) {
            System.out.println("Error in " + getClass().getName() + "\n" + e2);
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

}

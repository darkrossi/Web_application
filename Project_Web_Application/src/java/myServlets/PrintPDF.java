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
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.net.MalformedURLException;
import javax.servlet.ServletOutputStream;

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
      

      Document document = new Document(PageSize.A6);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PdfWriter.getInstance(document, baos);
      document.open();
      
      //définition des polices
      Font font0 = new Font(Font.COURIER);
      font0.setStyle(Font.UNDERLINE);
      font0.setStyle(Font.ITALIC);
      
      Font font1 = new Font(Font.COURIER);
      font1.setStyle(Font.BOLD);
                    
      //titre centré
      Paragraph paragraph = new Paragraph();
      paragraph.setFont(font0);
      paragraph.add("Votre ticket");
      paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);
      document.add(Chunk.NEWLINE);
      document.add(Chunk.NEWLINE);
      
      //paragraphe 1
      Paragraph paragraphND = new Paragraph();
      paragraphND.setFont(font1);
      paragraphND.add("N° de dossier : " + ND);
      //paragraphND.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraphND);    
      document.add(Chunk.NEWLINE);
      
      //paragraphe 2
      Paragraph paragraphNR = new Paragraph();
      paragraphNR.setFont(font1);
      paragraphNR.add("N° de réservation : " + NR);
      //paragraphND.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraphNR);    
      document.add(Chunk.NEWLINE);
      
      //paragraphe 3
      Paragraph paragraphLogin = new Paragraph();
      paragraphLogin.setFont(font1);
      paragraphLogin.add("Votre login : " + login);
      //paragraphND.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraphLogin);    
      document.add(Chunk.NEWLINE);    
      document.close();

      response.setHeader("Expires", "0");
      response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
      response.setHeader("Pragma", "public");

      response.setContentType("application/pdf");

      response.setContentLength(baos.size());

      ServletOutputStream out = response.getOutputStream();
      baos.writeTo(out);
      out.flush();

    } catch (Exception e2) {
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

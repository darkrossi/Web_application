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
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
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
      
      Chunk chunk = new Chunk("Votre ticket");
      Font font = new Font(Font.COURIER);
      font.setStyle(Font.UNDERLINE);
      font.setStyle(Font.ITALIC);
      chunk.setFont(font);
      chunk.setBackground(Color.CYAN);
      document.add(chunk);
                        
      Paragraph paragraph = new Paragraph();
      paragraph.add("Hello World");
      paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);
      
      document.add(Chunk.NEWLINE);
      document.add(new Paragraph("N° de dossier : " + ND));
      document.add(Chunk.NEWLINE);
      document.add(new Paragraph("N° de réservation : " + NR));
      document.add(Chunk.NEWLINE);
      document.add(new Paragraph("Login : " + login));
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

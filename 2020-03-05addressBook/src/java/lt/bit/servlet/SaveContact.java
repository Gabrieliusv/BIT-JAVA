/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Contact;
import lt.bit.data.DB;

/**
 *
 * @author Gabrielius
 */
@WebServlet(name = "SaveContact", urlPatterns = {"/saveContact"})
public class SaveContact extends HttpServlet {

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
        String id = request.getParameter("id");
        String contactId = request.getParameter("contactId");
        Contact c = null;
        if (contactId != null) {
            try {
                c = DB.getContact(new Integer(contactId));
                if (c == null) {
                    response.sendRedirect("contacts.jsp?id=" + id);
                    return;
                }
            } catch (Exception e) {
                response.sendRedirect("contacts.jsp?id=" + id);
                return;
            }
        } else {
            c = new Contact();
        }

        c.setContact(request.getParameter("contact"));
        c.setType(request.getParameter("type"));

        if (contactId == null) {
            DB.addPersonContact(new Integer(id), c);
        } else {
            DB.updateContact(c);
        }

        response.sendRedirect("contacts.jsp?id=" + id);
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

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
import lt.bit.data.Address;
import lt.bit.data.DB;

/**
 *
 * @author Gabrielius
 */
@WebServlet(name = "SaveAddress", urlPatterns = {"/saveAddress"})
public class SaveAddress extends HttpServlet {

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
        String addressId = request.getParameter("addressId");
        Address a = null;
        if (addressId != null) {
            try {
                a = DB.getAddress(new Integer(addressId));
                if (a == null) {
                    response.sendRedirect("addresses.jsp?id=" + id);
                    return;
                }
            } catch (Exception e) {
                response.sendRedirect("addresses.jsp?id=" + id);
                return;
            }
        } else {
            a = new Address();
        }

        a.setAddress(request.getParameter("address"));
        a.setCity(request.getParameter("city"));
        a.setPostalCode(request.getParameter("postalCode"));

        if (addressId == null) {
            DB.addPersonAddress( new Integer(id), a);
        } else {
            DB.updateAddress(a);
        }

        response.sendRedirect("addresses.jsp?id=" + id);
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

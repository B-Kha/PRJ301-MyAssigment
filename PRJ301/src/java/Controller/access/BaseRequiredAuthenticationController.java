/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.access;

import model.accesscontrol.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="BaseRequiredAuthenticationController", urlPatterns={"/BaseRequiredAuthenticationController"})
public abstract class BaseRequiredAuthenticationController extends HttpServlet {
   
   private boolean isAuthenticated(HttpServletRequest request)
    {
        return request.getSession().getAttribute("account") != null;
    }
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         if(isAuthenticated(request))
        {
            //do business logic
            doPost(request, response, (User)request.getSession().getAttribute("account"));
        }
        else
            response.sendError(403, "You do not have right to access this page.");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if(isAuthenticated(request))
        {
            //do business logic
            doGet(request, response, (User)request.getSession().getAttribute("account"));
        }
        else
            response.sendError(403, "You do not have right to access this page.");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;
     protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;
}

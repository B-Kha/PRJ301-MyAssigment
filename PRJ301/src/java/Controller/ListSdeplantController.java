/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.SdeplantDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Sdeplant;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="ListSdeplantController", urlPatterns={"/sdeplant/list"})
public class ListSdeplantController extends HttpServlet {
   
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            SdeplantDBContext db = new SdeplantDBContext();
            ArrayList<Sdeplant> sdeplants = db.list();
            request.setAttribute("sdeplants", sdeplants);
            request.getRequestDispatcher("../view/sdeplant/list.jsp").forward(request, response);
        } else {
            response.sendRedirect("../login.html");
        }
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      
    }

  
    @Override
    public String getServletInfo() {
        return "ListSdeplantController";
    }// </editor-fold>

}

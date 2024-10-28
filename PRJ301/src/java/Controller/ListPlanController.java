/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.PlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Plan;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="ListPlanController", urlPatterns={"/plan/list"})
public class ListPlanController extends HttpServlet {
   
    
     

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            PlanDBContext db = new PlanDBContext();
            ArrayList<Plan> plans = db.list();
            request.setAttribute("plans", plans);
            request.getRequestDispatcher("...view/productionplan/list.jsp").forward(request, response);
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
       return "ListPlanController";
    }// </editor-fold>

}

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
import model.Plan;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="DeletePlanController", urlPatterns={"/plan/delete"})
public class DeletePlanController extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        PlanDBContext planDB = new PlanDBContext();
        Plan plan = planDB.get(id);

        request.setAttribute("plan", plan);
        request.getRequestDispatcher("/view/productionplan/delete.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        PlanDBContext planDB = new PlanDBContext();
        Plan plan = new Plan();
        plan.setId(id);
        planDB.delete(plan);

        response.sendRedirect(request.getContextPath() + "/plan/viewList");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

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
import java.time.LocalDate;
import java.util.Date;
import model.Department;
import model.Plan;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="UpdatePlanController", urlPatterns={"/plan/update"})
public class UpdatePlanController extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          int id = Integer.parseInt(request.getParameter("id"));
        
        PlanDBContext planDB = new PlanDBContext();
        Plan plan = planDB.get(id); // Phương thức get để lấy thông tin chi tiết của Plan
        
        request.setAttribute("plan", plan);
        request.getRequestDispatcher("/view/productionplan/update.jsp").forward(request, response);
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
      int id = Integer.parseInt(request.getParameter("id"));

    LocalDate startDate = LocalDate.parse(request.getParameter("start"));
    LocalDate endDate = LocalDate.parse(request.getParameter("end"));

    // Chuyển đổi LocalDate sang java.sql.Date
    java.sql.Date start = java.sql.Date.valueOf(startDate);
    java.sql.Date end = java.sql.Date.valueOf(endDate);

    int did = Integer.parseInt(request.getParameter("did"));

    // Tạo đối tượng Plan để cập nhật
    Plan plan = new Plan();
    plan.setId(id);
    plan.setStart(start);
    plan.setEnd(end);

    Department dept = new Department();
    dept.setId(did);
    plan.setDept(dept);

    PlanDBContext db = new PlanDBContext();
    db.update(plan);

    response.sendRedirect(request.getContextPath() + "/plan/viewList");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

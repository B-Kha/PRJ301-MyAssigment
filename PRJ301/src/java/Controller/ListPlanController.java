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
@WebServlet(name = "ListPlanController", urlPatterns = {"/plan/viewList"})
public class ListPlanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        PlanDBContext planDB = new PlanDBContext();
        ArrayList<Plan> plans = planDB.list();

        // Kiểm tra xem danh sách plans có dữ liệu không
        System.out.println("Number of plans: " + plans.size());
        for (Plan plan : plans) {
            System.out.println("Plan ID: " + plan.getId() + ", Start: " + plan.getStart() + ", End: " + plan.getEnd());
        }

        request.setAttribute("plans", plans);
        request.getRequestDispatcher("/view/productionplan/list.jsp").forward(request, response);

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

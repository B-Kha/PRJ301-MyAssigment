/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Plan;
import model.PlanCampain;
import model.Product;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="ProductionPlanCreateController", urlPatterns={"/productionplan/create"})
public class ProductionPlanCreateController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            ProductDBContext dbProduct = new ProductDBContext();
            DepartmentDBContext dbDept = new DepartmentDBContext();
            request.setAttribute("products", dbProduct.list());
            request.setAttribute("depts", dbDept.get("workshop"));
            request.getRequestDispatcher("../view/productionplan/create.jsp").forward(request, response);
        } else {
            response.sendRedirect("../login.html");
        }
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            String[] pids = request.getParameterValues("pid");

            Plan plan = new Plan();
            plan.setStart(Date.valueOf(request.getParameter("from")));
            plan.setEnd(Date.valueOf(request.getParameter("to")));

            Department d = new Department();
            d.setId(Integer.parseInt(request.getParameter("did")));

            plan.setDept(d);
            plan.setCampains(new ArrayList<>());

            for (String pid : pids) {
                Product p = new Product();
                p.setId(Integer.parseInt(pid));

                PlanCampain c = new PlanCampain();
                c.setProduct(p);
                String raw_quantity = request.getParameter("quantity" + pid);
                String raw_effort = request.getParameter("effort" + pid);
                c.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
                c.setEstimatedeffort(raw_effort != null && raw_effort.length() > 0 ? Float.parseFloat(raw_effort) : 0);
                c.setPlan(plan);
                if (c.getQuantity() != 0 && c.getEstimatedeffort() != 0)
                    plan.getCampains().add(c);
            }

            if (plan.getCampains().size() > 0) {
                PlanDBContext db = new PlanDBContext();
                db.insert(plan);
                response.getWriter().println("created a new plan!");
            } else {
                response.getWriter().println("your plan did not have any campains");
            }
        } else {
            response.sendRedirect("../login.html");
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

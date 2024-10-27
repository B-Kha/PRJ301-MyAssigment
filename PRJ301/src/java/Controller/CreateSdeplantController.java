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
import java.util.Date;
import model.PlanCampain;
import model.Sdeplant;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="CreateSdeplantController", urlPatterns={"/sdeplant/create"})
public class CreateSdeplantController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            request.getRequestDispatcher("../view/sdeplant/create.jsp").forward(request, response);
        } else {
            response.sendRedirect("../login.html");
        }       
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            int planCampainId = Integer.parseInt(request.getParameter("planCampainId"));
            String dateString = request.getParameter("date");
            String K = request.getParameter("K");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date != null) {
                Sdeplant sdeplant = new Sdeplant();
                PlanCampain planCampain = new PlanCampain();
                planCampain.setId(planCampainId);
                sdeplant.setPlanCampain(planCampain);
                sdeplant.setDate(date);
                sdeplant.setK(K);
                sdeplant.setQuantity(quantity);

                SdeplantDBContext db = new SdeplantDBContext();
                db.insert(sdeplant);
                response.getWriter().println("created a new schedule!");
            } else {
                response.getWriter().println("Error: Invalid date format.");
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

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
import java.text.ParseException;
import model.Sdeplant;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.PlanCampain;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="UpdateSdeplantController", urlPatterns={"/sdeplant/update"})
public class UpdateSdeplantController extends HttpServlet {
   
   
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            int scid = Integer.parseInt(request.getParameter("scid"));
            SdeplantDBContext db = new SdeplantDBContext();
            Sdeplant sdeplant = db.get(scid); // Giả sử bạn có phương thức get(int scid) trong SdeplantDBContext để lấy dữ liệu của một campain.
            request.setAttribute("sdeplant", sdeplant);
            request.getRequestDispatcher("../view/sdeplant/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("../login.html");
        }
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            int scid = Integer.parseInt(request.getParameter("scid"));
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
                sdeplant.setId(scid);
                PlanCampain planCampain = new PlanCampain();
                planCampain.setId(planCampainId);
                sdeplant.setPlanCampain(planCampain);
                sdeplant.setDate(date);
                sdeplant.setK(K);
                sdeplant.setQuantity(quantity);

                SdeplantDBContext db = new SdeplantDBContext();
                db.update(sdeplant);
                response.getWriter().println("updated the schedule!");
            } else {
                response.getWriter().println("Error: Invalid date format.");
            }
        } else {
            response.sendRedirect("../  login.html");
        }
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

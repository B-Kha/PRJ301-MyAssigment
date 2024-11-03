/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.WorkerScheduleDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Employee;
import model.Sdeplant;
import model.WorkerSchedule;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="CreateWorkerScheduleController", urlPatterns={"/workerschedule/create"})
public class CreateWorkerScheduleController extends HttpServlet {
   
    
   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            request.getRequestDispatcher("../view/workerschedule/create.jsp").forward(request, response);
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
            int eid = Integer.parseInt(request.getParameter("eid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            WorkerSchedule workerSchedule = new WorkerSchedule();
            Sdeplant sdeplant = new Sdeplant();
            sdeplant.setId(scid);
            workerSchedule.setSdeplant(sdeplant);

            Employee empolyee = new Employee();
            empolyee.setEid(eid);
            workerSchedule.setEmpolyee(empolyee);

            workerSchedule.setQuantity(quantity);

            WorkerScheduleDBContext db = new WorkerScheduleDBContext();
            db.insert(workerSchedule);
            response.getWriter().println("created a new worker schedule!");
        } else {
            response.sendRedirect("../login.html");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

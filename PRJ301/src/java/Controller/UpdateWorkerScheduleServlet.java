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
import model.Employee;
import model.Sdeplant;
import model.WorkerSchedule;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="UpdateWorkerScheduleController", urlPatterns={"/workerschedule/update"})
public class UpdateWorkerScheduleServlet extends HttpServlet {
   
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
         int wsid = Integer.parseInt(request.getParameter("wsid"));
        WorkerScheduleDBContext db = new WorkerScheduleDBContext();
        WorkerSchedule ws = db.get(wsid);
        request.setAttribute("workerSchedule", ws);
        request.getRequestDispatcher("/view/workerschedule/update.jsp").forward(request, response);
    } 

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int wsid = Integer.parseInt(request.getParameter("wsid"));
        int scid = Integer.parseInt(request.getParameter("scid"));
        int eid = Integer.parseInt(request.getParameter("eid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        WorkerSchedule ws = new WorkerSchedule();
        ws.setWsid(wsid);

        Sdeplant sc = new Sdeplant();
        sc.setId(scid);
        ws.setSdeplant(sc);

        Employee e = new Employee();
        e.setEid(eid);
        ws.setEmpolyee(e);

        ws.setQuantity(quantity);

        WorkerScheduleDBContext db = new WorkerScheduleDBContext();
        db.update(ws);

        response.sendRedirect("/workerschedule/list");
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

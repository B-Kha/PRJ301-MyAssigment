/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;



import dal.WorkerScheduleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.WorkerSchedule;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name = "CreateWorkerScheduleController", urlPatterns = {"/create/workerschedule"})
public class CreateWorkerScheduleController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      // Check if user is logged in
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login page if not logged in
            return;
        }

        // Forward to create.jsp if user is logged in
        request.getRequestDispatcher("/view/workerschedule/create.jsp").forward(request, response);
    }    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form parameters
            int scid = Integer.parseInt(request.getParameter("scid"));
            int eid = Integer.parseInt(request.getParameter("eid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Create and set up WorkerSchedule object
            WorkerSchedule workerSchedule = new WorkerSchedule();
            workerSchedule.setScid(scid);
            workerSchedule.setEid(eid);
            workerSchedule.setQuantity(quantity);

            // Insert into database
            WorkerScheduleDBContext dbContext = new WorkerScheduleDBContext();
            dbContext.insert(workerSchedule);

            // Send success message and forward back to create.jsp
            request.setAttribute("message", "Create thành công!");
            request.getRequestDispatcher("/view/workerschedule/create.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(CreateWorkerScheduleController.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Đã xảy ra lỗi khi tạo Worker Schedule mới.");
        }
    
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="ListEmployeeController", urlPatterns={"/list/employee"})
public class ListEmployeeController extends HttpServlet {
   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Kiểm tra nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        EmployeeDBContext db = new EmployeeDBContext();
        ArrayList<Employee> employees = db.list();

        // Đặt danh sách nhân viên vào request attribute và chuyển tiếp đến trang list.jsp
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/view/employee/list.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

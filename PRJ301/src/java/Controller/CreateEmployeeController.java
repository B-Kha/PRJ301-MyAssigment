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
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="CreateEmployeeController", urlPatterns={"/create/employee"})
public class CreateEmployeeController extends HttpServlet {
   
   
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
        request.getRequestDispatcher("/view/employee/create.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       // Lấy thông tin từ form
        String name = request.getParameter("ename");
        Date dob = Date.valueOf(request.getParameter("edob"));
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        String jobTitle = request.getParameter("jobTitle");
        int did = Integer.parseInt(request.getParameter("did"));
        String address = request.getParameter("address");

        // Tạo đối tượng Employee và thiết lập các thuộc tính
        Employee employee = new Employee();
        employee.setEname(name);
        employee.setEdob(dob);
        employee.setSalary(salary);
        employee.setJobTitle(jobTitle);
        employee.setDid(did);
        employee.setAddress(address);

        // Thêm vào cơ sở dữ liệu
        EmployeeDBContext db = new EmployeeDBContext();
        db.insert(employee);

       // Thiết lập thông báo thành công và điều hướng lại đến trang tạo
        request.setAttribute("message", "Employee created successfully!");
        request.getRequestDispatcher("/view/employee/create.jsp").forward(request, response);
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

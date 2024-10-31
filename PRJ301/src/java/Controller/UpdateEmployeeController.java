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
import model.Employee;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="UpdateEmployeeController", urlPatterns={"/update/employee"})
public class UpdateEmployeeController extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int eid = Integer.parseInt(request.getParameter("eid"));
        EmployeeDBContext db = new EmployeeDBContext();
        Employee employee = db.get(eid);

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/view/employee/update.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Nhận dữ liệu từ form cập nhật
        int eid = Integer.parseInt(request.getParameter("eid"));
        String ename = request.getParameter("ename");
        Date edob = Date.valueOf(request.getParameter("edob"));
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        String jobTitle = request.getParameter("job_Title");
        int did = Integer.parseInt(request.getParameter("did"));
        String address = request.getParameter("address");

        Employee employee = new Employee();
        employee.setEid(eid);
        employee.setEname(ename);
        employee.setEdob(edob);
        employee.setSalary(salary);
        employee.setJobTitle(jobTitle);
        employee.setDid(did);
        employee.setAddress(address);

        EmployeeDBContext db = new EmployeeDBContext();
        db.update(employee);

        response.sendRedirect(request.getContextPath() + "/list/employee");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

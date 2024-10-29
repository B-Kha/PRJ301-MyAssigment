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
import java.sql.Date;
import java.time.LocalDate;
import model.PlanCampain;
import model.Sdeplant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="CreateSdeplantController", urlPatterns={"/sdeplant/create"})
public class CreateSdeplantController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      // Kiểm tra session xem người dùng đã đăng nhập chưa
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Điều hướng đến trang tạo mới Sdeplant_campain nếu người dùng đã đăng nhập
        request.getRequestDispatcher("/view/productionplan/sdeplant/create.jsp").forward(request, response);   
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          // Lấy thông tin từ form
        int comid = Integer.parseInt(request.getParameter("comid"));
        Date date = Date.valueOf(request.getParameter("date"));
        String k = request.getParameter("k");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Tạo PlanCampain với comid
        PlanCampain planCampain = new PlanCampain();
        planCampain.setId(comid);

        // Tạo Sdeplant và thiết lập các thuộc tính
        Sdeplant sdeplant = new Sdeplant();
        sdeplant.setPlanCampain(planCampain);
        sdeplant.setDate(date);
        sdeplant.setK(k);
        sdeplant.setQuantity(quantity);

        // Thêm vào cơ sở dữ liệu
        SdeplantDBContext db = new SdeplantDBContext();
        db.insert(sdeplant);

        // Chuyển hướng về trang danh sách Sdeplant hoặc trang chủ
        response.sendRedirect(request.getContextPath() + "/view/productionplan/sdeplant/list.jsp");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

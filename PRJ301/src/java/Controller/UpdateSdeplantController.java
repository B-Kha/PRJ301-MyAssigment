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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
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
         if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy ID của Sdeplant từ tham số
        int scid = Integer.parseInt(request.getParameter("scid"));
        
        // Lấy Sdeplant từ cơ sở dữ liệu
        SdeplantDBContext db = new SdeplantDBContext();
        Sdeplant sdeplant = db.get(scid);
        
        // Gửi Sdeplant đến trang update.jsp
        request.setAttribute("sdeplant", sdeplant);
        request.getRequestDispatcher("/view/sdeplant/update.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int scid = Integer.parseInt(request.getParameter("scid"));
        int comid = Integer.parseInt(request.getParameter("comid"));
        LocalDate localDate = LocalDate.parse(request.getParameter("date"));
        Date date = Date.valueOf(localDate);
        String k = request.getParameter("k");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Tạo đối tượng Sdeplant và thiết lập các thuộc tính
        Sdeplant sdeplant = new Sdeplant();
        sdeplant.setId(scid);
        PlanCampain planCampain = new PlanCampain();
        planCampain.setId(comid);
        sdeplant.setPlanCampain(planCampain);
        sdeplant.setDate(date);
        sdeplant.setK(k);
        sdeplant.setQuantity(quantity);

        // Cập nhật dữ liệu vào cơ sở dữ liệu
        SdeplantDBContext db = new SdeplantDBContext();
        db.update(sdeplant);

        // Chuyển hướng về trang danh sách sau khi cập nhật
        response.sendRedirect(request.getContextPath() + "/sdeplant/list");
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

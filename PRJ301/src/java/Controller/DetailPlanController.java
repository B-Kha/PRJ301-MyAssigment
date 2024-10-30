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
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import model.Sdeplant;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name="DetailPlanController", urlPatterns={"/plan/detail"})
public class DetailPlanController extends HttpServlet {
   
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy các tham số từ request
        int planId = Integer.parseInt(request.getParameter("id"));
        Date startDate = Date.valueOf(request.getParameter("start"));
        Date endDate = Date.valueOf(request.getParameter("end"));

        // Truy vấn danh sách Sdeplant từ cơ sở dữ liệu dựa trên ngày bắt đầu và ngày kết thúc
        SdeplantDBContext sdeplantDB = new SdeplantDBContext();
        ArrayList<Sdeplant> sdeplants = sdeplantDB.getSdeplantsByDateRange(startDate, endDate);

        // Đặt thuộc tính "sdeplants" để truyền tới trang JSP
        request.setAttribute("sdeplants", sdeplants);
        request.getRequestDispatcher("/view/sdeplant/list.jsp").forward(request, response);
    
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }

  
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

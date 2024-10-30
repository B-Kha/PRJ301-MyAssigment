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
import model.Sdeplant;

/**
 *
 * @author Laptop Acer
 */
@WebServlet(name = "DeleteSdeplantController", urlPatterns = {"/sdeplant/delete"})
public class DeleteSdeplantController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy ID của Sdeplant từ tham số
        int scid = Integer.parseInt(request.getParameter("scid"));

        // Tạo đối tượng Sdeplant và gọi phương thức delete trong SdeplantDBContext
        Sdeplant sdeplant = new Sdeplant();
        sdeplant.setId(scid);

        SdeplantDBContext db = new SdeplantDBContext();
        db.delete(sdeplant);

        // Chuyển hướng về trang danh sách sau khi xóa
        response.sendRedirect(request.getContextPath() + "/sdeplant/list");

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

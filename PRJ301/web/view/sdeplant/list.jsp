<%-- 
    Document   : list
    Created on : Oct 28, 2024, 3:41:35 PM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Sdeplant" %> <!-- Thay đổi theo package thực tế -->
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>List Sdeplant Campaigns</title>
</head>
<body>
    <h2>List Schedue plant Campaigns</h2>

    <%
        ArrayList<Sdeplant> sdeplants = (ArrayList<Sdeplant>) request.getAttribute("sdeplants");
        if (sdeplants == null || sdeplants.isEmpty()) {
            out.println("<p>Không có dữ liệu để hiển thị.</p>");
        } else {
    %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>K</th>
                <th>Quantity</th>
                <th>ComID</th>
                <th>Actions</th>
            </tr>
            <%
                for (Sdeplant s : sdeplants) {
            %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getDate() %></td>
                <td><%= s.getK() %></td>
                <td><%= s.getQuantity() %></td>
                <td><%= s.getPlanCampain().getId() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/sdeplant/update?scid=<%= s.getId() %>">Update</a> |
                    <a href="${pageContext.request.contextPath}/sdeplant/delete?scid=<%= s.getId() %>" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        }
    %>
</body>
</html>
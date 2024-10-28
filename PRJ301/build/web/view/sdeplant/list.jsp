<%-- 
    Document   : list
    Created on : Oct 28, 2024, 3:41:35 PM
    Author     : Laptop Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Sdeplant" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>List Campain</title>
</head>
<body>
    <div class="list-container">
        <h2>List Campain</h2>
        <table border="1">
            <tr>
                <th>SCID</th>
                <th>ComID</th>
                <th>Date</th>
                <th>K</th>
                <th>Quantity</th>
            </tr>
            <%
                ArrayList<Sdeplant> sdeplants = (ArrayList<Sdeplant>) request.getAttribute("sdeplants");
                for (Sdeplant s : sdeplants) {
            %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getPlanCampain().getId() %></td>
                <td><%= s.getDate() %></td>
                <td><%= s.getK() %></td>
                <td><%= s.getQuantity() %></td>
                <td><a href="update?scid=<%= s.getId() %>">Update</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
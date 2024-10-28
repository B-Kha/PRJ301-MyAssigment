<%-- 
    Document   : update
    Created on : Oct 28, 2024, 4:18:22 PM
    Author     : Laptop Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Sdeplant" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Update Campain</title>
</head>
<body>
    <div class="update-container">
        <h2>Update Campain</h2>
        <%
            Sdeplant sdeplant = (Sdeplant) request.getAttribute("sdeplant");
        %>
        <form action="update" method="post">
            <input type="hidden" name="scid" value="<%= sdeplant.getId() %>">
            <label for="planCampainId">PlanCampain ID:</label>
            <input type="text" id="planCampainId" name="planCampainId" required value="<%= sdeplant.getPlanCampain().getId() %>">
            <br>
            <label for="date">Date (yyyy-MM-dd):</label>
            <input type="text" id="date" name="date" required value="<%= sdeplant.getDate() %>">
            <br>
            <label for="K">K:</label>
            <input type="text" id="K" name="K" required value="<%= sdeplant.getK() %>">
            <br>
            <label for="quantity">Quantity:</label>
            <input type="text" id="quantity" name="quantity" required value="<%= sdeplant.getQuantity() %>">
            <br>
            <button type="submit">Update</button>
        </form>
    </div>
</body>
</html>

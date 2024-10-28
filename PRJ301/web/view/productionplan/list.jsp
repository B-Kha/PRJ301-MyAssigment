<%-- 
    Document   : list
    Created on : Oct 28, 2024, 5:23:02 PM
    Author     : Laptop Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Plan" %>
<%@ page import="model.Department" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>List Plan</title>
</head>
<body>
    <div class="list-container">
        <h2>List Plan</h2>
        <table border="1">
            <tr>
                <th>PLID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Department ID</th>
            </tr>
            <%
                ArrayList<Plan> plans = (ArrayList<Plan>) request.getAttribute("plans");
                for (Plan plan : plans) {
            %>
            <tr>
                <td><%= plan.getId() %></td>
                <td><%= plan.getStart() %></td>
                <td><%= plan.getEnd() %></td>
                <td><%= plan.getDept().getId() %></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>

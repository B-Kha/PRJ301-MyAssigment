<%-- 
    Document   : update
    Created on : Oct 28, 2024, 4:18:22 PM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Sdeplant" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Update Sdeplant</title>
</head>
<body>
    <h2>Update Sdeplant</h2>

    <%
        Sdeplant sdeplant = (Sdeplant) request.getAttribute("sdeplant");
        if (sdeplant == null) {
            out.println("<p>Sdeplant không tồn tại!</p>");
        } else {
    %>
    <form action="${pageContext.request.contextPath}/sdeplant/update" method="post">
        <input type="hidden" name="scid" value="<%= sdeplant.getId() %>" />

        <label for="comid">ComID:</label>
        <input type="number" id="comid" name="comid" value="<%= sdeplant.getPlanCampain().getId() %>" required><br/>

        <label for="date">Ngày:</label>
        <input type="date" id="date" name="date" value="<%= sdeplant.getDate() %>" required><br/>

        <label for="k">K:</label>
        <input type="text" id="k" name="k" value="<%= sdeplant.getK() %>" required><br/>

        <label for="quantity">Số lượng:</label>
        <input type="number" id="quantity" name="quantity" value="<%= sdeplant.getQuantity() %>" required><br/>

        <button type="submit">Update</button>
    </form>
    <%
        }
    %>
</body>
</html>

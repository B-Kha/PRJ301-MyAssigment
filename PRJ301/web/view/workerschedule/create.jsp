<%-- 
    Document   : create
    Created on : Nov 1, 2024, 10:27:25 PM
    Author     : Laptop Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Worker Schedule</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h2>Create Worker Schedule</h2>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

   <form action="<%= request.getContextPath() %>/create/workerschedule" method="POST">
        <label for="scid">Schedule Campaign ID:</label>
        <input type="number" id="scid" name="scid" required><br><br>

        <label for="eid">Employee ID:</label>
        <input type="number" id="eid" name="eid" required><br><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br><br>

        <input type="submit" value="Create Schedule">
    </form>
</body>
</html>
<%-- 
    Document   : delete
    Created on : Oct 29, 2024, 8:51:51 PM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title> Delete Plan</title>
</head>
<body>
    <h2> Delete Plan</h2>
    <p>Are you sure you want to delete a Plan with the following information?</p>
    
    <p><strong>ID:</strong> ${plan.id}</p>
    <p><strong>Star Day:</strong> ${plan.start}</p>
    <p><strong>End day:</strong> ${plan.end}</p>
    <p><strong>Department (ID):</strong> ${plan.dept.id}</p>

    <form action="${pageContext.request.contextPath}/plan/delete" method="post">
        <input type="hidden" name="id" value="${plan.id}" />
        <button type="submit">Delete</button>
        <a href="${pageContext.request.contextPath}/plan/viewList">Cancel</a>
    </form>
</body>
</html>

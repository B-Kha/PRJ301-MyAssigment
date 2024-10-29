<%-- 
    Document   : udpdate
    Created on : Oct 29, 2024, 2:42:59 PM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>UpDate Plan</title>
</head>
<body>
    <h2>Update Plan</h2>
    <form action="${pageContext.request.contextPath}/plan/update" method="post">
        <!-- Hidden input để giữ ID của Plan -->
        <input type="hidden" name="id" value="${plan.id}" />
        
        <label for="start">Ngày bắt đầu:</label>
        <input type="date" id="start" name="start" value="${plan.start}" required /><br/>
        
        <label for="end">Ngày kết thúc:</label>
        <input type="date" id="end" name="end" value="${plan.end}" required /><br/>
        
        <label for="did">Phòng ban (ID):</label>
        <input type="number" id="did" name="did" value="${plan.dept.id}" required /><br/>
        
        <button type="submit">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/plan/viewList">Quay lại danh sách</a>
    </form>
</body>
</html>

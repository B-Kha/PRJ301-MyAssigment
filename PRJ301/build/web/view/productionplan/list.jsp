<%-- 
    Document   : list
    Created on : Oct 29, 2024, 1:34:59 PM
    Author     : Laptop Acer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>List Plan</title>
</head>
<body>
    <h2>List Plan</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Phòng ban (ID)</th>
                <th>Hành động</th> <!-- Thêm cột hành động -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="plan" items="${plans}">
                <tr>
                    <td>${plan.id}</td>
                    <td>${plan.start}</td>
                    <td>${plan.end}</td>
                    <td>${plan.dept.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/plan/update?id=${plan.id}">Cập nhật</a> |
                        <a href="${pageContext.request.contextPath}/plan/delete?id=${plan.id}">Xóa</a> <!-- Thêm link xóa -->
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
<%-- 
    Document   : list
    Created on : Oct 31, 2024, 9:45:07 PM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>List of Employees</title>
</head>
<body>
    <h2>List of Employees</h2>
   <table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Salary</th>
            <th>Job Title</th>
            <th>Department ID</th>
            <th>Address</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.eid}</td>
                <td>${employee.ename}</td>
                <td>${employee.edob}</td>
                <td>${employee.salary}</td>
                <td>${employee.jobTitle}</td>
                <td>${employee.did}</td>
                <td>${employee.address}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/update/employee?eid=${employee.eid}">Update</a>
                    <a href="${pageContext.request.contextPath}/delete/employee?eid=${employee.eid}" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>

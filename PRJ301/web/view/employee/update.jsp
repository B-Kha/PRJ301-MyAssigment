<%-- 
    Document   : update
    Created on : Oct 31, 2024, 10:08:44 PM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
    <h2>Update Employee</h2>
    <form action="${pageContext.request.contextPath}/update/employee" method="post">
        <input type="hidden" name="eid" value="${employee.eid}" />
        Name: <input type="text" name="ename" value="${employee.ename}" /> <br/>
        Date of Birth: <input type="date" name="edob" value="${employee.edob}" /> <br/>
        Salary: <input type="number" name="salary" step="0.01" value="${employee.salary}" /> <br/>
        Job Title: <input type="text" name="job_Title" value="${employee.jobTitle}" /> <br/>
        Department ID: <input type="number" name="did" value="${employee.did}" /> <br/>
        Address: <input type="text" name="address" value="${employee.address}" /> <br/>
        <input type="submit" value="Update" />
    </form>
</body>
</html>

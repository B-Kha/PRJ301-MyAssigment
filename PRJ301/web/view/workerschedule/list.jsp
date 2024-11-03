<%-- 
    Document   : list
    Created on : Nov 4, 2024, 1:54:47 AM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Worker Schedule List</title>
</head>
<body>
    <h2>Worker Schedule List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>WSID</th>
                <th>Schedule Campaign ID</th>
                <th>Employee ID</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ws" items="${workerSchedules}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/workerschedule/update?wsid=${ws.wsid}">${ws.wsid}</a></td>
                    <td><a href="${pageContext.request.contextPath}/workerschedule/update?wsid=${ws.wsid}">${ws.sdeplant.id}</a></td>
                    <td><a href="${pageContext.request.contextPath}/workerschedule/update?wsid=${ws.wsid}">${ws.empolyee.eid}</a></td>
                    <td><a href="${pageContext.request.contextPath}/workerschedule/update?wsid=${ws.wsid}">${ws.quantity}</a></td>
                    <td><a href="${pageContext.request.contextPath}/workerschedule/update?wsid=${ws.wsid}">Update</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>


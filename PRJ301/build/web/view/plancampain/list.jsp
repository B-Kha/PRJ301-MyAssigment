<%-- 
    Document   : list
    Created on : Nov 4, 2024, 2:38:51 AM
    Author     : Laptop Acer
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Plan Campain List</title>
</head>
<body>
    <h2>Plan Campain List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ComID</th>
                <th>Plan ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Estimated Effort</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pc" items="${planCampains}">
                <tr>
                    <td>${pc.id}</td>
                    <td>${pc.plan.pid}</td>
                    <td>${pc.product.pid}</td>
                    <td>${pc.quantity}</td>
                    <td>${pc.estimatedeffort}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>


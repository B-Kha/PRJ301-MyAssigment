<%-- 
    Document   : create
    Created on : Oct 26, 2024, 10:53:50 AM
    Author     : Laptop Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tạo mới Sdeplant Campaign</title>
</head>
<body>
    <h2>Tạo mới Sdeplant Campaign</h2>
    <form action="${pageContext.request.contextPath}/sdeplant/create" method="post">
        <label for="comid">ComID:</label>
        <input type="number" id="comid" name="comid" required><br>

        <label for="date">Ngày:</label>
        <input type="date" id="date" name="date" required><br>

        <label for="k">K:</label>
        <input type="text" id="k" name="k" required><br>

        <label for="quantity">Số lượng:</label>
        <input type="number" id="quantity" name="quantity" required><br>

        <button type="submit">Tạo mới</button>
    </form>
</body>
</html>

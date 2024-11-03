<%-- 
    Document   : create
    Created on : Nov 4, 2024, 1:17:10 AM
    Author     : Laptop Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tạo WorkerSchedule</title>
</head>
<body>
    <div class="create-container">
        <h2>Tạo WorkerSchedule</h2>
        <form action="create" method="post">
            <label for="scid">Schedule Campaign ID:</label>
            <input type="text" id="scid" name="scid" required>
            <br>
            <label for="eid">Employee ID:</label>
            <input type="text" id="eid" name="eid" required>
            <br>
            <label for="quantity">Quantity:</label>
            <input type="text" id="quantity" name="quantity" required>
            <br>
            <button type="submit">Tạo WorkerSchedule</button>
        </form>
    </div>
</body>
</html>

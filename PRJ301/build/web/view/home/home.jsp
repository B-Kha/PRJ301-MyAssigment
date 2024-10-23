<%-- 
    Document   : home
    Created on : Oct 22, 2024, 1:03:15 PM
    Author     : Laptop Acer
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
    // Lấy tài khoản từ session
    User account = (User) session.getAttribute("account");
    if (account != null) {
%>
    <h1>Welcome, <%= account.getUsername() %>!</h1>
    <p>This is your home page.</p>
    <a href="create-plan">Go to Production Plan</a>
<%
    } else {
        // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
        response.sendRedirect("login.html");
    }
%>
</body>
</html>

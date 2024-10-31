<%-- 
    Document   : create
    Created on : Oct 31, 2024, 9:25:43 PM
    Author     : Laptop Acer
--%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Create Employee</title>
</head>
<body>
    <h2>Create New Employee</h2>

    <!-- Hi?n th? thông báo thành công n?u có -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/create/employee" method="POST">
        <label for="ename">Name:</label>
        <input type="text" name="ename" id="ename" required><br><br>

        <label for="edob">Date of Birth:</label>
        <input type="date" name="edob" id="edob" required><br><br>

        <label for="salary">Salary:</label>
        <input type="text" name="salary" id="salary" required><br><br>

        <label for="jobTitle">Job Title:</label>
        <input type="text" name="jobTitle" id="jobTitle" required><br><br>

        <label for="did">Department ID:</label>
        <input type="number" name="did" id="did" required><br><br>

        <label for="address">Address:</label>
        <input type="text" name="address" id="address"><br><br>

        <button type="submit">Create Employee</button>
    </form>
</body>
</html>

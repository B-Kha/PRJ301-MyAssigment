<%-- 
    Document   : home
    Created on : Oct 22, 2024, 1:03:15 PM
    Author     : Laptop Acer
--%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="home-container">
            <h2>Home Page</h2>
            <p>Welcome to Factory!</p>
            <a href="<%= request.getContextPath() %>/productionplan/create">Create new plan</a><br/>
            <a href="${pageContext.request.contextPath}/sdeplant/create">Create Schedueplant Campaign</a> <br/>
           <a href="${pageContext.request.contextPath}/sdeplant/list">List All Sdeplant Campaigns</a><br/>
            <a href="<%= request.getContextPath() %>/plan/viewList">List all Plans</a> <br/>
            <a href="${pageContext.request.contextPath}/create/employee">Create New Employee</a> <br/>
             <a href="<%= request.getContextPath() %>/list/employee">List of Employees</a><br/>
            <form action="logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </body>
</html>
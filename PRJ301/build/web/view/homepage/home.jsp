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
<a href="<%= request.getContextPath() %>/sdeplant/create">Create new Campaign</a><br/>
<a href="<%= request.getContextPath() %>/sdeplant/list">List all Campaign</a><br/>
<a href="<%= request.getContextPath() %>/plan/viewList">List all Plans</a>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>
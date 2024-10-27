<%-- 
    Document   : create
    Created on : Oct 26, 2024, 10:53:50 AM
    Author     : Laptop Acer
--%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Create Schedule</title>
</head>
<body>
    <div class="create-container">
        <h2>Create Schedule for Compain</h2>
        <form action="create" method="post">
            <label for="planCampainId">PlanCampain ID:</label>
            <input type="text" id="planCampainId" name="planCampainId" required>
            <br>
            <label for="date">Date (yyyy-MM-dd):</label>
            <input type="date" id="date" name="date" required pattern="\d{4}-\d{2}-\d{2}">
            <br>
            <label for="K">K:</label>
            <input type="text" id="K" name="K" required>
            <br>
            <label for="quantity">Quantity:</label>
            <input type="text" id="quantity" name="quantity" required>
            <br>
            <button type="submit">Create</button>
        </form>
    </div>
</body>
</html>
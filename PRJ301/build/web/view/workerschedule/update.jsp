<%-- 
    Document   : update
    Created on : Nov 4, 2024, 2:14:26 AM
    Author     : Laptop Acer
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Worker Schedule</title>
</head>
<body>
    <h2>Update Worker Schedule</h2>
    <form action="${pageContext.request.contextPath}/workerschedule/update" method="post">
        <input type="hidden" name="wsid" value="${workerSchedule.wsid}" />
        
        <label for="scid">Schedule Campaign ID:</label>
        <input type="text" id="scid" name="scid" value="${workerSchedule.sdeplant.id}" required /><br/>
        
        <label for="eid">Employee ID:</label>
        <input type="text" id="eid" name="eid" value="${workerSchedule.empolyee.eid}" required /><br/>
        
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${workerSchedule.quantity}" required /><br/>
        
        <button type="submit">Update</button>
    </form>
</body>
</html>

<%-- 
    Document   : updateRecord2
    Created on : Nov 3, 2015, 8:54:49 PM
    Author     : John Phillips
--%>

<%@page import="model.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Phillips' Employee Web</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Employee Web</a></h1>
        <h2>Update Employee Record</h2>
        <form action="update" method="get">
            <% Employee employee = (Employee) request.getAttribute("employee");%>
            Employee Id: <input type="text" name="empId" value="<%= employee.getEmpId() %>" readonly>
            <br><br>
            Last Name: <input type="text" name="lastName" size="30" value="<%= employee.getLastName() %>" required>
            <br><br>
            First Name: <input type="text" name="firstName" size="30" value="<%= employee.getFirstName() %>" required>
            <br><br>            
            Home Phone: <input type="text" name="homePhone" size="30" value="<%= employee.getHomePhone() %>" required>
            <br><br>
            Salary: <input type="number" name="salary" value="<%= employee.getSalary() %>" required>
            <br><br>

            <input type="hidden" name="action" value="updateRecord2">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>

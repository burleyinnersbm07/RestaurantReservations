<%@page import="java.util.List, model.Reservation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Restaurant Reservation System</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Restaurant Reservation System</a></h1>
        <h2>Reservation Report</h2>
        <%
            List<Reservation> mydata = (List<Reservation>) request.getAttribute("mydata");
            out.println("<table>");
            for (Reservation res : mydata) {
                out.println(res.inHTMLRowFormat());
            }
            out.println("</table>");
        %>
    </body>
</html>

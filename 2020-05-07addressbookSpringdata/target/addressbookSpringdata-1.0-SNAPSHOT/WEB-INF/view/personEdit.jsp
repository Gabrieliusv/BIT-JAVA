<%-- 
    Document   : personEdit
    Created on : May 4, 2020, 8:59:04 PM
    Author     : Gabrielius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="save">
            <% 
                if(request.getAttribute("person") != null){
                %>
                <input type="hidden" name="id" value="${person.id}">
                <% 
                    }
                %>
            First name: <input name="firstName" value="${person.firstName}">
            Last Name: <input name="lastName" value="${person.lastName}">
            Birth Date: <input name="birthDate" value="${person.birthDate}">
            Salary: <input name="salary" value="${person.salary}">
            <input type="submit" value="Save">
        </form>
            <a href="./">Cancel</a>
    </body>
</html>

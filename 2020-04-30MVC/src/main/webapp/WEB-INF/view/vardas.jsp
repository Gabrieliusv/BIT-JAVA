<%-- 
    Document   : vardas
    Created on : Apr 30, 2020, 3:43:08 PM
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
        <h1>Hello ${v} ${p}</h1>
        <form method="POST">
            <input name="vardas">
            <input type="submit">
        </form>
    </body>
</html>

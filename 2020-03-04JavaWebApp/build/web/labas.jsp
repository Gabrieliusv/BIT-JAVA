<%-- 
    Document   : labas
    Created on : Mar 4, 2020, 3:25:39 PM
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
        <h1>Hello World!</h1>
        <%=2 * 2%>
        <%=Math.random()%>
        <%="Labas" + " pasauli"%>
        <ul>
            <%
                for (int i = 1; i <= 10; i++) {
            %>
            <li><%=i%>
                <%
                 }
                %>
        </ul>
    </body>
</html>

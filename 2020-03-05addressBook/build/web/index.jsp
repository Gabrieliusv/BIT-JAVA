<%-- 
    Document   : index
    Created on : Mar 5, 2020, 4:06:40 PM
    Author     : Gabrielius
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.data.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Person p : DB.getPersons()) {
        %>
        id: <%=p.getId()%>
        Vardas: <%=p.getFirstName()%><br>
        Pavardė: <%=p.getLastName()%><br>
        Gimimo diena: <%=(p.getBirthDate() != null) ? sdf.format(p.getBirthDate()) : ""%><br>
        Alga: <%=p.getSalary()%><br>
        <a href="deletePerson?id=<%=p.getId()%>">Delete</a>
        <a href="edit.jsp?id=<%=p.getId()%>">Edit</a>
        <a href="addresses.jsp?id=<%=p.getId()%>">Addresses</a>
        <a href="contacts.jsp?id=<%=p.getId()%>">Contacts</a>
        <hr>
        <%
            }
        %>
        <a href="edit.jsp">Add</a>
    </body>
</html>

<%-- 
    Document   : addresses
    Created on : Mar 10, 2020, 4:03:18 PM
    Author     : Gabrielius
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.data.DB"%>
<%@page import="lt.bit.data.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            EntityManager em = (EntityManager) request.getAttribute("em");
            Integer id = null;
            Person p = null;
            try {
                id = new Integer(request.getParameter("id"));
                p = DB.getPerson(em, id);
                if (p == null) {
                    response.sendRedirect("index.jsp");
                    return;
                }
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
                return;
            }

        %>
        <h1><%=p.getFirstName() + " " + p.getLastName() + " adresai:"%></h1>
        <%
            for (Address a : DB.getPersonAddresses(em, id)) {
        %>
        Address: <%=a.getAddress()%><br>
        City: <%=a.getCity()%><br>
        Postal Code: <%=a.getPostalCode()%><br>
        <a href="deleteAddress?id=<%=id%>&addressId=<%=a.getId()%>">Delete</a>
        <a href="addressEdit.jsp?id=<%=id%>&addressId=<%=a.getId()%>">Edit</a>
        <hr>
        <%
            }
        %>
        <a href="addressEdit.jsp?id=<%=id%>">Add</a>
        <a href="index.jsp">Back</a>
    </body>
</html>

<%-- 
    Document   : contacts
    Created on : Mar 10, 2020, 4:03:31 PM
    Author     : Gabrielius
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data.Contact"%>
<%@page import="lt.bit.data.DB"%>
<%@page import="lt.bit.data.Person"%>
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
        <h1><%=p.getFirstName() + " " + p.getLastName() + " kontaktai:"%></h1>
        <%
            for (Contact c : DB.getPersonContacts(em, id)) {
        %>
        Contact: <%=c.getContact()%><br>
        Type: <%=c.getType()%><br>
        <a href="deleteContact?id=<%=id%>&contactId=<%=c.getId()%>">Delete</a>
        <a href="contactEdit.jsp?id=<%=id%>&contactId=<%=c.getId()%>">Edit</a>
        <hr>
        <%
            }
        %>
        <a href="contactEdit.jsp?id=<%=id%>">Add</a>
        <a href="index.jsp">Back</a>
    </body>
</html>

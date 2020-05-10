<%-- 
    Document   : contactEdit
    Created on : Mar 10, 2020, 4:10:06 PM
    Author     : Gabrielius
--%>

<%@page import="java.sql.Connection"%>
<%@page import="lt.bit.data.DB"%>
<%@page import="lt.bit.data.Contact"%>
<%@page import="lt.bit.data.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Person p = null;
            Contact c = null;
            String id = request.getParameter("id");
            String contactId = request.getParameter("contactId");
            try {
                Connection conn = (Connection) request.getAttribute("conn");
                p = DB.getPerson(conn, new Integer(id));
                if (contactId != null) {
                    c = DB.getContact(conn, new Integer(contactId));
                    if (c == null) {
                        response.sendRedirect("contacts.jsp?id=" + id);
                        return;
                    }
                }
            } catch (Exception e) {
                response.sendRedirect("contacts.jsp?id=" + id);
                return;
            }

        %>
        <h1><%=p.getFirstName() + " " + p.getLastName() + " kontaktas:"%></h1><br>
        <form method="POST" action="saveContact">
            <%if (c != null) {
            %>
            <input type="hidden" name="contactId" value="<%=contactId%>">
            <%
                }
            %>
            <input type="hidden" name="id" value="<%=id%>">
            Contact: <input name="contact" value="<%=(c != null) ? c.getContact(): ""%>"><br>
            Type: <input name="type" value="<%=(c != null) ? c.getType(): ""%>"><br>
            <input type="submit" value="Save">
        </form>
        <a href="contacts.jsp?id=<%=id%>">Cancel</a>
    </body>
</html>

<%-- 
    Document   : addressEdit
    Created on : Mar 10, 2020, 4:09:42 PM
    Author     : Gabrielius
--%>

<%@page import="java.sql.Connection"%>
<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.data.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Person p = null;
            Address a = null;
            String id = request.getParameter("id");
            String addressId = request.getParameter("addressId");
            try {
                Connection conn = (Connection) request.getAttribute("conn");
                p = DB.getPerson(conn, new Integer(id));
                if (addressId != null) {
                    a = DB.getAddress(conn, new Integer(addressId));
                    if (a == null) {
                        response.sendRedirect("addresses.jsp?id=" + id);
                        return;
                    }
                }
            } catch (Exception e) {
                response.sendRedirect("addresses.jsp?id=" + id);
                return;
            }

        %>
    <h1><%=p.getFirstName() + " " + p.getLastName() + " adresas:"%></h1><br>
    <form method="POST" action="saveAddress">
        <%if (a != null) {
        %>
        <input type="hidden" name="addressId" value="<%=addressId%>">
        <%
            }
        %>
        <input type="hidden" name="id" value="<%=id%>">
        Address: <input name="address" value="<%=(a != null) ? a.getAddress() : ""%>"><br>
        City: <input name="city" value="<%=(a != null) ? a.getCity() : ""%>"><br>
        Postal Code: <input name="postalCode" value="<%=(a != null) ? a.getPostalCode() : ""%>"><br>
        <input type="submit" value="Save">
    </form>
    <a href="addresses.jsp?id=<%=id%>">Cancel</a>
</head>
<body>
</body>
</html>

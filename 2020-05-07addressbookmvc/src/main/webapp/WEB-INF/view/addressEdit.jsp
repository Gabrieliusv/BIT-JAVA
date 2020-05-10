<%-- 
    Document   : addressEdit
    Created on : May 4, 2020, 8:59:04 PM
    Author     : Gabrielius
--%>

<%@page import="lt.bit.addressbookmvc.util.Util"%>
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
                if(request.getAttribute("address") != null){
                %>
                <input type="hidden" name="id" value="${address.id}">
                <% 
                    }
                %>
            Address: <input name="address" value="${address.address}">
            City: <input name="city" value="${address.city}">
            Postal code: <input name="postalCode" value="${address.postalCode}">
            <input type="submit" value="Save">
        </form>
            <a href="<%=Util.requestPath(request)%>..">Cancel</a>
    </body>
</html>

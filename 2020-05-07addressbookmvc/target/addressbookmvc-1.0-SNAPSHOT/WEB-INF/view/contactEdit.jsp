<%-- 
    Document   : contactEdit
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
                if(request.getAttribute("contact") != null){
                %>
                <input type="hidden" name="id" value="${contact.id}">
                <% 
                    }
                %>
            Contact: <input name="contact" value="${contact.contact}">
            Contact Type: <input name="contactType" value="${contact.contactType}">
            <input type="submit" value="Save">
        </form>
            <a href="<%=Util.requestPath(request)%>..">Cancel</a>
    </body>
</html>

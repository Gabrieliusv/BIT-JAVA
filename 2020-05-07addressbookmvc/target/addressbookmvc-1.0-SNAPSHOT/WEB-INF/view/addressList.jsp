<%-- 
    Document   : addressList
    Created on : May 4, 2020, 8:26:22 PM
    Author     : Gabrielius
--%>

<%@page import="lt.bit.addressbookmvc.util.Util"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach var="address" items="${list}">
            ${address.address}  ${address.city}  ${address.postalCode}
            <a href="<%=Util.requestPath(request)%>edit?id=${address.id}">Edit</a>
            <a href="<%=Util.requestPath(request)%>delete?id=${address.id}">Delete</a>
            <hr>
        </c:forEach>
        <a href="<%=Util.requestPath(request)%>edit">Add Address</a>
        <a href="<%=Util.requestPath(request)%>../..">Back</a>
    </body>
</html>

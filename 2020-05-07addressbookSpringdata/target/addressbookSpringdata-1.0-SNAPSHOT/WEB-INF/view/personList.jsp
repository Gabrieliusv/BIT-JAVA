<%-- 
    Document   : personList
    Created on : May 4, 2020, 8:26:22 PM
    Author     : Gabrielius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Filter: <form method="POST">
            <input name="filter">
            <input type="submit" value="filter">
        </form>
        <c:forEach var="person" items="${list}">
            ${person.firstName}  ${person.lastName}  ${person.birthDate}  ${person.salary} 
            <a href="edit?id=${person.id}">Edit</a>
            <a href="delete?id=${person.id}">Delete</a>
            <a href="${person.id}/address">Addresses</a>
            <a href="${person.id}/contact">Contacts</a>
            <hr>
        </c:forEach>
        <a href="edit">Add Person</a>
    </body>
</html>

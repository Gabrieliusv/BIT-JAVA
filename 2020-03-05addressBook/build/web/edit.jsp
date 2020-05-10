<%-- 
    Document   : edit
    Created on : Mar 5, 2020, 4:47:32 PM
    Author     : Gabrielius
--%>

<%@page import="java.text.SimpleDateFormat"%>
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
            String ids = request.getParameter("id");
            Integer id = null;
            Person p = null;
            if (ids != null) {
                try {
                    id = new Integer(ids);
                    p = DB.getPerson(id);
                    if (p == null) {
                        response.sendRedirect("index.jsp");
                        return;
                    }
                } catch (Exception e) {
                    response.sendRedirect("index.jsp");
                    return;
                }
            }
//            if (ids != null && p == null) {
//                response.sendRedirect("index.jsp");
//                return;
//            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        %>
        <form method="POST" action="savePerson">
            <%if (p != null) {
            %>
            <input type="hidden" name="id" value="<%=p.getId()%>">
            <%
                }
            %>
            Vardas: <input name="fn" value="<%=(ids != null) ? p.getFirstName() : ""%>">
            Pavarde: <input name="ln" value="<%=(ids != null) ? p.getLastName() : ""%>">
            Gimimo data: <input name="bd" value="<%=(ids != null && p.getBirthDate() != null) ? sdf.format(p.getBirthDate()) : ""%>">
            Alga: <input name="salary" value="<%=(ids != null && p.getSalary() != null) ? p.getSalary() : ""%>">
            <input type="submit" value="Save">
        </form>
        <a href="index.jsp">Cancel</a>
    </body>
</html>

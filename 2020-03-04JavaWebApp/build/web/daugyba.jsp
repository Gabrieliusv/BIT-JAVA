<%-- 
    Document   : daugyba
    Created on : Mar 4, 2020, 4:03:42 PM
    Author     : Gabrielius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daugybos lentelė</title>
    </head>
    <body>
        <%
            String ns = request.getParameter("nuo");
            int nuo = 1;
            try {
                nuo = new Integer(ns);
            } catch (Exception ex) {
                //ignored
            }
            String is = request.getParameter("iki");
            int iki = 10;
            try {
                iki = new Integer(is);
            } catch (Exception ex) {
                //ignored
            }
        %>
        <form method="POST">
            Nuo: <input type="number" name ="nuo" value="<%=nuo%>"><br>
            Iki: <input type="number" name="iki" value="<%=iki%>"><br>
            <input type="submit">
        </form>
        <hr>
        </form>
        <table>
            <tr>
                <th></th>
                    <%
                        for (int i = nuo; i <= iki; i++) {
                    %>
                <th><%=i%></th>
                    <%}%>
            </tr>
        <%
            for (int i = nuo; i <= iki; i++) {
        %>
        <tr>
            <td><b><%=i%></b></td>
            <%
                for (int j = nuo; j <= iki; j++) {
            %>
            <td><%=i * j%></td>
            <%}%>
        </tr>
        <%}%>
    </table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: markus
  Date: 13.12.18
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>First JSP</title></head>
<body>
<%
    double num = Math.random();
    if (num > 0.95) {
%>
<h2>You'll have a luck day!</h2><p>(<%= num %>)</p>
<%
} else {
%>
<h2>Well, life goes on ... </h2><p>(<%= num %>)</p>
<%
    }
%>
<a href="<%= request.getRequestURI() %>"><h3>Try Again</h3></a>
</body>
</html>
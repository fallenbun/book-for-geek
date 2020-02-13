<%--
  Created by IntelliJ IDEA.
  User: Dasha
  Date: 08.05.2019
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="customer" scope="request" type="store.models.Customer"/>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome, ${customer.firstName}
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  Customer: Dasha
  Date: 12.05.2019
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="customer" scope="request" class="store.models.Customer"/>
<html>
<head>
    <title>Edit customer</title>
</head>
<body>
<h1>Edit customer ${customer.firstName} ${customer.lastName}</h1>
<form:form modelAttribute="editUser" method="POST" action="/admin/users/edit/${customer.customerId}">
    <table>
        <tr>
            <td><form:label path="customerId">ID:</form:label></td>
            <td><form:input path="customerId" disabled="true"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name:</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>

        <tr>
            <td><form:label path="phone">Phone</form:label></td>
            <td><form:input path="phone"/></td>
        </tr>

        <tr>
            <td><form:label path="email">E-mail</form:label></td>
            <td><form:input path="email"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>
</body>
</html>

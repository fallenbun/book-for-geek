<%--
  Created by IntelliJ IDEA.
  User: Dasha
  Date: 29.04.2019
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<form method="POST" action="signin">
    <table>
        <tr>
            <td><label>Login</label> </td>
            <td><input id="loginfield" type="tel" firstName="login"></td>
        </tr>
         <td><label>password</label> </td>
            <td><input id="pass" type="password" firstName="password"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="login"></td>
        </tr>
    </table>
<%--    Login: <input type="text" firstName="username"><br>--%>
<%--    Password: <input type="password" firstName="password"><br>--%>
<%--    <input type="submit"value="login">--%>
</form>
</body>
</html>

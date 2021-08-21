<%-- 
    Document   : register
    Created on : Mar 23, 2020, 8:38:37 PM
    Author     : KHANHBQSE63463
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <a href="DispatcherServlet">Home</a>
        <form method="POST" action="DispatcherServlet">
            <p>Username</p>
            ${requestScope.USERNAME}
            <input type="text" maxlength="10" value="${param.username}" minlength="3" name="username">
            <p>Password</p>
            ${requestScope.PASSWORD}
            <input type="password" minLength="5" maxLength="45" name="password">
            <p>Re-type your password</p>
            <input type="password" minlength="5" maxLength="45" name="repassword">
            <p>Your name</p>
            <input type="text" maxlength="100" value="${param.name}"  minlength="3" name="name">
            <input type="submit" name="action" value="register">
        </form>
    </body>
</html>

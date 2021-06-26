<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link href="stylesheets/style.css" rel="stylesheet">
    <script src="javascript/login.js"></script>
</head>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="center">
    <div class="header">Sign Up</div>
    <form>
        <p class="alert-success">${message}</p>
        <p class="alert-danger">${message2}</p>
        <input type="text" placeholder="Enter New Username" name="username" required><span><i class="fa fa-user-circle-o" aria-hidden="true"></i></span>
        <input id="password" type="password" placeholder="Enter Password" name="password" required><span><i class="fa fa-eye" aria-hidden="true" onclick="show()"></i></span>
        <input type="Submit" value="Sign up!" href="index.jsp">
        <a href="index.jsp">Return to login</a>
    </form>
</div>

</body>
</html>

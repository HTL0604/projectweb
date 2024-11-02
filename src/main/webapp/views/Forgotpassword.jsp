<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
	<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
<c:if test="${alert !=null}">
			<h3 class="alert alert danger">${alert}</h3>
		</c:if>
    <div class="forgot-password-form">
        <h2>Forgot Password</h2>
        <form action="/projectfinal/forgotpassword" method="post">
            <label for="username">Enter Email</label>
            <input type="text" id="username" name="email" placeholder="Enter your email">

            <label for="email">Enter Phone</label>
            <input type="text" id="phone" name="phone" placeholder="Enter your phone">

            <button type="submit">Confirm</button>
        </form>

    </div>
</body>
</html>
	

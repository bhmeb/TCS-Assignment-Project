<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="wrapper">
	<div class="container">
		<h1>User SignUp</h1>
		
		<form action="/insert-new-user" method="post">
		<div>Full Name : <input type="text" name="fullName" value=""><br></div>
		<div>UserName : <input type="text" name="userName" value=""><br></div>
		<div>Password : <input type="text" name="password" value=""></div>
		<div>Confirm password : <input type="text" name="confirmPassword" value=""></div>
		<div>Contact No : <input type="text" name="phoneNo" value=""></div>
		<div><input type="submit" value="Submit"></div>
	</form>
	</div>
</div>





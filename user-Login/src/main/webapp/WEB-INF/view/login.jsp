<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrapper">
	<div class="container">
		<h1>Welcome</h1>
		
		<form action="/login" method="post">
		<c:if test="${not empty error}">
			<div style="color: red;"><h3>${error}</h3></div>
			<div><br></div>
		</c:if>
		<c:if test="${not empty registerSuccess}">
			<div style="color:yellow;"><h3>${Success}</h3></div>
			<div><br></div>
		</c:if>
			<div>UserName : <input type="text" name="userName" value=""><br></div>
		    <div>Password : <input type="text" name="password" value=""></div>
			<button type="submit" id="login-button">Login</button>
			<input type="button" value="registration" onclick="goToRegister()">
		</form>
	</div>
</div>
<script type="text/javascript">
	function goToRegister(){
		window.location.href="/registration";
	}
</script>

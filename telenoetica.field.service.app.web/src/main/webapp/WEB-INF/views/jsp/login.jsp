<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
	<title>Login</title>
</head>

<body>
<c:set var="emptySring" value="Type in your Credentials"/>
	<div class="wrapper">
		<form class="form1" action="j_spring_security_check" method="post">
			<div class="formtitle">Login to your account</div>
			<div id="site_login"></div>
			<div class="site_login_logo"></div>
			<div class="input">
				<div class="inputtext">Username: </div>
				<div class="inputcontent">
					<input id="j_username" name="j_username" size="20" maxlength="50" type="text" />
				</div>
			</div>
			<div class="input nobottomborder">
				<div class="inputtext">Password: </div>
				<div class="inputcontent">
					<input id="j_password" name="j_password" size="20" maxlength="50" type="password" />
				<!-- 	<br/><a href="#">Forgot password?</a> -->
				</div>
			</div>
			<c:choose>
			    <c:when test="${empty message}">
			      <div class="message">${emptySring}</div>
			    </c:when>
			    <c:otherwise>
			       <div class="message">${message}</div>
			    </c:otherwise>
			</c:choose>
			
			<div class="buttons">
				<input class="orangebutton" type="submit" value="Login" />
			<div>
				Copyright 2013 HTN/Telenoetica | Designed by HTN/Telenoetica 
			 </div>
			</div>
		</form>
		
	</div>
</body>
</html>
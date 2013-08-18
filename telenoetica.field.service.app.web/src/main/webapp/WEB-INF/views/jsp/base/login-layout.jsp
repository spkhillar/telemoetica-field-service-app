<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<spring:url value="/resources/css/login.css" var="mainCssUrl"/>
<link rel="stylesheet" type="text/css" href="${mainCssUrl}"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Field Service Application</title>

</head>
<body>
	<div id="templatemo_body_wrapper">
				<tiles:insertAttribute name="body" />
	</div>
</body>
</html>
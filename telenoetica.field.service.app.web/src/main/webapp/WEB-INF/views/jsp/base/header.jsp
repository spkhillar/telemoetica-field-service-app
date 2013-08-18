<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<spring:url value="/resources/css/templatemo_style.css" var="resourceCssUrl"/>
<spring:url value="/resources/css/custom-theme/jquery-ui-1.10.3.custom.min.css" var="resourceJqUiCssUrl"/>
<spring:url value="/resources/css/ui.jqgrid.css" var="resourceJqGridCssUrl"/>
<spring:url value="/resources/css/menu.css" var="resourceMenuCssUrl"/>

<spring:url value="/resources/js/jquery-1.9.1.min.js" var="resourceJqUrl"/>
<spring:url value="/resources/js/jquery.validate.min.js" var="resourceJqvUrl"/>
<spring:url value="/resources/js/application.js" var="resourceAppJsUrl"/>
<spring:url value="/resources/js/jquery-ui-1.10.3.custom.min.js" var="resourceJqUiUrl"/>
<spring:url value="/resources/js/grid.locale-en.js" var="resourceJqGridLocaleUrl"/>
<spring:url value="/resources/js/jquery.jqGrid.min.js" var="resourceJqGridUrl"/>
<spring:url value="/resources/js/jquery-ui-timepicker-addon.js" var="dateTimePicKerJs"/>
<spring:url value="/resources/js/sliderAccess.js" var="sliderAccessJS"/>


<script type="text/javascript" src="${resourceJqUrl}"></script>
<script type="text/javascript" src="${resourceAppJsUrl}"></script>
<script type="text/javascript" src="${resourceJqvUrl}"></script>
<script type="text/javascript" src="${resourceJqUiUrl}"></script>
<script type="text/javascript" src="${resourceJqGridLocaleUrl}"></script>
<script type="text/javascript" src="${resourceJqGridUrl}"></script>
<script type="text/javascript" src="${dateTimePicKerJs}"></script>
<script type="text/javascript" src="${sliderAccessJS}"></script>


<link rel="stylesheet" type="text/css" href="${resourceCssUrl}"/>
<link rel="stylesheet" type="text/css" href="${resourceMenuCssUrl}"/>
<link rel="stylesheet" type="text/css" href="${resourceJqUiCssUrl}"/>
<link rel="stylesheet" type="text/css" href="${resourceJqGridCssUrl}" />

<script type="text/javascript">
webContextPath="${pageContext.request.contextPath}";
</script>
</head>
<body>
	
	<spring:url value="/" var="homeUrl" htmlEscape="true"/>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div id="templatemo_header">
        	<div id="site_title"><h1><a href="#" target="_parent">Helios Field Application</a></h1></div>
        	<div class="col_right_allw270">
        	<span><b>Logged-In User: <sec:authentication property="principal.username"/></b> </span>
        	<div id="dateTime" style="height: 10px;"></div>
        	<div id="logout"><a href="${contextPath}/j_spring_security_logout"> <br><br>Logout</a>  </div>
        	</div>
        </div>
       <ul id="menu">
		<li><a href="${homeUrl}">Home</a></li>
		<sec:authorize access="hasAnyRole('ROLE_CSOE','ROLE_OPS_MANAGER','ROLE_ADMIN')">
		<li><a href="#">Administration</a>
			<ul>
				<li>
					<a href="${contextPath}/user/list">User Management</a>				
				</li>
			</ul>
		</li>
		</sec:authorize>
		<li><a href="#">Site Visit</a>
			<ul>
				<li><a href="${contextPath}/routine/new">Routine Visit</a>							
				</li>
				<li><a href="${contextPath}/diesel/new">Diesel Visit</a>
				</li>
				<li><a href="${contextPath}/callout/new">Call-Out Visit</a>			
				</li>
				<li><a href="${contextPath}/maintenance/new">Maintenance Visit</a>
				</li>
			</ul>
		</li>
		<sec:authorize access="hasAnyRole('ROLE_CSOE','ROLE_OPS_MANAGER','ROLE_ADMIN')">
		<li><a href="#">Reports</a>
			<ul>
				<li><a href="${contextPath}/routine/list">Routine Visit</a></li>
				<li><a href="${contextPath}/diesel/list">Diesel Visit</a></li>
				<li><a href="${contextPath}/callout/list">Call-Out Visit</a></li>
				<li><a href="${contextPath}/maintenance/list">Maintenance Visit</a></li>
				<li><a href="${contextPath}/reportDownload/monthlyReport/diesel">Diesel Details</a></li>
				<li><a href="${contextPath}/reportDownload/monthlyReport/spares">Spares Utilization</a></li>
			</ul>	
		</li>
		</sec:authorize>
		<li><a href="${contextPath}/downloadAndroidApp">Download Android App</a>
		</li>
		<li><a href="${contextPath}/help">Help</a>
		</li>
	</ul>
	<div id="currentViewTitle">${currentViewTitle}</div>

</body>
</html>
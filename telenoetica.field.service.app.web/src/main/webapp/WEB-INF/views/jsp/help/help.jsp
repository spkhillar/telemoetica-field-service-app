<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
  $(function() {
    $( "#tabs" ).tabs({
      collapsible: true
    });
  });
  </script>
</head>
<body>
<div id="tabs">
  <ul>
    <li><a href="resources/html/overview.html">Overview</a></li>
    <li><a href="resources/html/home.html">Home</a></li>
    <li><a href="resources/html/admin.html">Admin</a></li>
    <li><a href="resources/html/visit.html">Visit</a></li>
    <li><a href="resources/html/report.html">Report</a></li>
  </ul>
 </div>
 
</body>
</html>
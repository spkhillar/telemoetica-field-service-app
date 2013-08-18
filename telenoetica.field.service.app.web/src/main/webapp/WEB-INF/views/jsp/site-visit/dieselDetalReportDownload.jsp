<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>
<html>
<head>
<spring:url value="/resources/css/jquery.jqplot.min.css" var="resourceJqplotCssUrl"/>

<spring:url value="/resources/js/jqplot.barRenderer.min.js" var="barRendererUrl"/>
<spring:url value="/resources/js/jqplot.categoryAxisRenderer.min.js" var="categoryAxisRendererUrl"/>
<spring:url value="/resources/js/jqplot.pointLabels.min.js" var="pointLabelsUrl"/>
<spring:url value="/resources/js/jquery.jqplot.min.js" var="jqplotUrl"/>

<link href="${resourceJqplotCssUrl}" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${jqplotUrl}"></script>
<script type="text/javascript" src="${barRendererUrl}"></script>
<script type="text/javascript" src="${categoryAxisRendererUrl}"></script>
<script type="text/javascript" src="${pointLabelsUrl}"></script>

<script type="text/javascript">

	$().ready(function() {
		var typeVar = '<c:out value="${type}"/>';
		var jsonurl = webContextPath + "/reportDownload/yearlyReportList/"+typeVar;
		var listReport = populateDataForHomeScreen(jsonurl);
		populateReportDownloadTableData(listReport);
	});

	function populateDataForHomeScreen(url) {
		var ret = null;
		$.ajax({
			// have to use synchronous here, else the function 
			// will return before the data is fetched
			async : false,
			url : url,
			success : function(data) {
				ret = data;
			}
		});
		return ret;
	}

	function populateReportDownloadTableData(input) {
		$.each(input, function(i, row) {
			$('[name="reportTable"]').append(
					"<tr><td>" + row.description + "</td><td>" + row.startTime
							+ "</td><td>" + '<a href="'+webContextPath+'/reportDownload/monthlyReport/export/'+ row.id+'" style="font-weight: bold">Download</a>'
							+ "</td></tr>");
		});
	}
</script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div style="height: 650px;">
		<div class="col_w420 lp_box float_l">
			<c:choose>
				<c:when test="${type eq 'diesel'}">
					<h2>Diesel Detail Report list</h2>
			    </c:when>
				<c:otherwise>
					<h2>Spares Utilization Report</h2>
			    </c:otherwise>
			</c:choose>
			<h5>(Last 12 Months Report)</h5>
			<table id="newspaper-b" name="reportTable">
				<thead>
					<tr>
						<th scope="col">Report</th>
						<th scope="col">Date Generated</th>
						<th scope="col">Download Report</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

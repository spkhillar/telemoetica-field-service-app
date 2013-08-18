<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>
<html>


<spring:url value="/resources/css/screen.css" var="resourceCmxUrl"/>

<link href="${resourceCmxUrl}" rel="stylesheet" type="text/css" />
<head>
<script type="text/javascript">

//$("#accountForm").validate( {
	

	$(document).ready(function() {
		$("#maintenanceCreateForm").validate({
			success : "valid",
			ignoreTitle : true,
			rules : {
				"siteId" : {
					required : true,
					siteIdCheck : true
				},
				"accessCode" : {
					required : true,
					alphanumeric : true
				},
				"runHoursAfterPmdG1" : {
					number : true,
					range : [ 0, 30000 ]
				},
				"runHoursAfterPmdG2" : {
					number : true,
					range : [ 0, 30000 ]
				},
			}
		});
		$('#categoryOfMaintenance').html(htmlMaintenanceCategoriesOptions);
		$('#sparesUsedItemsReplaced1').html(htmlEquipmentOptions);
		$('#sparesUsedItemsReplaced2').html(htmlEquipmentOptions);
		$('#sparesUsedItemsReplaced3').html(htmlEquipmentOptions);
		$('#sparesUsedItemsReplaced4').html(htmlEquipmentOptions);
		$('#sparesUsedItemsReplaced5').html(htmlEquipmentOptions);
		$('#sparesUsedItemsReplaced6').html(htmlEquipmentOptions);
		$('#cosumablesUsed1').html(htmlEquipmentOptions);
		$('#cosumablesUsed2').html(htmlEquipmentOptions);
		$('#cosumablesUsed3').html(htmlEquipmentOptions);
		$('#cosumablesUsed4').html(htmlEquipmentOptions);
		$('#cosumablesUsed5').html(htmlEquipmentOptions);
		$('#cosumablesUsed6').html(htmlEquipmentOptions);
		$("#save").button();
		$("#reset").button();
		$( "#siteId" ).autocomplete({
		      source: homeSiteArray
		    });
	});

	function test() {

		/* messages: {
			required: "This field is required.",
			remote: "Please fix this field.",
			email: "Please enter a valid email address.",
			url: "Please enter a valid URL.",
			date: "Please enter a valid date.",
			dateISO: "Please enter a valid date (ISO).",
			number: "Please enter a valid number.",
			digits: "Please enter only digits.",
			creditcard: "Please enter a valid credit card number.",
			equalTo: "Please enter the same value again.",
			maxlength: $.validator.format("Please enter no more than {0} characters."),
			minlength: $.validator.format("Please enter at least {0} characters."),
			range: $.validator.format("Please enter a value between {0} and {1} characters long."),
			rangelength: $.validator.format("Please enter a value between {0} and {1}."),
			max: $.validator.format("Please enter a value less than or equal to {0}."),
			min: $.validator.format("Please enter a value greater than or equal to {0}.")
		}
		 */}

	function submitMaintenanceData() {

		console.log('.....webContextPath....' + webContextPath);
		var actionUrl = webContextPath + "/maintenance/save";

		console.log('..actionUrl-maintenance..', actionUrl);
		var isValid = $("#maintenanceCreateForm").valid();
		console.log('Form Valid...', isValid);
		if (isValid) {
			var str = $("#maintenanceCreateForm").serialize();
			console.log('values...', str);
			$.ajax({
				type : "post",
				data : str,
				url : actionUrl,
				async : false,
				success : function(data, textStatus) {
					showVisitMessage("Saved Successfuly.");
					$("#save").hide();
				},
				error : function(textStatus, errorThrown) {
					alert(textStatus + "" + errorThrown);
				}
			});
		}
	}

	function refreshMaintenanceData() {
		location.reload();
	}
</script>
<style type="text/css">

#maintenanceCreateForm { width: 900px; }
#maintenanceCreateForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

</style>
</head>

<body>
<form:form id="maintenanceCreateForm" name="maintenanceCreateForm" modelAttribute="maintenanceForm" cssClass="cmxform">
		<fieldset>
				<p>
					<label><spring:message code="fieldapp.label.site"/> <em>*</em> </label> 
					<form:input path="siteId"/>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.access.code"/>  <em>*</em> </label> 
					<form:input path="accessCode"/>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.category.of.maintainance"/></label> 
					<form:select id="categoryOfMaintenance" path="categoryOfMaintenance">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.spares.used.replace.1"/></label> 
					<form:select id="sparesUsedItemsReplaced1" path="sparesUsedItemsReplaced1">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.spares.used.replace.2"/></label> 
					<form:select id="sparesUsedItemsReplaced2" path="sparesUsedItemsReplaced2">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.spares.used.replace.3"/></label> 
					<form:select id="sparesUsedItemsReplaced3" path="sparesUsedItemsReplaced3">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.spares.used.replace.4"/></label> 
					<form:select id="sparesUsedItemsReplaced4" path="sparesUsedItemsReplaced4">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.spares.used.replace.5"/></label> 
					<form:select id="sparesUsedItemsReplaced5" path="sparesUsedItemsReplaced5">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.spares.used.replace.6"/></label> 
					<form:select id="sparesUsedItemsReplaced6" path="sparesUsedItemsReplaced6">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.consumable.used.1"/></label> 
					<form:select id="cosumablesUsed1" path="cosumablesUsed1">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.consumable.used.2"/></label> 
					<form:select id="cosumablesUsed2" path="cosumablesUsed2">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.consumable.used.3"/></label> 
					<form:select id="cosumablesUsed3" path="cosumablesUsed3">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.consumable.used.4"/></label> 
					<form:select id="cosumablesUsed4" path="cosumablesUsed4">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.consumable.used.5"/></label> 
					<form:select id="cosumablesUsed5" path="cosumablesUsed5">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.consumable.used.5"/></label> 
					<form:select id="cosumablesUsed6" path="cosumablesUsed6">
					</form:select>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.run.hours.dg.1"/>  </label> 
					<form:input path="runHoursAfterPmdG1"/>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.mv.run.hours.dg.2"/>  </label> 
					<form:input path="runHourAfterPmdG2"/>
				</p>
		</fieldset>
	</form:form>
			<button id="save" onclick="submitMaintenanceData();"><spring:message code="fieldapp.label.save"/> </button>
			<button id="reset" onclick="refreshMaintenanceData();"><spring:message code="fieldapp.label.reset"/></button>
			<span id="messageSpanId" class="message"></span>
</body>
</html>

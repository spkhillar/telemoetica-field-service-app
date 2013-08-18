<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>
<html>


<spring:url value="/resources/css/screen.css" var="resourceCmxUrl"/>

<link href="${resourceCmxUrl}" rel="stylesheet" type="text/css" />
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dieselCreateForm").validate({
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
				"drnNumber" : {
					required : true,
					alphanumeric : true
				},
				"bulkNameOfVendor" : {
					bulkCheck: true
				},
				"transferredSiteId" : {
					siteIdCheck : true,
					siteCheck : true
				},
				"dieselLevelT1BeforeVisit" : {
					number : true,
					range : [ 0, 6000 ]
				},
				"dieselLevelT2BeforeVisit" : {
					number : true,
					range : [ 0, 6000 ]
				},
				"dieselReceivedLtrs" : {
					number : true,
					range : [ 0, 6000 ]
				},
				"runHourGen1" : {
					number : true,
					range : [ 0, 30000 ]
				},
				"runHourGen2" : {
					number : true,
					range : [ 0, 30000 ]
				},
				"phcnHrsPerDay" : {
					number : true,
					range : [ 0, 24 ]
				},
				"hybridOrPiuHrsPerDay" : {
					number : true,
					range : [ 0, 24 ]
				}
			}
		});
		
		jQuery.validator.addMethod('bulkCheck', function(value) {
			var transfer = $('input:radio[name=dieselTransferOrBulkSupply]:checked').val();
			if(transfer == 'Bulk'){
				var vendorName = $('#bulkNameOfVendor').val();
				if (vendorName.length <=0){
					return false;
				}
			}
			return true;
		}, "Vendor name is required for bulk diesel transfer.");
		
		jQuery.validator.addMethod('siteCheck', function(value) {
			var transfer = $('input:radio[name=dieselTransferOrBulkSupply]:checked').val();
			if(transfer == 'Site'){
				var vendorName = $('#transferredSiteId').val();
				if (vendorName.length <=0){
					return false;
				}
			}
			return true;
		}, "Transferred site is required for site diesel transfer.");
		$("#save").button();
		$("#reset").button();
		initializeDefaultSelection();
		bulkOrTransferEnableText();
		radioBtnPiuInstalledEnableText();
		radioBtnPhcnInstalledText();
		$( "#siteId" ).autocomplete({
		      source: homeSiteArray
		    });
		$( "#transferredSiteId" ).autocomplete({
		      source: homeSiteArray
		    });
		$('#dieselDensity').html(htmlDieselDensityOptions);
		$('#bulkNameOfVendor').html(htmlDieselVendorOptions);		
	});
	
	function initializeDefaultSelection(){
		$("#transferredSiteId").attr("disabled", "disabled");
		$("#hybridOrPiuHrsPerDay").attr("disabled", "disabled"); 
		$("#phcnHrsPerDay").attr("disabled", "disabled"); 
	}
	function bulkOrTransferEnableText(){
		$("#radioBtnBulkOrSupply input[type=radio]").each(function(i){
		    $(this).click(function () {
		    	if(i==0) { //Bulk selected
		    		$("#transferredSiteId").attr("disabled", "disabled"); 
		    		$("#bulkNameOfVendor").removeAttr("disabled"); 
		    		$("#bulkNameOfVendor").val("");
		    		$("#transferredSiteId").val("");
		    	}
		    	else { //Transfer selectedbulkNameOfVendor
		    		$("#bulkNameOfVendor").attr("disabled", "disabled"); 
		    		$("#transferredSiteId").removeAttr("disabled"); 
		    		$("#bulkNameOfVendor").val("");
		    		$("#transferredSiteId").val("");
		    	}
		      });

		  });
	}
	
	function radioBtnPhcnInstalledText(){
		$("#radioBtnPhcnInstalled input[type=radio]").each(function(i){
		    $(this).click(function () {
		    	if(i==0) { //Bulk selected
		    		$("#phcnHrsPerDay").removeAttr("disabled"); 
		    		$("#phcnHrsPerDay").val("");
		    	}
		    	else { //Transfer selectedbulkNameOfVendor
		    		$("#phcnHrsPerDay").attr("disabled", "disabled"); 
		    		$("#phcnHrsPerDay").val("");
		    	}
		      });

		  });
	}
	
	function radioBtnPiuInstalledEnableText(){
		$("#radioBtnPiuInstalled input[type=radio]").each(function(i){
		    $(this).click(function () {
		    	if(i==0) { //Bulk selected
		    		$("#hybridOrPiuHrsPerDay").removeAttr("disabled"); 
		    		$("#hybridOrPiuHrsPerDay").val("");
		    	}
		    	else { //Transfer selectedbulkNameOfVendor
		    		$("#hybridOrPiuHrsPerDay").attr("disabled", "disabled"); 
		    		$("#hybridOrPiuHrsPerDay").val("");
		    	}
		      });

		  });
	}
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

	function submitDieselData() {
		var saved = $("#save").attr("disabled");
		if (saved == 'disabled') {
			return;
		}

		var actionUrl = webContextPath + '/diesel/save';
		console.log('..actionUrl-diesel..', actionUrl);
		var isValid = $("#dieselCreateForm").valid();
		console.log('Form Valid...', isValid);
		if (isValid) {
			var str = $("#dieselCreateForm").serialize();
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

	function refreshDieselData() {
		location.reload();
	}
</script>
<style type="text/css">

#dieselCreateForm { width: 900px; }
#dieselCreateForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

</style>
</head>

<body>
<form:form id="dieselCreateForm" name="dieselCreateForm" modelAttribute="dieselForm" cssClass="cmxform">
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
					<label><spring:message code="fieldapp.label.dv.drn.or.dtn.num"/></label> 
					<form:input path="drnNumber" />
				</p>
				<p>
					<span id="radioBtnBulkOrSupply">
					<label><spring:message code="fieldapp.label.dv.transfer.or.bulk.supply"/></label> 
					<label style="width: 50px;"><form:radiobutton path="dieselTransferOrBulkSupply" value="Bulk" /><spring:message code="fieldapp.label.dv.bulk.transfer"/></label>
					<label style="width: 50px;"><form:radiobutton path="dieselTransferOrBulkSupply" value="Site" /><spring:message code="fieldapp.label.dv.site.transfer"/></label>
					</span>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.transfer.site"/></label> 
					<form:input path="transferredSiteId" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.bulk.vendor.name"/></label>
					<form:select id="bulkNameOfVendor" path="bulkNameOfVendor">
					</form:select> 
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.level.t1"/></label> 
					<form:input path="dieselLevelT1BeforeVisit" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.level.t2"/></label> 
					<form:input path="dieselLevelT2BeforeVisit" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.diesel.received"/></label> 
					<form:input path="dieselReceivedLtrs" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.diesel.density"/></label>
					<form:select id="dieselDensity" path="dieselDensity">
					</form:select> 
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.run.hr.gen1"/></label> 
					<form:input path="runHourGen1" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.run.hr.gen2"/></label> 
					<form:input path="runHourGen2" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.drn.booked.at.site"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="drnBookAtSite" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="drnBookAtSite" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.diesel.log.book.maintained"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="dieselLogBookMaintained" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="dieselLogBookMaintained" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
				</p>
				<p>
				<span id="radioBtnPhcnInstalled">
					<label><spring:message code="fieldapp.label.dv.phcn.installed"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="phcnInstalled" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="phcnInstalled" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
				</span>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.phcn.hrs.per.day"/></label> 
					<form:input path="phcnHrsPerDay" />
				</p>
				<p>
				<span id="radioBtnPiuInstalled">
					<label><spring:message code="fieldapp.label.dv.hybrid.piu.installed"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="hybridOrPiuInstalled" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="hybridOrPiuInstalled" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
				</span>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.dv.hybrid.piu.hrs.per.day"/></label> 
					<form:input path="hybridOrPiuHrsPerDay" />
				</p>
		</fieldset>
	</form:form>
			<button id="save" onclick="submitDieselData();"><spring:message code="fieldapp.label.save"/> </button>
			<button id="reset" onclick="refreshDieselData();"><spring:message code="fieldapp.label.reset"/></button>
			<span id="messageSpanId" class="message"></span>

</body>
</html>
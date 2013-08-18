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
		$("#routineCreateForm").validate( {
		    success : "valid",
		    ignoreTitle : true,
		    rules : {
				"siteId" : {
			        required : true,
			        siteIdCheck:true
			   },
					"accessCode" : {
						required : true,
						alphanumeric : true
		      },
		      "dieselLevelT1" : {
		    	  required : true,
		    	  range:[0,6000]
		      },
		      "dieselLevelT2" : {
		    	  number : true,
		    	  range:[0,6000]
		      },
		      "runHourGen1":{
		    	  number : true,
		    	  range:[0,30000]
		      },
		      "runHourGen2":{
		    	  number : true,
		    	  range:[0,30000]
		      },
		      "voltageNrVolts":{
		    	  number : true,
		    	  range:[0,400]
		      },
		      "voltageNyVolts":{
		    	  number : true,
		    	  range:[0,400]
		      },
		      "voltageNbVolts":{
		    	  number : true,
		    	  range:[0,400]
		      },
		      "loadRPhaseAmps":{
		    	  number : true,
		    	  range:[0,999]
		      },
		      "loadYPhaseAmps":{
		    	  number : true,
		    	  range:[0,999]
		      },
		      "loadBPhaseAmps":{
		    	  number : true,
		    	  range:[0,999]
		      },
		      "rectifierOpVoltage":{
		    	  number : true,
		    	  range:[0,99]
		      },
		      "rectifierOpCurrentAmp":{
		    	  number : true,
		    	  range:[0,999]
		      },
		      "batteryCapcityV":{
		    	  number : true,
		    	  range:[0,50]
		      },
		      "batteryCapcityAh":{
		    	  number : true,
		    	  range:[0,2000]
		      }
		    }
		});

		$("#save").button();
		$("#reset").button();
		$( "#siteId" ).autocomplete({
		      source: homeSiteArray
		    });
	});
	
	function test(){

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
 */	}

function submitRoutineData(){
	 var saved = $("#save").attr("disabled");
	if(saved == 'disabled'){
		return;
	}
	
	var actionUrl = webContextPath+'/routine/save';
	console.log('..actionUrl-routine..',actionUrl);
	var isValid = $("#routineCreateForm").valid();
	console.log('Form Valid...',isValid);
	if(isValid){
		var str = $("#routineCreateForm").serialize();
		console.log('values...',str);
		$.ajax({
		    type:"post",
		    data:str,
		    url:actionUrl,
		    async: false,
		    success: function(data, textStatus){
		    	showVisitMessage("Saved Successfuly.");
		    	$("#save").hide();
		       //$("#save").attr("disabled","disabled");
		       //$("#save").css("background-color","silver");
		    },
		    error: function(textStatus,errorThrown){
			       alert(textStatus+""+errorThrown);
			}
		});
	}
}

function refreshRoutineData(){
	location.reload();
}

</script>
<style type="text/css">

#routineCreateForm { width: 900px; }
#routineCreateForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

</style>
</head>

<body>
<form:form id="routineCreateForm" name="routineCreateForm" modelAttribute="routineForm" cssClass="cmxform">
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
					<label><spring:message code="fieldapp.label.rv.dlt1"/></label> 
					<form:input path="dieselLevelT1" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.dlt2"/></label> 
					<form:input path="dieselLevelT2" />
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
					<label><spring:message code="fieldapp.label.rv.voltage.nr.volts"/></label> 
					<form:input path="voltageNrVolts" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.voltage.ny.volts"/></label> 
					<form:input path="voltageNyVolts" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.voltage.nb.volts"/></label> 
					<form:input path="voltageNbVolts" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.load.rphase.amps"/></label> 
					<form:input path="loadRPhaseAmps" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.load.yphase.amps"/></label> 
					<form:input path="loadYPhaseAmps" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.load.bphase.amps"/></label> 
					<form:input path="loadBPhaseAmps" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.recitifier.op.volt"/></label> 
					<form:input path="rectifierOpVoltage" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.recitifier.op.curr.amp"/></label> 
					<form:input path="rectifierOpCurrentAmp" />
				</p>
				<!-- New fields -->
				
				<p>
					<label><spring:message code="fieldapp.label.rv.battery.capacity.v"/> </label> 
					<form:input path="batteryCapcityV" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.battery.capacity.ah"/></label> 
					<form:input path="batteryCapcityAh" />
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.ats.functional"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="atsFunctional" value="true" /><spring:message code="fieldapp.label.yes.value"/> </label>
					<label style="width: 50px;"><form:radiobutton path="atsFunctional" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.ats.syncronising"/></label> 
					<label style="width: 50px;"><form:radiobutton path="atsSysncronising" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="atsSysncronising" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
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
					<label><spring:message code="fieldapp.label.rv.aircon.shelter1.cooling"/> </label> 
					<label style="width: 50px;"><form:radiobutton path="airconShelter1Cooling" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="airconShelter1Cooling" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
					<label style="width: 100px;"><form:radiobutton path="airconShelter1Cooling" value="Not Applicable" /><spring:message code="fieldapp.label.na.value"/></label>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.aircon.shelter2.cooling"/></label> 
					<label style="width: 50px;"><form:radiobutton path="airconShelter2Cooling" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="airconShelter2Cooling" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
					<label style="width: 100px;"><form:radiobutton path="airconShelter2Cooling" value="Not Applicable" /><spring:message code="fieldapp.label.na.value"/></label>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.aircon.shelter3.cooling"/></label> 
					<label style="width: 50px;"><form:radiobutton path="airconShelter3Cooling" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="airconShelter3Cooling" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
					<label style="width: 100px;"><form:radiobutton path="airconShelter3Cooling" value="Not Applicable" /><spring:message code="fieldapp.label.na.value"/></label>
				</p>
				<p>
					<label><spring:message code="fieldapp.label.rv.aircon.shelter4.cooling"/></label> 
					<label style="width: 50px;"><form:radiobutton path="airconShelter4Cooling" value="true" /><spring:message code="fieldapp.label.yes.value"/></label>
					<label style="width: 50px;"><form:radiobutton path="airconShelter4Cooling" value="false" /><spring:message code="fieldapp.label.no.value"/></label>
					<label style="width: 100px;"><form:radiobutton path="airconShelter4Cooling" value="Not Applicable" /><spring:message code="fieldapp.label.na.value"/></label>
				</p>
		</fieldset>
	</form:form>
			<button id="save" onclick="submitRoutineData();"><spring:message code="fieldapp.label.save"/> </button>
			<button id="reset" onclick="refreshRoutineData();"><spring:message code="fieldapp.label.reset"/></button>
			<span id="messageSpanId" class="message"></span>
</body>
</html>

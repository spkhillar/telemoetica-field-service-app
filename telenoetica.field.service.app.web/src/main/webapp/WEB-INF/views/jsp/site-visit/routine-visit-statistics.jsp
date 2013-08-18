<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		var actionUrl = webContextPath + "/routine/records";
		$("#grid").jqGrid(
				{
					url : actionUrl,
					datatype : 'json',
					mtype : 'GET',
					hidegrid: false, 
					colNames : [ "Access Code", "User Name", "Site",
							"Created At", "Diesel Level T1", "Diesel Level T2",
							"Run Hour Gen 1", "Run Hour Gen 2",
							"Voltage N-R volts", "Voltage N-Y volts",
							"Voltage N-B volts", "Load R phase Amps",
							"Load Y phase Amps", "Load B phase Amps",
							"Rectifier O/P voltage",
							"Rectifier O/P current Amp", "Battery capacity V",
							"Battery capacity AH", "Ats Functional",
							"Ats Syncronising", "DRN booked at site",
							"Diesel log book maintained",
							"Aircon of shelter1 cooling",
							"Aircon of shelter2 cooling",
							"Aircon of shelter3 cooling",
							"Aircon of shelter4 cooling" ],
					colModel : [ {
						name : 'accessCode',
						index : 'accessCode',
						width : 75,
						frozen : true
					}, {
						name : 'userId',
						index : 'userId',
						width : 75,
						frozen : true
					}, {
						name : 'siteId',
						index : 'siteId',
						width : 100,
						frozen : true
					}, {
						name : 'createdAt',
						index : 'createdAt',
						width : 100,
						frozen : true,
						stype : 'text',
						searchoptions : {
							dataInit : datePick,
							attr : {
								title : 'Select Date'
							}
						}
					}, {
						name : 'dieselLevelT1',
						index : 'dieselLevelT1',
						width : 100
					}, {
						name : 'dieselLevelT2',
						index : 'dieselLevelT2',
						width : 100
					}, {
						name : 'runHourGen1',
						index : 'runHourGen1',
						width : 100
					}, {
						name : 'runHourGen1',
						index : 'runHourGen2',
						width : 100
					}, {
						name : 'voltageNrVolts',
						index : 'voltageNrVolts',
						width : 100
					}, {
						name : 'voltageNyVolts',
						index : 'voltageNyVolts',
						width : 100
					}, {
						name : 'voltageNbVolts',
						index : 'voltageNbVolts',
						width : 100
					}, {
						name : 'loadRPhaseAmps',
						index : 'loadRPhaseAmps',
						width : 120
					}, {
						name : 'loadYPhaseAmps',
						index : 'loadYPhaseAmps',
						width : 120
					}, {
						name : 'loadBPhaseAmps',
						index : 'loadBPhaseAmps',
						width : 120
					}, {
						name : 'rectifierOpVoltage',
						index : 'rectifierOpVoltage',
						width : 120
					}, {
						name : 'rectifierOpCurrentAmp',
						index : 'rectifierOpCurrentAmp',
						width : 150
					}, {
						name : 'batteryCapcityV',
						index : 'batteryCapcityV',
						width : 120
					}, {
						name : 'batteryCapcityAh',
						index : 'batteryCapcityAh',
						width : 120
					}, {
						name : 'atsFunctional',
						index : 'atsFunctional',
						width : 100,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'atsSysncronising',
						index : 'atsSysncronising',
						width : 100,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'drnBookAtSite',
						index : 'drnBookAtSite',
						width : 120,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'dieselLogBookMaintained',
						index : 'dieselLogBookMaintained',
						width : 150,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'airconShelter1Cooling',
						index : 'airconShelter1Cooling',
						width : 140,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseNAOption
						}
					}, {
						name : 'airconShelter2Cooling',
						index : 'airconShelter2Cooling',
						width : 140,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseNAOption
						}
					}, {
						name : 'airconShelter3Cooling',
						index : 'airconShelter3Cooling',
						width : 140,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseNAOption
						}
					}, {
						name : 'airconShelter4Cooling',
						index : 'airconShelter4Cooling',
						width : 140,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseNAOption
						}
					} ],
					rowNum : 20,
					rowList : [ 20, 30, 40, 50 ],
					height : 450,
					width : 900,
					pager : '#pager',
					rownumbers : true,
					sortname : 'createdAt',
					viewrecords : true,
					sortorder : "desc",
					caption : "Routine Visit Report",
					emptyrecords : "Empty records",
					shrinkToFit : false,
					jsonReader : {
						repeatitems : false,
					}
				});

		function datePick(elem) {
			$(elem).datepicker({
				dateFormat : "dd-mm-yy"
			});
		}

		$("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false,
			search : true,
			excel : true,
		    searchtext: "Filter",
		    searchtitle: "Filter",
		    refreshtext: "Refresh",
		    refreshtitle: "Refresh"
		}, {}, {}, {}, { //search
			sopt : [ 'cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : true,
			closeAfterSearch : false
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Export",
			buttonicon : "ui-icon-newwin",
			onClickButton : exportExcel,
			position : "last",
			title : "",
			cursor : "pointer"
		});
		$("#grid").jqGrid('setFrozenColumns');
		
	});
	function exportExcel() {
		jQuery("#grid").jqGrid('excelExport', {
			tag : 'excel',
			url : webContextPath + '/routine/export'
		});
	}
</script>
</head>
<body>
<div style="height: 550px;">
	<div id='jqgrid'>
		<table id='grid'></table>
		<div id='pager'></div>
	</div>
</div>
	<div id='msgbox' title='' style='display: none'></div>
</body>
</html>
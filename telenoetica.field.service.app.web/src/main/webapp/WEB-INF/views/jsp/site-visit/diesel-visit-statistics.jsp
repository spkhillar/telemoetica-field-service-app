<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		var actionUrl = webContextPath + "/diesel/records";
		$("#grid").jqGrid(
				{
					url : actionUrl,
					datatype : 'json',
					mtype : 'GET',
					hidegrid: false, 
					colNames : [ "Access Code", "User Name", "Site",
							"Created At", "DRN/DTN Number",
							"Diesel Supply Type", "Transfer Site",
							"Vendor Name", "Diesel Level T1 before receipt",
							"Diesel Level T2 before receipt",
							"Diesel received (Ltrs)", "diesel density", "Run Hour Gen 1",
							"Run Hour Gen 2", "DRN booked at site",
							"Diesel log book maintained", "PHCN Installed",
							"PHCN Hrs per day", "Hybrid/PIU installed",
							"Hybrid/PIU hrs per Day" ],
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
						width : 120,
						frozen : true,
						stype : 'text',
						searchoptions : {
							dataInit : datePick,
							attr : {
								title : 'Select Date'
							}
						}
					}, {
						name : 'drnNumber',
						index : 'drnNumber',
						width : 100
					}, {
						name : 'dieselTransferOrBulkSupply',
						index : 'dieselTransferOrBulkSupply',
						width : 100,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + bulkOrTransferOption
						}
					}, {
						name : 'transferredSiteId',
						index : 'transferredSiteId',
						width : 100
					}, {
						name : 'bulkNameOfVendor',
						index : 'bulkNameOfVendor',
						width : 100
					}, {
						name : 'dieselLevelT1BeforeVisit',
						index : 'dieselLevelT1BeforeVisit',
						width : 170
					}, {
						name : 'dieselLevelT2BeforeVisit',
						index : 'dieselLevelT2BeforeVisit',
						width : 170
					}, {
						name : 'dieselReceivedLtrs',
						index : 'dieselReceivedLtrs',
						width : 150
					},{
						name : 'dieselDensity',
						index : 'dieselDensity',
						width : 150
					}, {
						name : 'runHourGen1',
						index : 'runHourGen1',
						width : 120
					}, {
						name : 'runHourGen2',
						index : 'runHourGen2',
						width : 120
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
						name : 'phcnInstalled',
						index : 'phcnInstalled',
						width : 120,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'phcnHrsPerDay',
						index : 'phcnHrsPerDay',
						width : 120
					}, {
						name : 'hybridOrPiuInstalled',
						index : 'hybridOrPiuInstalled',
						width : 120,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'hybridOrPiuHrsPerDay',
						index : 'hybridOrPiuHrsPerDay',
						width : 120
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
					caption : "Diesel Visit Report",
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
			url : webContextPath + '/diesel/export'
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
</body>
</html>
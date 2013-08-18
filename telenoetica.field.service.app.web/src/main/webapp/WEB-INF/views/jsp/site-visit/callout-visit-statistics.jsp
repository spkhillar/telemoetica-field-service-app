<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	$(function() {
		var actionUrl = webContextPath + "/callout/records";
		$("#grid").jqGrid(
				{
					url : actionUrl,
					datatype : 'json',
					mtype : 'GET',
					hidegrid: false, 
					colNames : [ "Access Code", "User Name", "Site", "Created At",
					 			"Call-out CSR or TT number", "Time when complain received", "Time when reached to site",
								"Time when fault resolved", "Customer1 Impacted", "Customer2 Impacted",
								"Customer3 Impacted", "Customer4 Impacted", "Main Category of fault",
								"Equipment/ Component that caused fault", "Equipment/ Componenet repaired",
								"Equipment/ Componenet replaced", "Is the fix/ resolution temporary or permanent? ",
								"Actions required for permanent resolution" ],
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
						name : 'callOutCsrOrTtNumber',
						index : 'callOutCsrOrTtNumber',
						width : 100
					}, {
						name : 'timeComplainReceived',
						index : 'timeComplainReceived',
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
						name : 'timeReachedToSite',
						index : 'timeReachedToSite',
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
						name : 'timeFaultResolved',
						index : 'timeFaultResolved',
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
						name : 'customer1Impacted',
						index : 'customer1Impacted',
						width : 100
					}, {
						name : 'customer2Impacted',
						index : 'customer2Impacted',
						width : 100
					}, {
						name : 'customer3Impacted',
						index : 'customer3Impacted',
						width : 100
					}, {
						name : 'customer4Impacted',
						index : 'customer4Impacted',
						width : 120
					}, {
						name : 'mainCategoryOfFault',
						index : 'mainCategoryOfFault',
						width : 120
					}, {
						name : 'equipmentComponentCausedFault',
						index : 'equipmentComponentCausedFault',
						width : 120
					}, {
						name : 'equipmentComponentRepaired',
						index : 'equipmentComponentRepaired',
						width : 120
					}, {
						name : 'equipmentComponentReplaced',
						index : 'equipmentComponentReplaced',
						width : 150
					}, {
						name : 'fixResolutionTemporaryOrPermanent',
						index : 'fixResolutionTemporaryOrPermanent',
						width : 100,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + temproryPermanentOption
						}
						}, {
						name : 'actionsRequiredForPermanentResolution',
						index : 'actionsRequiredForPermanentResolution',
						width : 120
					}
					 ],
					rowNum : 20,
					rowList : [ 20, 30, 40, 50 ],
					height : 450,
					width : 900,
					pager : '#pager',
					rownumbers : true,
					sortname : 'createdAt',
					viewrecords : true,
					sortorder : "desc",
					caption : "Callout Visit Report",
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
			url : webContextPath + '/callout/export'
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

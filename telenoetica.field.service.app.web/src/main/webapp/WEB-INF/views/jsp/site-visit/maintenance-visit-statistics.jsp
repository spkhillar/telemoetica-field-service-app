<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		var actionUrl = webContextPath + "/maintenance/records";
		$("#grid").jqGrid(
				{
					url : actionUrl,
					datatype : 'json',
					mtype : 'GET',
					hidegrid: false, 
					colNames : [ "Access Code", "User Name", "Site", "Created At",
					 			"Category of maintenance", "Spares used/ Items replaced - 1", "Spares used/ Items replaced - 2",
								"Spares used/ Items replaced - 3", "Spares used/ Items replaced - 4",
								"Spares used/ Items replaced - 5", "Spares used/ Items replaced - 6", "Consumables used -1",
								"Consumables used -2", "Consumables used -3",
								"Consumables used -4","Consumables used -5", "Consumables used -6",
								"Run-Hours after PM of DG1 ",
								"Run-Hours after PM of DG2" ],
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
						name : 'categoryOfMaintenance',
						index : 'categoryOfMaintenance',
						width : 100
					}, {
						name : 'sparesUsedItemsReplaced1',
						index : 'sparesUsedItemsReplaced1',
						width : 100,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + bulkOrTransferOption
						}
					}, {
						name : 'sparesUsedItemsReplaced2',
						index : 'sparesUsedItemsReplaced2',
						width : 100
					}, {
						name : 'sparesUsedItemsReplaced3',
						index : 'sparesUsedItemsReplaced3',
						width : 100
					}, {
						name : 'sparesUsedItemsReplaced4',
						index : 'sparesUsedItemsReplaced4',
						width : 170
					}, {
						name : 'sparesUsedItemsReplaced5',
						index : 'sparesUsedItemsReplaced5',
						width : 170
					}, {
						name : 'sparesUsedItemsReplaced6',
						index : 'sparesUsedItemsReplaced6',
						width : 150
					}, {
						name : 'cosumablesUsed1',
						index : 'cosumablesUsed1',
						width : 120
					}, {
						name : 'cosumablesUsed2',
						index : 'cosumablesUsed2',
						width : 120
					}, {
						name : 'cosumablesUsed3',
						index : 'cosumablesUsed3',
						width : 120,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'cosumablesUsed4',
						index : 'cosumablesUsed4',
						width : 150,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'cosumablesUsed5',
						index : 'cosumablesUsed5',
						width : 120,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'cosumablesUsed6',
						index : 'cosumablesUsed6',
						width : 120
					}, {
						name : 'runHoursAfterPmdG1',
						index : 'runHoursAfterPmdG1',
						width : 120,
						stype : 'select',
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'runHourAfterPmdG2',
						index : 'runHourAfterPmdG2',
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
					caption : "Maintenance Visit Report",
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
			url : webContextPath + '/maintenance/export'
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
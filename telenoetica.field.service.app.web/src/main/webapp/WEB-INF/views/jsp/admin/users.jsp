<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'>
	var editUserAction = false;
	$(function() {
		var actionUrl = webContextPath + "/user/records";
		var roles = jqgridUserRolesFilter;
		$("#grid").jqGrid(
				{
					url : actionUrl,
					datatype : 'json',
					mtype : 'GET',
					colNames : [ 'Id', 'Username', 'Password', 'First Name',
							'Last Name', "Phone", "Email", "Enabled", "Role" ],
					colModel : [ {
						name : 'id',
						index : 'id',
						width : 55,
						editable : false,
						editoptions : {
							readonly : true,
							size : 10
						},
						hidden : true
					}, {
						name : 'userName',
						index : 'userName',
						width : 100,
						editable : true,
						editrules : {
							required : true,
							custom:true, 
							custom_func:validateUser
						},
						editoptions : {
							size : 20
						}
					}, {
						name : 'password',
						index : 'password',
						width : 100,
						editable : true,
						editrules : {
							required : true
						},
						editoptions : {
							size : 20
						},
						edittype : 'password',
						hidden : true
					}, {
						name : 'firstName',
						index : 'firstName',
						width : 100,
						editable : true,
						editrules : {
							required : true
						},
						editoptions : {
							size : 20
						}
					}, {
						name : 'lastName',
						index : 'lastName',
						width : 100,
						editable : true,
						editrules : {
							required : false
						},
						editoptions : {
							size : 20
						}
					}, {
						name : 'phone',
						index : 'phone',
						width : 100,
						editable : true,
						editrules : {
							required : true
						},
						editoptions : {
							size : 20
						}
					}, {
						name : 'email',
						index : 'email',
						width : 100,
						editable : true,
						editrules : {
							required : true,
							email : true
						},
						editoptions : {
							size : 20
						}
					}, {
						name : 'enabled',
						index : 'enabled',
						width : 50,
						editable : true,
						editrules : {
							required : true
						},
						edittype : "select",
						formatter : 'select',
						stype : 'select',
						editoptions : {
							value : trueOrFalseOption
						},
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + trueOrFalseOption
						}
					}, {
						name : 'roleId',
						index : 'roleId',
						width : 50,
						editable : true,
						editrules : {
							required : true
						},
						edittype : "select",
						formatter : 'select',
						stype : 'select',
						editoptions : {
							value : roles
						},
						searchoptions : {
							sopt : [ 'eq' ],
							value : ":;" + roles
						}
					} ],
					postData : {},
					rowNum : 20,
					rowList : [ 20, 30, 40, 50 ],
					height : 450,
					width : 900,
					rownumbers : true,
					pager : '#pager',
					sortname : 'id',
					viewrecords : true,
					sortorder : "asc",
					caption : "Users",
					emptyrecords : "Empty records",
					loadonce : false,
					hidegrid: false, 
					loadComplete : function() {
					},
					jsonReader : {
						root : "rows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						cell : "cell",
						id : "id"
					}
				});

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
			caption : "Add",
			buttonicon : "ui-icon-plus",
			onClickButton : addRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Edit",
			buttonicon : "ui-icon-pencil",
			onClickButton : editRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Delete",
			buttonicon : "ui-icon-trash",
			onClickButton : deleteRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Export",
			buttonicon : "ui-icon-newwin",
			onClickButton : exportExcel,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		//$("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
	});

	function exportExcel() {
		editUserAction=false;
		jQuery("#grid").jqGrid('excelExport', {
			tag : 'excel',
			url : webContextPath + '/user/export'
		});
	}

	function addRow() {
		editUserAction=false;
		$("#grid").jqGrid('setColProp', 'password', {
			hidden : false
		});
		$("#grid").jqGrid('setColProp', 'password', {
			editrules : {
				required : true
			}
		});
		$('#grid').jqGrid(
				'editGridRow',
				'new',
				{
					url : webContextPath + '/user/create',
					editData : {},
					serializeEditData : function(data) {
						data.id = 0;
						return $.param(data);
					},
					recreateForm : true,
					beforeShowForm : function(form) {
						$('#pData').hide();
						$('#nData').hide();
						$('#password', form).addClass('ui-widget-content')
								.addClass('ui-corner-all');
					},
					beforeInitData : function(form) {
					},
					closeAfterAdd : true,
					reloadAfterSubmit : true,
					afterSubmit : function(response, postdata) {
						//var result = eval('(' + response.responseText + ')');
						if (response.statusCode == 0) {
							$('#msgbox').text(response.message);
							$('#msgbox').dialog({
								title : 'Success',
								modal : true,
								buttons : {
									"Ok" : function() {
										$(this).dialog("close");
									}
								}
							});
						} else {

						}
						// only used for adding new records
						var newId = null;

						return [ "false", "", newId ];
					}
				});

		$("#grid").jqGrid('setColProp', 'password', {
			hidden : true
		});
	}

	function editRow() {
		$("#grid").jqGrid('setColProp', 'userName', {
			editoptions : {
				readonly : true,
				size : 10
			}
		});
		$("#grid").jqGrid('setColProp', 'password', {
			hidden : false
		});
		$("#grid").jqGrid('setColProp', 'password', {
			editrules : {
				required : false
			}
		});

		// Get the currently selected row
		var row = $('#grid').jqGrid('getGridParam', 'selrow');

		if (row != null) {
			editUserAction = true;
			$('#grid').jqGrid('editGridRow', row, {
				url : webContextPath + '/user/update',
				editData : {},
				recreateForm : true,
				beforeShowForm : function(form) {
					$('#pData').hide();
					$('#nData').hide();
				},
				beforeInitData : function(form) {
				},
				closeAfterEdit : true,
				reloadAfterSubmit : true,
				afterSubmit : function(response, postdata) {

					//var result = eval('(' + response.responseText + ')');
					if (response.statusCode == 0) {
						$('#msgbox').text(response.message);
						$('#msgbox').dialog({
							title : 'Success',
							modal : true,
							buttons : {
								"Ok" : function() {
									$(this).dialog("close");
								}
							}
						});
					} else {

					}
					// only used for adding new records
					var newId = null;

					return [ "false", "", newId ];
				}
			});
		} else {
			$('#msgbox').text('You must select a record first!');
			$('#msgbox').dialog({
				title : 'Error',
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
		$("#grid").jqGrid('setColProp', 'userName', {
			editoptions : {
				readonly : false,
				size : 10
			}
		});
		$("#grid").jqGrid('setColProp', 'password', {
			hidden : true
		});

	}

	function deleteRow() {
		editUserAction = false;
		// Get the currently selected row
		var row = $('#grid').jqGrid('getGridParam', 'selrow');

		// A pop-up dialog will appear to confirm the selected action
		if (row != null)
			$('#grid').jqGrid(
					'delGridRow',
					row,
					{
						url : webContextPath + '/user/delete',
						recreateForm : true,
						beforeShowForm : function(form) {
							//Change title
							$(".delmsg").replaceWith(
									'<span style="white-space: pre;">'
											+ 'Delete selected record?'
											+ '</span>');
							//hide arrows
							$('#pData').hide();
							$('#nData').hide();
						},
						reloadAfterSubmit : true,
						closeAfterDelete : true,
						serializeDelData : function(postdata) {
							var rowdata = $('#grid').getRowData(postdata.id);
							// append postdata with any information 
							return {
								id : postdata.id,
								oper : postdata.oper,
								userName : rowdata.userName
							};
						},
						afterSubmit : function(response, postdata) {
							//var result = eval('(' + response.responseText + ')');
							if (response.statusCode == 0) {
								$('#msgbox').text(response.message);
								$('#msgbox').dialog({
									title : 'Success',
									modal : true,
									buttons : {
										"Ok" : function() {
											$(this).dialog("close");
										}
									}
								});
							} else {

							}
							// only used for adding new records
							var newId = null;

							return [ "false", "", newId ];
						}
					});
		else {
			$('#msgbox').text('You must select a record first!');
			$('#msgbox').dialog({
				title : 'Error',
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	}
	
	function validateUser(value, colname){
		var retArray = new Array();
		console.log('...editUserAction....',editUserAction);
		if(editUserAction == false){
		$.ajax({
			type : "get",
			url : webContextPath + '/user/checkUserName/'+value,
			async : false,
			success : function(data, textStatus) {
				console.log("...data...",data);
				if(data){
					console.log('in true');
					retArray.push(false);
					retArray.push("Username already exists in system.");
				}else{
					retArray.push(true);
					retArray.push("");
				}
			}
		});
		}else{
			retArray.push(true);
			retArray.push("");
		}
		console.log('..retArray..',retArray);
		return retArray;
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
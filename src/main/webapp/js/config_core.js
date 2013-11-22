var timeoutHnd;
var flAuto = false;

//On load
$(function() {	
	jQuery("#list10").jqGrid({
		url : '',
		datatype : "json",
		colNames : ['Name', 'Station', 'Zone'],
		colModel : [{
				name : 'name',
				index : 'name',
				width : 90
			}, {
				name : 'station',
				index : 'station',
				width : 90
			}, {
				name : 'zone',
				index : 'zone',
				width : 90
			}
		],
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : '#pager10',
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		multiselect : false,
		caption : "Sensor list",
		onSelectRow : function (ids) {
			if (ids == null) {
				ids = 0;
				if (jQuery("#list10_d").jqGrid('getGridParam', 'records') > 0) {
					jQuery("#list10_d").jqGrid('setGridParam', {
						url : "subgrid.php?q=1&id=" + ids,
						page : 1
					});
					jQuery("#list10_d").jqGrid('setCaption', "Invoice Detail: " + ids).trigger('reloadGrid');
				}
			} else {
				jQuery("#list10_d").jqGrid('setGridParam', {
					url : "subgrid.php?q=1&id=" + ids,
					page : 1
				});
				jQuery("#list10_d").jqGrid('setCaption', "Invoice Detail: " + ids).trigger('reloadGrid');
			}
		}
	});
	jQuery("#list10").jqGrid('navGrid', '#pager10', {
		add : false,
		edit : false,
		del : false
	});
	jQuery("#list10_d").jqGrid({
		height : 100,
		url : '',
		datatype : "json",
		colNames : ['Name', 'Code', 'Description'],
		colModel : [{
				name : 'name',
				index : 'name',
				width : 90
			}, {
				name : 'code',
				index : 'code',
				width : 90
			}, {
				name : 'description',
				index : 'description',
				width : 90
			}
		],
		rowNum : 5,
		rowList : [5, 10, 20],
		pager : '#pager10_d',
		sortname : 'item',
		viewrecords : true,
		sortorder : "desc",
		multiselect : true,
		caption : "Sensor alert's"
	}).navGrid('#pager10_d', {
		add : false,
		edit : false,
		del : false
	});

	jQuery("#add").click(function () {
		alert("add");
	});

	jQuery("#del").click(function () {
		var s;
		s = jQuery("#list10_d").jqGrid('getGridParam', 'selarrrow');
		alert(s);
	});
});


function doSearch(ev) {
	if (!flAuto)
		return; 
	//var elem = ev.target||ev.srcElement; 
	if(timeoutHnd) 
		clearTimeout(timeoutHnd); 
	timeoutHnd = setTimeout(gridReload,500); 
} 

function gridReload(){ 
	var nm_mask = jQuery("#item_nm").val(); 
	var cd_mask = jQuery("#search_cd").val(); 
	jQuery("#bigset").jqGrid('setGridParam',{url:"bigset.php?nm_mask="+nm_mask+"&cd_mask="+cd_mask,page:1}).trigger("reloadGrid"); 
} 

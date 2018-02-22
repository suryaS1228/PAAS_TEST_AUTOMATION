<!DOCTYPE HTML>
<html>
<head>  
<meta charset="UTF-8">
<script>
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Automation Test Report"
	},
	axisY: {
		title: "Validation/Error Count"
	},
	data: [{        
		type: "column",  
		showInLegend: true, 
		legendMarkerColor: "grey",
		legendText: "Validation/Error Description",
		dataPoints: [   
		<#list ReportInformation as result>{ y:${result.value},label:"${result.atrib}"}<#if result?is_last><#else>,</#if>
        </#list>
		]
	}]
});
chart.render();

}

function GenerateTable() {
    //Build an array containing Customer records.
    var customers = new Array();
    customers.push(["S.No", "Validation/Error Description", "Validation/Error Count"]);
    <#assign i=1>
    <#list ReportInformation as result>customers.push([${i},"${result.atrib}","${result.value}"]);
    <#assign i=i+1>
        </#list>
 
    //Create a HTML Table element.
    var table = document.createElement("TABLE");
    table.border = "1";
 
    //Get the count of columns.
    var columnCount = customers[0].length;
 
    //Add the header row.
    var row = table.insertRow(-1);
    for (var i = 0; i < columnCount; i++) {
        var headerCell = document.createElement("TH");
        headerCell.innerHTML = customers[0][i];
        row.appendChild(headerCell);
    }
 
    //Add the data rows.
    for (var i = 1; i < customers.length; i++) {
        row = table.insertRow(-1);
        for (var j = 0; j < columnCount; j++) {
            var cell = row.insertCell(-1);
            cell.innerHTML = customers[i][j];
        }
    }
 
    var dvTable = document.getElementById("dvTable");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>
<script src="../../ReportTemplate/canvasjs.min.js"></script>

<button>
    <a href=""../../Report/${ExcelReport}" download>Click to Download!</a>
</button>
<input type="button" value="View in Table" onclick="GenerateTable()" />
<hr />
<div id="dvTable" align="center">
</div>
</body>
</html>




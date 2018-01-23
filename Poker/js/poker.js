/**
 * 
 */

function addPlayer(tableID) {

	var table = document.getElementById(tableID);

	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var cell1 = row.insertCell(0);
	cell1.innerHTML = rowCount;
	
	var numColumns = table.rows[1].cells.length;
	
	for (i = 1; i < numColumns; i++) {	
	    var cell2 = row.insertCell(i);	
	    var element2 = document.createElement("input");
		element2.type = "text";
		element2.name = "Hand"+rowCount+"Input"+i;
		element2.size = "5";
		cell2.appendChild(element2);	    
	}
	rowCount +=1;
}
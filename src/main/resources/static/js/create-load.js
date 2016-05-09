$(document).ready(function() {
				row = $("<tr></tr>");
				col1 = $("<td><b>Nickname</b></td>");
				col2 = $("<td><b>Group Id</b></td>");
				row.append(col1,col2).prependTo("#table"); 
			});
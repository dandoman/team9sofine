$(document).ready(function() {
				row = $("<tr></tr>");
				col1 = $("<td><b>Name</b></td>");
				col2 = $("<td><b>Category</b></td>");
				col3 = $("<td><b>Description</b></td>");
				row.append(col1,col2,col3).prependTo("#table"); 
				$.get("/api/kink", function(retdata, status) {
					$.each(retdata, function(i, item) {
		                $('<tr>').append(
		                    $('<td>').text(item.name),
		                    $('<td>').text(item.category),
		                    $('<td>').text(item.description)
		                ).appendTo('#table');
					});
				});
				$("form").on("submit",function(event) {
					event.preventDefault();
					var data = {}
			    	var Form = this;
					$.each(this.elements, function(i, v){
		        	    var input = $(v);
		        		data[input.attr("name")] = input.val();
		        		delete data["undefined"];
		    		});
					var jsonData = JSON.stringify(data)
				
					jQuery.ajax ({
   				 		url: "/api/kink/create",
    					type: "POST",
    					data: jsonData,
    					dataType: "json",
    					contentType: "application/json; charset=utf-8",
    					success: function(retData) {
    						$('<tr>').append(
    			                    $('<td>').text(retData.name),
    			                    $('<td>').text(retData.category),
    			                    $('<td>').text(retData.description)
    			                ).appendTo('#table');
    					}
					});
				});
			});
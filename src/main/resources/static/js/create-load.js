$(document).ready(function() {
				row = $("<tr></tr>");
				col1 = $("<td><b>Nickname</b></td>");
				col2 = $("<td><b>Group Id</b></td>");
				row.append(col1,col2).prependTo("#table"); 
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
   				 		url: "/api/kinkster/create-group",
    					type: "POST",
    					data: jsonData,
    					dataType: "json",
    					contentType: "application/json; charset=utf-8",
    					success: function(retData){
    						window.location.replace("/index.html");
    					},
    					failure: function(retData){
    						$('<tr>').append(
    			                    $('<td>').text("FAIL"),
    			                    $('<td>').text("FAIL")
    			                ).appendTo('#table');
        					}	
					});
				});
			});
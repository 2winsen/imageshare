$(document).ready(function() {
	
	$("#clearDBButton").click(function() {
		$.ajax({
			type: 'POST',
			url: CONTEXT_PATH + "/clearDB",
			dataType: "json",
			success: function(data) {
				
			},
			error: CommonModule.generalErrorHandler
		});	
	});

	$("#maintenanceAuthForm").ajaxForm({
		clearForm : false,
		success : function (response) {
			if (response != null) {
				if (response.indexOf("<html>") !== -1) {
					$("html").html(response);
				} else {
					
				}
				
			}
		},
		error : function () {
			
		}
	});
});


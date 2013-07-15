$(document).ready(function() {

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


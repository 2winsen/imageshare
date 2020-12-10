$(document).ready(function() {
	$("#maintenanceAuthForm").ajaxForm({
		clearForm : false,
		success : function (response) {
			if (response != null) {
                if (response.errors != null) {
                    CommonModule.showErrorMessages(response.errors);
                } else {
                    window.location = response.response;
                }
			}
		},
		error: CommonModule.generalErrorHandler
	});
	
});


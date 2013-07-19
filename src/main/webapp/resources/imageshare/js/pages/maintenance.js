$(document).ready(function() {
	
	$("#maintenanceAuthForm").ajaxForm({
		clearForm : false,
		success : function (response) {
			if (response != null) {
				if (response.captchaError != null) {
					CommonModule.showCaptchaErrorMessage(response.captchaError);
				} else {
					if (response.errors != null) {
						CommonModule.showErrorMessages(response.errors);
					} else {
						window.location = response.response;
					}			
				}
			}
		},
		error: CommonModule.generalErrorHandler
	});
	
	$("#clearDBButton").click(function() {
		$.ajax({
			type: 'POST',
			url: CONTEXT_PATH + "/clearDB",
			dataType: "json",
			success: function(response) {
			},
			error: CommonModule.generalErrorHandler
		});	
	});
});


$(document).ready(function() {
	
	$("#maintenanceAuthForm").ajaxForm({
		clearForm : false,
		beforeSubmit: function (formData, jqForm, options) {
			formData[1].value = CryptoJS.SHA256(formData[1].value + CommonModule.salt());
		},
		success : function (response) {
			if (response != null) {
				CommonModule.recaptchaRefresh();
				if (response.captchaError != null) {
					CommonModule.showCaptchaErrorMessage(response.captchaError);
				} else {
					if (response.errors != null) {
						$("#captchaErrorContainer").html('');
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
			url: CONTEXT_PATH + "/maintenance/clearDB",
			dataType: "json",
			success: function(response) {
				CommonModule.showSuccessMessage(response.success);
			},
			error: CommonModule.generalErrorHandler
		});	
	});
});


$(document).ready(function() {

	if ($('#captchaTr').attr('showCaptcha') == 'showCaptcha') {
		$('#captchaTr').attr('style', 'display: table-row;');
	}
	
	$("#maintenanceAuthForm").ajaxForm({
		clearForm : false,
		success : function (response) {
			if (response != null) {
				CommonModule.recaptchaRefresh();
				if (response.captchaError != null) {
					CommonModule.showCaptchaErrorMessage(response.captchaError);
				} else {
					if (response.errors != null) {
						if (response.response == 'showCaptcha') {
							$('#captchaTr').attr('style', 'display: table-row;');
						}
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
	
});


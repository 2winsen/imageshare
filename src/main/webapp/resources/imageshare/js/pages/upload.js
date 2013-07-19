$(document).ready(function() {
	$("#wrap").attr("style", "margin-bottom: -80px");
	
	$("#share1").prop('disabled', true);
	
	$("#imageFile").change(function() {
		enableShareButton();
	});
	$('#termsCheckbox').change(function() {
		enableShareButton();
	});

	$('#share1').click(function() {
		$('#commentModal').modal('show');
	});

	$('#share2').click(function() {
		// Copies comment value from popup
		$('#comment').val($('#commentTemp').val());
		$('#captcha_response').val($('#recaptcha_response_field').val());
		$('#captcha_challenge').val($('#recaptcha_challenge_field').val());
		$('#uploadForm').submit();
	});

	$("#uploadForm").ajaxForm({
		clearForm : false,
		success : function(response) {
			if (response != null) {
				$('#recaptcha_challenge_field').val('');
				Recaptcha.reload();
				if (response.captchaError != null) {
					CommonModule.showCaptchaErrorMessage(response.captchaError);
				} else {
					if (response.errors != null) {
						CommonModule.showErrorMessages(response.errors);
						$('#commentModal').modal('hide');
					} else {
						window.location = response.response;
					}					
				}
			}
		},
		error: CommonModule.generalErrorHandler
	});
});

function enableShareButton() {
	if ($("#imageFile").val() && $('#termsCheckbox').is(':checked')) {
		$("#share1").prop('disabled', false);
	} else {
		$("#share1").prop('disabled', true);
	}
}

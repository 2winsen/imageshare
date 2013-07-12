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
				if (response.captchaError != null) {
					showCaptchaError(response.captchaError);
				} else {
					if (response.errors != null) {
						for ( var i = 0; i < response.errors.length; i++) {
							// modal close
							showAlert(response.errors[i]);
						}
					} else {
						window.location = response.response;
					}					
				}
			}
		}
	});
});

function showAlert(text) {
	var alertHtml = "<div class='alert alert-error'>"
			+ "<strong>Warning!</strong>&nbsp;<span>"
			+ text + "</span>" + "</div>";
	$("#errorsContainer").append(alertHtml);
}

function showCaptchaError(text) {
	var errorHtml = "<div class='alert alert-error'>"
		+ "<strong>Warning!</strong>&nbsp;<span>"
		+ text + "</span>" + "</div>";
	$("#captchaErrorContainer").html(errorHtml);
}

function enableShareButton() {
	if ($("#imageFile").val() && $('#termsCheckbox').is(':checked')) {
		$("#share1").prop('disabled', false);
	} else {
		$("#share1").prop('disabled', true);
	}
}

$(document).ready(function() {
	$("#share1").prop('disabled', true);
	
	$("#imageFile").change(function() {
		enableShareButton();
	});
	$('#termsCheckbox').change(function() {
		enableShareButton();
	});

	$('#share1').click(function() {
		$('#commentModal').modal('show');
		$('#commentTemp').val(null);
	});

	$('#share2').click(function() {
		// Copies comment value from popup
		$('#comment').val($('#commentTemp').val());
		$('#commentTemp').val(null);
		$('#uploadForm').submit();
	});

	$("#uploadForm").ajaxForm({
		clearForm : true,
		success : function(response) {
			if (response != null) {
				if (response.errors != null) {
					for ( var i = 0; i < response.errors.length; i++) {
						showAlert(response.errors[i]);
					}
				} else {
					window.location = response.response;
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

function enableShareButton() {
	if ($("#imageFile").val() && $('#termsCheckbox').is(':checked')) {
		$("#share1").prop('disabled', false);
	} else {
		$("#share1").prop('disabled', true);
	}
}

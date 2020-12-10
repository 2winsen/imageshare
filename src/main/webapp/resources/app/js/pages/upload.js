$(document).ready(function() {
	$("#share1").prop('disabled', true);
	$('#uploadForm')[0].reset();
	
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
		$('#uploadForm').submit();
	});

	$("#uploadForm").ajaxForm({
		clearForm : false,
		success : function(response) {
			if (response != null) {
                if (response.errors != null) {
                    CommonModule.showErrorMessages(response.errors);
                    $('#commentModal').modal('hide');
                } else {
                    window.location = response.response;
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

$(document).ready(function() {
	$("#imageFile").change(function(){
		var fileName = $("#imageFile").val();
		if (fileName) {
			$("#share1").removeAttr('disabled');
		}
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
			+ "<button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>&nbsp;<span>"
			+ text + "</span>" + "</div>";
	$("#errorsContainer").append(alertHtml);
}

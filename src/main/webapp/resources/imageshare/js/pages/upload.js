$(document).ready(function() {
	$('#share1').click(function() {
		$('#commentModal').modal('show');
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
			var a = 10;
			var b = 10;
			var c = a + b;
		}
	});
});
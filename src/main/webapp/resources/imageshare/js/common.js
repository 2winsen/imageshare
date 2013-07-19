var CommonModule = (function() {
	
	return {
		generalErrorHandler : function(jqXHR, textStatus, errorThrown) {
			console.log("Error with server side data: " + textStatus);
			CommonModule.showErrorMessages(["Server side error occured. Please try again in few seconds."]);
		},
		
		showErrorMessages : function(messages) {
			var errorMessage = '';
			$.each(messages, function(index, value) {
				errorMessage += value + '\n';
			});
			var alertHtml = "<div class='alert alert-error'>"
				+ "<strong>Warning!</strong>&nbsp;<span>"
				+ errorMessage + "</span>" + "</div>";
			$("#errorsContainer").append(alertHtml);
		},
		
		showCaptchaErrorMessage : function(text) {
			var errorHtml = "<div class='alert alert-error'>"
				+ "<strong>Warning!</strong>&nbsp;<span>"
				+ text + "</span>" + "</div>";
			$("#captchaErrorContainer").html(errorHtml);
		},
		
		showSuccessMessage : function(text) {
			
		}
	};
	
}());

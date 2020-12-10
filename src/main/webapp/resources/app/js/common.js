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
			var messageHtml = "<div class='alert alert-error'>"
				+ "<strong>Warning!</strong>&nbsp;<span>"
				+ errorMessage + "</span>" + "</div>";
			$("#messagesContainer").append(messageHtml);
		},
		
		showSuccessMessage : function(text) {
			var messageHtml = "<div class='alert alert-info'>"
				+ "<strong>Success.</strong>&nbsp;<span>"
				+ text + "</span>" + "</div>";
			$("#messagesContainer").append(messageHtml);
		}
	};
	
}());

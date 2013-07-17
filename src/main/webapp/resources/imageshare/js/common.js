var CommonModule = (function() {
	
	return {
		generalErrorHandler : function(jqXHR, textStatus, errorThrown) {
			console.log("Error with server side data: " + textStatus);
			CommonModule.showErrorMessage("Server side error occured. Please try again in few seconds.");
		},
		
		showErrorMessage : function(text) {
			
		},
		
		showSuccessMessage : function(text) {
			
		}
	};
	
}());

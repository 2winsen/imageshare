$(document).ready(function() {
	var clip = new ZeroClipboard($("#copyToClipboard"), {
		moviePath : $("#ZeroClipboard_swf").val()
	});

	clip.on('mousedown', function(client) {
		$('#copyToClipboard').tooltip('show');
	});
	
	clip.on('mouseout', function(client) {
		$('#copyToClipboard').tooltip('destroy');
	});
});

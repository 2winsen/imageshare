
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4 offset4">
			<form id="uploadForm" action="" method="post"
				enctype="multipart/form-data" class="cleanform">
				<input type="file" value="browse" name="file" /> 
				<input id="comment" type="hidden" name="comment" />
				<button id="share1" class="btn btn-large btn-primary" type="button">Share...</button>
			</form>
		</div>
	</div>
</div>
<div id="result"></div>

<!-- Modal -->
<div id="commentModal" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="commentModal" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&#x2715;</button>
		<h3 id="myModalLabel">Add comment (optional)</h3>
	</div>
	<div class="modal-body">
		<input id="commentTemp" type="text" />
	</div>
	<div class="modal-footer">
		<button id="share2" class="btn btn-primary" data-dismiss="modal"
			aria-hidden="true">Share!</button>
	</div>
</div>

<script>
	// JQuery block
	// ============
	$(document).ready(function() {
		$('#share1').click(function() {
			$('#commentModal').modal('show');
		});
		
		$('#share2').click(function() {
			// Copies comment value from popup
			$('#comment').val($('#commentTemp').val());
			$('#uploadForm').submit();
		});

		$("#uploadForm").ajaxForm({
			clearForm : true,
			success : function(html) {
				$('#result').text(html);
			}
		});
	});

	// Functions block
	// ===============
</script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4 offset4">
			<form:form id="uploadForm" method="post" modelAttribute="image">
				<form:input type="file" value="browse" path="file" /> 
				<form:input id="comment" type="hidden" path="comment" />
				<input id="share1" class="btn btn-large btn-primary" type="button" value="Share..." />
			</form:form>
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
			$('#commentTemp').val(null);
			$('#uploadForm').submit();
		});

		$("#uploadForm").ajaxForm({
			clearForm : true,
			success : function(response) {
//				$('#result').text(html);
			}
		});
	});

	// Functions block
	// ===============
</script>
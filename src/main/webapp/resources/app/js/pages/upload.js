var droppedFile = false;
$(document).ready(function() {
    var $form = $('#uploadForm');
    var ajaxData = new FormData($form.get(0));

    $form.on('drag dragstart dragend dragover dragenter dragleave drop', function(e) {
        e.preventDefault();
        e.stopPropagation();
    })
    .on('dragover dragenter', function() {
        $form.addClass('is-dragover');
    })
    .on('dragleave dragend drop', function() {
        $form.removeClass('is-dragover');
    })
    .on('drop', function(e) {
        var file = e.originalEvent.dataTransfer.files[0];
        ajaxData.set('file', file);
        preview();
        toggleShareButton();
    });

	$("#imageFile").change(function() {
        ajaxData.set('file', this.files[0]);
        preview();
		toggleShareButton();
	});
	$('#termsCheckbox').change(function() {
		toggleShareButton();
	});

	$('#share1').click(function() {
		$('#commentModal').modal('show');
	});

	$('#share2').click(function() {
		ajaxData.set('comment', $('#comment').val());
        $.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            data: ajaxData,
            processData: false,
            contentType: false,
            cache: false,
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

	function toggleShareButton() {
    	if (ajaxData.has('file') && $('#termsCheckbox').is(':checked')) {
    		$("#share1").prop('disabled', false);
    	} else {
    		$("#share1").prop('disabled', true);
    	}
    }

    function preview() {
        var reader = new FileReader();
        reader.onload = function(e) {
            var $img = $('<img />', {
                src: e.target.result
            });
            $('#uploadForm .preview').html($img);
        };
        reader.readAsDataURL(ajaxData.get('file'))
    }
});

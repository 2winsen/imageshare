$(document).ready(function() {
    var $form = $('#maintenanceAuthForm');

    $form.submit(function(e) {
        e.preventDefault();
        var ajaxData = new FormData($form.get(0));
        $.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            data: ajaxData,
            processData: false,
            contentType: false,
            cache: false,
            success : function (response) {
                if (response != null) {
                    if (response.errors != null) {
                        CommonModule.showErrorMessages(response.errors);
                    } else {
                        window.location = response.response;
                    }
                }
            },
            error: CommonModule.generalErrorHandler
        });
    });
});


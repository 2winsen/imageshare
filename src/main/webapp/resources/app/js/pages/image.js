$(document).ready(function() {
    function copy() {
        var $copiedNotification = $("#copied-notification");
        var urlInput = $("#url-input").get(0);
        urlInput.select();
        document.execCommand("copy");
        $copiedNotification.addClass("copied");
        var animationInterval = setInterval(function(){
          $copiedNotification.removeClass("copied");
          clearInterval(animationInterval);
        }, 1000);
    }

    $("#copy-to-clipboard-btn").click(copy);
});

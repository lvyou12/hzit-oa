/**
 * Created by lvyou on 2017/9/26.
 */
$(function(){
    $("#institutionList").click(function() {
        $("#myFrame").attr("src","/institutionInfo/institutionList");
    });
    $("#employeeList").click(function() {
        $("#myFrame").attr("src","/employeeInfo/employeeList");
    });
    $("#home").click(function(){
        $("#myFrame").attr("src","/home");
    });

    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    };

    window.onload = function () {
        setIframeHeight(document.getElementById('myFrame'));
    };
});
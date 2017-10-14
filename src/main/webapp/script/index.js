/**
 * Created by lvyou on 2017/9/26.
 */
$(function(){
    $("#sideList > li > a").on("click",function(){
        var obj = this;
        $("#myFrame").attr("src",$(obj).data("url"));
    });
    $("#sideList > li > ul > li >a").on("click",function(){
        var obj = this;
        $("#myFrame").attr("src",$(obj).data("url"));
    });

    //$("#institutionList").click(function() {
    //    $("#myFrame").attr("src","/institutionInfo/institutionList");
    //});
    //$("#employeeList").click(function() {
    //    $("#myFrame").attr("src","/employeeInfo/employeeList");
    //});
    //$("#home").click(function(){
    //    $("#myFrame").attr("src","/home");
    //});
    //$("#roleList").click(function(){
    //    $("#myFrame").attr("src","/role/roleList");
    //});


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
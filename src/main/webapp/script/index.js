/**
 * Created by lvyou on 2017/9/26.
 */
$(function(){
    $("#institutionList").click(function() {
        $("#myFrame").attr("src","/institutionInfo/institutionList");
    });
    $("#home").click(function(){
        $("#myFrame").attr("src","/home");
    });
});
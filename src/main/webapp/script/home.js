/**
 * Created by lvyou on 2017/9/27.
 */
$(function(){
    $("#showMore").click(function(){
        var ifm = parent.document.getElementById("myFrame");
        ifm.src = "/institutionInfo/institutionList";
    })
});
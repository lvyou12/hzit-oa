/**
 * Created by lvyou on 2017/9/27.
 */
$(function(){

    $.getJSON("",function(resp){

    });

    $("#showMore").click(function(){
        var ifm = parent.document.getElementById("myFrame");
        ifm.src = "/institutionInfo/institutionList";
    });


    $(".sideList > li > a").each(function(){
        var _this = $(this);
        _this.on("click",function(){
            var path = _this.attr("name");
            layer.open({
                type: 2,
                title: "制度预览",
                content: "/institutionInfo/showInstitution?path="+path,
                area:['860px','670px'],
                offset:['20px']
            })
        });
    });
});
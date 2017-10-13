/**
 * Created by lvyou on 2017/9/27.
 */
$(function(){

    $.getJSON("/institutionInfo/listData?offset=1&limit=6",function(resp){
        var result = "<span id='pin' class='glyphicon glyphicon-pushpin' aria-hidden='true'></span>"+
        "<span class='list-title'>部门最新制度</span>";
        for(var i = 0; i < resp.rows.length; i++){
            //console.log(i%3 == 0)
            result += "<li>";
            if(i % 3 == 0){
                result += "<span class='num1'>"+(i+1)+"</span>";
            }
            if(i % 3 == 1){
                result += "<span class='num2'>"+(i+1)+"</span>";
            }
            if(i % 3 == 2){
                result += "<span class='num3'>"+(i+1)+"</span>";
            }
            result += "<a name='"+resp.rows[i].path+resp.rows[i].instName+"'>"+resp.rows[i].instName+"</a>";
            result += "</li>";
        }
        result += "<span id='showMore' class='more'>查看更多...</span>"
        $(".sideList").html(result);

        $("#showMore").on("click",function(){
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
                    offset:['10px']
                })
            });
        });
    });


});
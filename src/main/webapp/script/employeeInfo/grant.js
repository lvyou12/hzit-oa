$(function(){
    //layui.use(["layer","jquery"],function() {
    //    var url = "/employeeInfo/listData";
    //    var layer = layui.layer;
    //});
    $("#confirm").click(function(){
        var selectedRoles = $("#duallist").val().toString();
        var userId = $("#currUserId").val();
        //layer.msg(m.toString());
        $.post("/employeeInfo/editEmployee",{userId:userId,roleName:selectedRoles},function(result){
            if(result.code == 200){
                var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
                layer.msg(result.msg);
                setTimeout(function () {
                    parent.layer.close(index);
                }, 1000);
            }else{
                layer.msg(result.msg);
            }
        });

    });


});
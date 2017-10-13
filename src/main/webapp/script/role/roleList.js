/**
 * Created by lvyou on 2017/10/12.
 */
$(function(){
    $("#addRole").click(function(){
        layer.open({
            type:2,
            title:'添加角色',
            shadeClose:true,
            shade:0,
            maxmin:true,
            area:["500px","350px"],
            offset:['100px'],
            content:['/role/addRole'],
            end:function(layer,index){
                $("#table").bootstrapTable("refresh"); //刷新
            }
        });
    });
});
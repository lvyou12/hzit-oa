/**
 * Created by lvyou on 2017/10/12.
 */
$(function(){
    var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    $("#cancel").click(function(){
        parent.layer.close(index);
    });

    $("#role").blur(function(){
        var roleName = $("#role").val();
        if(roleName == null || roleName === ""){
            layer.msg("角色名不能为空!");
            return ;
        }
        if(!new RegExp("[\u4e00-\u9fa5]").test(roleName)){
            layer.msg("角色名只能用中文!");
            return ;
        }
        $.post("/role/checkRole",{roleName:roleName},function(resp){
            console.log(resp);
        },"JSON");
    });

    layui.use(["form","layedit"],function(){
        var form = layui.form,
            layer = layui.layer;
        form.verify({
            roleName:function(value,item){
                if(value == null || value === ""){
                    return "角色名不能为空!";
                }
                if(!new RegExp("[\u4e00-\u9fa5]").test(value)){
                    return "角色名只能用中文!";
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data){
            $.post("/role/addRole",data.field,function(resp){
                console.log(resp);
            },"JSON");
            return false;
        });
    });
});
/**
 * Created by lvyou on 2017/10/13.
 */
$(function(){
    var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    $("#cancel").click(function(){
        parent.layer.close(index);
    });

    layui.use(["form"],function(){
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
        form.on('submit(edit)', function(data){
            $.post("/role/editRole",data.field,function(resp){
                console.log(resp);
            },"JSON");
            //setTimeout(function(){parent.layer.close(index)},2000);
        });
    });
});
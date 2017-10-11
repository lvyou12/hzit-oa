/*
    添加用户信息
 */
var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
$(function(){
   $("#cancel").click(function(){
      parent.layer.close(index);
   });

   $('input[name="name"]').blur(function(){
      $.get('/employeeInfo/checkEmployeeInfo?name='+$(this).val(),function(result){
          if(result.code ==200){
             layer.msg(result.msg);
             $('#add').removeClass('layui-btn layui-btn-small add-disable').addClass('layui-btn layui-btn-small');
          }else{
              $('#add').removeClass('layui-btn layui-btn-small').addClass('layui-btn layui-btn-small add-disable');
             layer.alert(result.msg);
          }
      });
   });

   $("#deptDiv").click(function(){
       layer.msg('请先选择所在公司!');
       $("#companyId").css('border-color','red');
      //var result =  this.children()[1];
      //if(typeof(result)  =='undefined'){
      //    layer.msg('请选择公司!');
      //}
   });

    layui.use(['form'],function(){
        var form = layui.form;
        form.on('select(companyId)', function (data) {
            if(data.value == ''){
                layer.msg('请选择公司!');
                $("#companyId").css('border-color','red');
                return false;
            }else{
                //到服务器中获取数据
                //根据pid获取部门信息
                $.get('/employeeInfo/getDept?companyId='+data.value,function(result){
                    //拼接!!
                    //清空 部门select标签中的内容
                    var $deptId = $("#deptId");
                    $deptId.children().remove();
                    var option = null;
                    $.each(result,function(item){
                        option = $('<option value="'+result[item].deptId+'">'+result[item].deptName+'</option>');
                        $deptId.append(option);
                    });
                    form.render();
                });
            }
        });

        form.on('select(deptId)', function (data) {
            if(data.value == ''){
                layer.msg('请选择部门!');
                $("#deptId").css('border-color','red');
                return false;
            }
        });

    });
    layui.use(['layer','form'], function () {
        var form = layui.form, layer = layui.layer;
        //自定义验证规则
        form.verify({
            tel: function (value) {
                var isMob = /1[2345678]\d{10}$/;
                if (value == '') {
                    return '请输入电话号码!'
                } else if (isMob.test(value)) {
                    return '电话号码格式不正确!'
                }
            },
            companyId: function (value) {
                if (value == '') {
                    return '请选择公司!';
                }
            },
            deptId:function(value){
                if(value==''){
                    return '请选择部门!';
                }
            }

        });
        //监听提交
        form.on('submit(add)', function (data) {
            //loading层
            var index2 = layer.load(1, {time:15*1000},{shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            //校验表单
            //提交表单
            $.ajax({
                type: 'post',
                url: '/employeeInfo/add',
                data: $('#addEmployeeInfoForm').serialize(),
                success: function (result) {
                    if (result.code == 200) {
                        layer.close(index2); //关闭当前弹层
                        layer.msg(result.msg);
                        window.setTimeout(function () {
                            parent.layer.close(index);//关闭层
                        }, 1000);
                    } else {
                        layer.msg(result.msg);
                        layer.close(index2); //关闭当前弹层
                    }
                }
            });
        });
    });

});


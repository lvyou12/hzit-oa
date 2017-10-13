/**
 * Created by lvyou on 2017/10/13.
 */
$(document).ready(function() {
    $("#name").focus(function(){
        $("#loginname_text").html("&nbsp;");
    });

    $("#psd").focus(function(){
        $("#loginpass_text").html("&nbsp;");
    });

    /*$("#check").focus(function(){
     $("#logincheck_text").html("&nbsp;");
     });*/

});

$(function() {

    $('#login').click(function() {
        var name_state = $('#name');
        var psd_state = $('#psd');
        /*var check_state = $('#check');*/

        var name = $('#name').val();
        var psd = $('#psd').val();
        /*var check = $('#check').val();*/
        if (name == '') {
            $("#loginname_text").html("请输入用户名/企业邮箱");
            return false;
        } else if (psd == '') {
            $("#loginname_text").html("&nbsp;");
            $("#loginpass_text").html("请输入密码");
            return false;
        }/*else if (check == '') {
         $("#loginname_text").html("&nbsp;");
         $("#loginname_text").html("&nbsp;");
         $("#logincheck_text").html("请输入验证码");
         return false;
         }*/else{
            $("#loginname_text").html("&nbsp;");
            $("#loginname_text").html("&nbsp;");
            /*$("#logincheck_text").html("&nbsp;");*/
//				<%-- var randCheckCode = '<%=Session["randCheckCode"] %>'; --%>
//				var pswd = MD5("hzit#" + psd);
//				var	data = {password:pswd,name:name,email:name,rememberMe:$("#rememberMe").is(':checked')};
            var	data = {password:psd,name:name,email:name};
            var load = layer.load();
            $.post("/employeeInfo/login",data ,function(result) {
                layer.close(load);
//					console.log(result);
                if(result.code != 200){
                    layer.msg(result.msg,function(){});
                    return;
                }else{
                    layer.msg(result.msg);
                    setTimeout(function(){
                        //登录成功进入主页
                        window.location.href= "/index";
                    },100);
                }
            },"json");

            /* $('.login').submit(); */
        }
    });

    $("body").keydown(function(event) {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('#login').click();
        }
    });

    $("#resetPwd").click(function(){
        var email = $("#email").val();
        if(email == ''){
            layer.msg("邮箱输入不能为空");
        }else{
            $.post("/employeeInfo/resetPwd",{email:email},function(result){
                if(result.code != 200){
                    layer.msg(result.msg);
                    return;
                }else{
                    layer.msg(result.msg);
                    setTimeout(function(){
                        //密码重置成功返回登录页面
                        window.location.href= "/login";
                    },2000);
                }
            })
        }

    })


})
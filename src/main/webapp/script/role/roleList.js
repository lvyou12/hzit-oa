/**
 * Created by lvyou on 2017/10/12.
 */
$(function(){
    layui.use(["table","layer","jquery"],function(){
        var table = layui.table,
            layer = layui.layer,
            $ = layui.$
            ; //获取选中数据

        var roleTable = table.render({
            elem: '#roleTable',
            id:'roleTable',
            width:'auto',
            loading:true,
            limits: [10,20,30,35],
            limit: 10, //默认采用60
            cols: [
                [{
                    checkbox: true,
                    width: 60,
                    fixed: true
                },{
                    field: 'roleName',
                    // edit:true,  //单元格编辑
                    width: 120,
                    align: 'center',
                    title: '角色名'
                },{
                    field:'available',
                    title:'禁用',
                    width:80,
                    align: 'center',
                    templet:'#isLockTpl'
                },{
                    field:'createBy',
                    title:"录入人",
                    align: 'center',
                    width:100
                },{
                    field:'createTime',
                    title:'录入时间',
                    align: 'center',
                    width:200
                },{
                    field:'updateBy',
                    title:'修改人',
                    align: 'center',
                    width:100
                },{
                    field:'updateTime',
                    title:'修改时间',
                    align: 'center',
                    width:200
                },{
                    title: '常用操作',
                    width: 252,
                    align: 'center',
                    toolbar: '#roleBar'
                }]

            ],
            url: '/role/roleAjaxData',
            page: true,
            even: true
        });

        table.on('tool(roleTables)',function(obj){
            var data = obj.data;
            var event = obj.event;
            if(event == "edit"){
                layer.open({
                    type:2,
                    title:'修改角色',
                    shadeClose:true,
                    shade:0,
                    maxmin:true,
                    area:["500px","350px"],
                    offset:['100px'],
                    content:'/role/editRole?roleId='+data.roleId,
                    end:function(layer,index){
                        table.reload('roleTable');
                    }
                });
            }else if(event == 'grant'){
                layer.msg("待会写!");
            }else if(event == 'disable'){
                // layer.alert('禁用行：<br>' + JSON.stringify(data))
                var isLock;
                if(data.available == 0){
                    //启用
                    isLock =1;
                    setTimeout(function(){
                        table.reload();
                    },1500);
                }else{
                    //禁用
                    isLock =0;
                    setTimeout(function(){
                        table.reload();
                    },1500);
                }
                var reqData = 'roleId='+data.roleId +"&isLock="+isLock;
                //异步修改数据
                $.getJSON("/role/disableRole?roleIds="+data.roleId+"&type="+isLock,function(resp){
                    if(resp.code === 300){
                        layer.msg(resp.msg,{icon:2,time:1000});
                    }
                    if(resp.code === 200){
                        layer.msg(resp.msg,{icon:1,time:1000});
                        setTimeout(table.reload('roleTable'),2000);
                    }
                });
            }else if(event == 'del'){
                layer.confirm("是否确定要删除所选角色",{
                    btn : ['确定','反悔了'],
                    offset:['100px']
                },function(){
                    $.getJSON("/role/deleteRole?roleIds="+data.roleId,function(resp){
                        if(resp.code === 300){
                            layer.msg(resp.msg,{icon:2,time:1000});
                        }
                        if(resp.code === 200){
                            layer.msg(resp.msg,{icon:1,time:1000});
                            setTimeout(table.reload('roleTable'),2000);
                        }
                    });
                });
            }
        });

        /**
         * 添加角色
         */
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
                    table.reload('roleTable');
                }
            });
        })

        /**
         * 修改角色
         */
        $("#editRole").click(function(){
            var checkStatus =table.checkStatus("roleTable")
            var roleList = checkStatus.data;
            if(roleList.length === 0 ){
                layer.msg("请选择要修改的数据!");
                return ;
            }else if(roleList.length > 1 ){
                layer.msg("选择的数据大于一条!");
                return ;
            }else{
                layer.open({
                    type:2,
                    title:'修改角色',
                    shadeClose:true,
                    shade:0,
                    maxmin:true,
                    area:["500px","350px"],
                    offset:['100px'],
                    content:'/role/editRole?roleId='+roleList[0].roleId,
                    end:function(layer,index){
                        table.reload('roleTable');
                    }
                });
            }
        });

        /**
         * 禁用角色
         */
        $("#disableRole").click(function(){
            var checkStatus =table.checkStatus("roleTable")
            var roleList = checkStatus.data;
            var ids = new Array();
            if(roleList.length === 0){
                layer.msg("请选择要禁用的角色!");
                return ;
            }
            for(var i = 0;i < roleList.length; i++){
                ids[i] = roleList[i].roleId;
            }
            $.getJSON("/role/disableRole?roleIds="+ids+"&type="+0,function(resp){
                if(resp.code === 300){
                    layer.msg(resp.msg,{icon:2,time:1000});
                }
                if(resp.code === 200){
                    layer.msg(resp.msg,{icon:1,time:1000});
                    setTimeout(table.reload('roleTable'),2000);
                }
            });
        });

        /**
         * 删除角色
         */
        $("#deleteRole").click(function(){
            var checkStatus =table.checkStatus("roleTable")
            var roleList = checkStatus.data;
            var ids = new Array();
            if(roleList.length === 0){
                layer.msg("请选择要删除的角色!");
                return ;
            }
            for(var i = 0;i < roleList.length; i++){
                ids[i] = roleList[i].roleId;
            }
            layer.confirm("是否确定要删除所选角色",{
                btn : ['确定','反悔了'],
                offset:['100px']
            },function(){
                $.getJSON("/role/deleteRole?roleIds="+ids,function(resp){
                    if(resp.code === 300){
                        layer.msg(resp.msg,{icon:2,time:1000});
                    }
                    if(resp.code === 200){
                        layer.msg(resp.msg,{icon:1,time:1000});
                        setTimeout(table.reload('roleTable'),2000);
                    }
                });
            });
        });
    });
});


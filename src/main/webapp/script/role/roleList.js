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
            var event = obj.event;
            if(event){}
        });

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
                console.log(roleList[0]);
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
    });
});


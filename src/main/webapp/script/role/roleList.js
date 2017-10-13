/**
 * Created by lvyou on 2017/10/12.
 */
$(function(){
    layui.use(["table","layer","jquery"],function(){
        var table = layui.table,
            layer = layui.layer,
            $ = layui.$;

        var roleTable = table.render({
            elem: '#roleTable',
            id:'roleTable',
            width:'auto',
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
    });
});


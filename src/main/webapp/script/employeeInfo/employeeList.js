$(function(){

    //**********************************Layui表格数据开始*****************************************
    layui.use(["table","layer","jquery"],function() {
        var url = "/employeeInfo/listData";
        var table = layui.table,
            layer = layui.layer,
            $ = layui.$,
            active = {
            reload: function(){
                table.reload('employeeTable', {
                    url: url,
                    where: {
                        condition:$('#searchParam option:selected').val(),//搜索条件
                        value: $('#searchValue').val()//搜索值

                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        var employeeTable = table.render({
            elem: '#table',
            id:'employeeTable',
            width:'auto',
            limits: [10, 15, 20, 25],
            limit: 20, //默认采用60
            url: url,
            page: true,
            even: true,
            //skin: 'line', //行边框风格
            cols: [
                [{
                    filed: 'userId',
                    title: '编号',
                    checkbox: true,
                    visible: true
                },{
                    field: 'userName',
                    title: '姓名',
                    align : 'center',
                    width: 140
                },{
                    field: 'email',
                    title: '企业邮箱',
                    align : 'center',
                    width: 200
                },{
                    field: 'deptName',
                    title: '所属部门',
                    align : 'center',
                    width: 100
                },{
                    field: 'companyName',
                    title: '所属公司',
                    align : 'center',
                    width: 120
                },{
                    field: 'roleName',
                    title: '角色',
                    align : 'center',
                    width: 120
                }, {
                    field: 'isDimission',
                    title: '是否在职',
                    align : 'center',
                    width: 90,
                    templet: '#isDimission'
                }, {
                    field: 'isEmailActive',
                    title: '是否发送邮件',
                    align : 'center',
                    width: 120,
                    templet: '#isEmailActive'
                },{
                    field: 'createTime',
                    title: '创建时间',
                    align : 'center',
                    width: 190
                } ,{
                    title: '常用操作',
                    fixed: 'right',
                    width:180,
                    align:'center',
                    toolbar: '#employeeBar'
                }]
            ]
        });

        //添加用户
        $('#add').click(function(){
            layer.open({
                type: 2,
                title: '添加用户<span style="color:red;">(新添加用户时由于网络原因可能会造成邮件的发送会较慢)</span>',
                shadeClose: true,
                shade: 0,
                maxmin: true,
                area: ['50%', '80%'],
                content: '/employeeInfo/add', //iframe的url
                success:function(layer,index){
                },end:function() {
                   table.reload('employeeTable',{url:url}); //刷新
                }
            });
        });

        /**
         * 监听工具条
         */
        table.on('tool(employeeTables)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'detail'){ //查看
                //do somehing
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){ //编辑
                //do something

                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    ,title: 'xxx'
                });
            }
        });

    });

    //**********************************Layui表格数据结束*****************************************

    /**
     * 搜索
     */

    //$("#searchInst").click(function(){
    //    var searchParam = $('#searchParam option:selected').val();
    //    var searchValueType = $('#searchValue').attr("type");
    //    var createTimeType =$("#date").attr("type");
    //
    //    var searchValue = $("#searchValue").val();
    //    var createTime = $("#date").val();
    //
    //    if(searchParam==''){
    //        layer.msg('请选择搜索条件');
    //        $('#searchParamDiv').css('border','1px solid red');
    //        return false;
    //    }else{
    //        $('#searchParamDiv').css('border','1px solid lightgrey');
    //    }
    //    if(searchValueType == 'text'){
    //        if(searchValue==''){
    //            layer.msg('请输入制度名称');
    //            $('#searchValue').css('border','1px solid red');
    //            return false;
    //        }else{
    //            $('#searchValue').css('border','1px solid lightgrey');
    //        }
    //        var value= url+"?offset="+searchParams.offset+"&limit="+searchParams.limit+"&condition="+searchParam+"&value="+searchValue;
    //    }
    //    if(createTimeType == 'text'){
    //        if(createTime==''){
    //            layer.msg('请选择上传时间');
    //            $('#date').css('border','1px solid red');
    //            return false;
    //        }else{
    //            $('#date').css('border','1px solid lightgrey');
    //        }
    //        var value= url+"?offset="+searchParams.offset+"&limit="+searchParams.limit+"&condition="+searchParam+"&value="+createTime;
    //    }
    //
    //    $.get(value,function(result){
    //        $("#table").bootstrapTable("load",result);
    //        var tmp = {
    //            offset:searchParams.offset,
    //            limit:searchParams.limit,
    //            condition:searchParam,
    //            value:searchValue //,
    //
    //        };
    //        searchParams = tmp;
    //    });
    //
    //});


    ///**
    // * 制度上传
    // */
    //$("#import").click(function(){
    //    layer.open({
    //        type:2,
    //        title:'上传制度',
    //        shadeClose:true,
    //        shade:0,
    //        maxmin:true,
    //        area:["680px","468px"],
    //        content:['/institutionInfo/importPage','on'],
    //        end:function(layer,index){
    //            $("#table").bootstrapTable("refresh"); //刷新
    //        }
    //
    //    })
    //});



});
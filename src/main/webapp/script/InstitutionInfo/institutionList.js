$(function(){

    //*********************************操作开始***************************************
    window.operateEvents = {
        'click .remove_coupon_click' : function(e, value, row, index) {
            var index2 = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            layer.confirm('确认删除该制度吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                removeData(row);
            }, function(){
                layer.close(index2); //关闭当前弹层
                layer.msg('已经取消了!');

            });
            function  removeData(row) {
                $.ajax({
                    type: 'post',
                    url: '/institutionInfo/deleteData',
                    data: row,
                    success: function (result) {
                        layer.close(index2); //关闭当前弹层
                        if (result.code == 200) {
                            layer.msg("操作成功!");
                            $("#table").bootstrapTable("refresh"); //刷新
                        } else {
                            layer.msg('操作失败!');
                        }
                    }
                });
            }
        },
        'click .download_coupon_click' : function(e, value, row, index) {

        }
    };
    //*******************************操作结束*************************************
    var searchParams;
    var url = "/institutionInfo/listData";

    var json ={
        url: url,
        toolbar: '#toolbar',                //工具按钮用哪个容器
        pagination: true,                   //是否显示分页（*）
        striped: true,                      //是否显示行间隔色
        sidePagination: "server",           //分页方式：client学员端分页，server服务端分页（*）
        idField: 'instId',
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 15, 20, 25],        //可供选择的每页的行数（*）
        //clickToSelect: true,                //是否启用点击选中行
        smartDisplay: false, // 智能显示 pagination 和 cardview 等
        //exportDataType: "basic",              //basic', 'all', 'selected'.
        showRefresh: true,
        showColumns: true,
        //detailView: true,

        columns: [{
            filed: 'instId',
            title: '编号',
            checkbox: true,
            visible: false
        }, {
            field: 'name',
            title: '制度名称',
            align : 'center',
            width: 450,
            formatter: function (value, row, index) {
                return "<a herf='"+row.path+row.name+"'>"+row.name+"</a>"
            }
        },{
            field: 'createBy',
            title: '上传者',
            align : 'center',
            width: 160
        }, {
            field: 'createTime',
            title: '上传时间',
            align : 'center',
            width: 220,
            formatter: function (value, row, index) {
                return new Date(parseInt(row.createTime)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ")
            }
        },{
            field:'operate',
            title : '操作',
            align : 'center',
            with: 10,
            events : operateEvents,
            formatter : function(value, row, index) {
                    return [
                        '<a class="download_coupon_click" href="javascript:void(0)" title="Download">',
                         '<i class="glyphicon glyphicon-circle-arrow-down">下载</i>',
                         '</a>',
                          '&nbsp;<a class="remove_coupon_click"  data-id="'
                         + row.instId
                         + '" href="javascript:void(0)" title="Remove">',
                         '<i class="glyphicon glyphicon-remove-sign" style="min-width: 45px;">删除</i>',
                         '</a>'
                    ].join('');

            }
        }],queryParams: function getParams(params){
            var  tmp = {
                offset:(this.pageNumber)*this.pageSize,
                limit:this.pageSize,
                condition:$('#searchParam option:selected').val(),
                value:$("#searchValue").val()
                /*sort:this.sortName,
                 order:this.sortOrder*/
            };
            searchParams =tmp;
            return tmp;
        }
    }

    showInsitutionInfo();
    function showInsitutionInfo() {
        //getUrl();
        $("#table").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
        $table = $('#table').bootstrapTable(json);
        //$("#table").bootstrapTable('hideColumn','operate');
    }

    /**
     * 搜索
     */

    $("#searchInst").click(function(){
        var searchParam = $('#searchParam option:selected').val();
        var searchValueType = $('#searchValue').attr("type");
        var createTimeType =$("#date").attr("type");

        var searchValue = $("#searchValue").val();
        var createTime = $("#date").val();

        if(searchParam==''){
            layer.msg('请选择搜索条件');
            $('#searchParamDiv').css('border','1px solid red');
            return false;
        }else{
            $('#searchParamDiv').css('border','1px solid lightgrey');
        }
        if(searchValueType == 'text'){
            if(searchValue==''){
                layer.msg('请输入制度名称');
                $('#searchValue').css('border','1px solid red');
                return false;
            }else{
                $('#searchValue').css('border','1px solid lightgrey');
            }
            var value= url+"?offset="+searchParams.offset+"&limit="+searchParams.limit+"&condition="+searchParam+"&value="+searchValue;
        }
        if(createTimeType == 'text'){
            if(createTime==''){
                layer.msg('请选择上传时间');
                $('#date').css('border','1px solid red');
                return false;
            }else{
                $('#date').css('border','1px solid lightgrey');
            }
            var value= url+"?offset="+searchParams.offset+"&limit="+searchParams.limit+"&condition="+searchParam+"&value="+createTime;
        }

        $.get(value,function(result){
            $("#table").bootstrapTable("load",result);
            var tmp = {
                offset:searchParams.offset,
                limit:searchParams.limit,
                condition:searchParam,
                value:searchValue //,

            };
            searchParams = tmp;
        });

    });


    /**
     * 制度上传
     */
    $("#import").click(function(){
        layer.open({
            type:2,
            title:'上传制度',
            shadeClose:true,
            shade:0,
            maxmin:true,
            area:["680px","468px"],
            content:['/institutionInfo/importPage','on'],
            end:function(layer,index){
                $("#table").bootstrapTable("refresh"); //刷新
            }

        })
    });

});
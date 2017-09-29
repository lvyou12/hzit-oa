$(function(){

    var json ={
        url: "/institutionInfo/listData",
        toolbar: '#toolBar',                //工具按钮用哪个容器
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
            checkbox: true
        }, {
            field: 'name',
            title: '制度名',
            width: 80
        }]
    }

    showInsitutionInfo();
    function showInsitutionInfo() {
        //getUrl();
        $("#table").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
        $table = $('#table').bootstrapTable(json);
    }

    /**
     * 制度上传
     */
    $("#import").click(function(){
        layer.msg("hello");
        layer.open({
            type:2,
            title:'上传制度',
            shadeClose:true,
            shade:0,
            maxmin:true,
            area:["680px","468px"],
            content:['/institutionInfo/importPage','on'],
            end:function(layer,index){
                //$("#table").bootstrapTable("refresh"); //刷新
            }

        })
    });

});
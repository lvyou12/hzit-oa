$(function(){
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
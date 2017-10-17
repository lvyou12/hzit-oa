/**
 * Created by lvyou on 2017/10/17.
 */
$(function(){
    var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    $("#cancel").click(function(){
        parent.layer.close(index);
    });

    var DataSourceTree = function(options) {
        this._data 	= options.data;
        this._delay = options.delay;
    }

    DataSourceTree.prototype.data = function(options, callback) {
        var self = this;
        var $data = null;

        if(!("name" in options) && !("type" in options)){
            $data = this._data;//the root tree
            callback({ data: $data });
            return;
        }
        else if("type" in options && options.type == "folder") {
            if("additionalParameters" in options && "children" in options.additionalParameters)
                $data = options.additionalParameters.children;
            else $data = {}//no data
        }

        if($data != null)//this setTimeout is only for mimicking some random delay
            setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

        //we have used static data here
        //but you can retrieve your data dynamically from a server using ajax call
        //checkout examples/treeview.html and examples/treeview.js for more info
    };

    function getTreeData(){
        var resultData = {};
        $.ajax({
            url: "/auth/getAuth?roleId="+ $("#roleId").val(),
            type: 'GET' ,
            async : false,
            dataType: 'json' ,
            success : function (data) {
                resultData = data;
            },
            error: function (response) {
                //console.log(response);
            }
        });
        return resultData;
    }

//    $.getJSON("/auth/getAuth",function(resp){
//        tree_data = resp;
//        console.log(resp);
//    })

    var treeDataSource = new DataSourceTree({data: getTreeData()});

    $('#tree1').ace_tree({
        dataSource: treeDataSource ,
        multiSelect:true,
        loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
        'open-icon' : 'icon-minus',
        'close-icon' : 'icon-plus',
        'selectable' : true,
        'selected-icon' : 'icon-ok',
        'unselected-icon' : 'icon-remove'
    });

    function xuanzhong() {
        var output = "";
        var ids = "";
        var items = $('#tree1').tree('selectedItems');
        for (var i in items) {
            if (items.hasOwnProperty(i)) {
                var item = items[i];
                ids += item.additionalParameters['id'] + ",";
            }
        }

        ids = ids.substring(0, ids.lastIndexOf(","));
        return ids;
    }

    $("#grant").click(function(){
        $.post("/role/grantRole",{ids:xuanzhong(),roleId:$("#roleId").val()},function(resp){
            if(resp.code === 300){
                layer.msg(resp.msg,{icon:2,time:800});
            }
            if(resp.code === 200){
                layer.msg(resp.msg,{icon:1,time:800});
                setTimeout(function(){parent.layer.close(index)},1200);
            }
        },"JSON")
    });
});
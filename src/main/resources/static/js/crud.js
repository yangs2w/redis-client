$(document).ready(function () {
    // 给inner-left设置height
    var height = window.screen.availHeight - 160;
    $('.inner-left').css("height", height);
    $('.keys').css("height", height - 202);

    // 导入layui模块
    layui.use(['layer', 'form', 'layedit', 'element'], function () {
        var layer = layui.layer,
            form = layui.form,
            element = layui.element,
            layedit = layui.layedit;
        // select改变事件
        form.on('select(dbSelect)', function (data) {
            var dbn = data.value;
            var db = dbn;
            // 开启ajax请求
            $.ajax({
                url: '/db/select',
                type: 'get',
                data: {
                    host: localStorage.getItem("host"),
                    dbNumber: dbn
                },
                success: function (result) {
                    if (result.status === 200) {
                        var keys = result.data[0];
                        // 改变值
                        $('.key-num').text("Keys: " + keys);
                        getTable(db);
                    }else if (result.status === 500) {
                        window.location.href = "/link?msg=" + result.msg;
                    }
                }
            });
        })
    });

    // 表格数据
    function getTable(db) {
        var db = db;
        if (typeof db === "undefined") {
            db = 0;
        }
        layui.use('table', function () {
            var table = layui.table;
            table.render({
                elem: '#allKey',
                url: '/keys?host=' + localStorage.getItem("host") + '&db=' + db,
                height: height - 200,
                cols: [[
                    {type: 'checkbox'},
                    {field: 'type', width: '20%', title: '类型', sort: true},
                    {field: 'key', width: '68%', title: 'KEY', sort: true}
                ]],
                page: true
            });
        });
    }

    getTable();
    // 获取dnNum的值
    var dbNum = $('#dbNum').val();
    for (var i = 0; i < dbNum; i++) {
        $('.dbOptions').append('<option value="' + i + '">DB' + i + '</option>');
    }
    // Select改变

});
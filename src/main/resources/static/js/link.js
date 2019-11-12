$(document).ready(function () {
    // 引入需要的layui模块
    layui.use(['layer', 'form', 'element'], function () {
       var layer = layui.layer,
           fom = layui.form,
           element = layui.element;
    });

    // 获取页面高度
    var height = window.screen.height/4 - 60;
    // 设置content垂直居中
    $('.layui-layer-content').css("margin-top", height);
    // 关闭提示
    $('.error .close').click(function () {
        $('.error').css("display", "none")
    });

    // 提交数据
    $('#start').click(submitData);
    function submitData() {
        // 使提交按钮变为不可用
        $('#start').addClass("layui-btn-disabled");
        var pass = $('#password').val();
        // 开始对密码加密
        var encrypt = new JSEncrypt();
        // 从localStorage中获取key
        var publicKey = localStorage.getItem("key");
        encrypt.setPublicKey(publicKey);
        var enPass = encrypt.encrypt(pass);
        console.log(enPass);
        $.ajax({
            url: '/redis/conn',
            type: 'post',
            data: {
                host: $('#host').val(),
                port: $('#port').val(),
                password: enPass,
                timeout: $('#timeout').val()
            },
            success: function (result) {
                if (result.status === 200) {
                    var data = result.data;
                    $('#start').empty("").append("连接成功，前往操作页面....");
                    localStorage.setItem("host", data);
                    window.location.href = "/crud?host=" + data;
                }
                if (result.status === 400) {
                    // 提示用户
                    $('.message').empty("").append(result.message);
                    // 使可见
                    $('.error').css("display", "block");
                    // 撤销提交按钮不可用
                    $('#start').removeClass("layui-btn-disabled");
                    $('input').val("");
                }
            }
        });
    }
    // 获取消息
    // var mag = $('#msg').val();
    // if (typeof msg === "undefined" || null == msg || msg.length === 0) {
    // }else {
    //     layer.msg(mag);
    // }
    var queryVariable = getQueryVariable("msg");
    var msg = decodeURI(queryVariable);
    if (msg === false)
        return;
    if (typeof msg != "undefined" || null != msg || msg.length > 0) {
        layui.use('layer', function () {
            layer = layui.layer;
            layer.msg(msg);
        })
    }
});
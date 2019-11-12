$(document).ready(function () {
    // 从页面获取key
    var publicKey = $('#pk').val();
    // 判断key是否存在
    console.log(publicKey.length);
    if (typeof publicKey == "undefined" || null == publicKey || publicKey.length === 0) {
        // 通知用户，密码项暂时不可用
        // 获取密码的输入框
        var pasIIn = $('#password');
        // 修改type为text，提高用户可见性
        pasIIn.attr("type", "text");
        pasIIn.val("抱歉，密码项正在维护");
        pasIIn.addClass("layui-disabled")
        // 提示用户
        // pasIn.value = "抱歉，密码项正在维护";
    }
    // 将key存入到localStorage
    localStorage.setItem("key", publicKey);
});
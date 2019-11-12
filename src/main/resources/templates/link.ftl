<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Redis连接登录页面</title>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/link.css">
    <script type="application/javascript" src="../js/jquery.min.js"></script>
    <script type="application/javascript" src="../js/utils.js"></script>
    <script type="application/javascript" src="../layui/layui.js"></script>
    <script type="application/javascript" src="../js/jsencrypt.min.js"></script>
    <script type="application/javascript" src="../js/link.js"></script>
    <script type="application/javascript" src="../js/key.js"></script>
</head>
<body>
<header class="layui-header header">
    <div class="layui-main">
        <div class="logo">
            <a href="#">
                <img src="../image/9a056f85f3bd9b731734bbbb8309bb05.jpg">
            </a>
        </div>
        <ul class="layui-nav">
            <li class="layui-nav-item"><a href="#">首页</a></li>
            <li class="layui-nav-item layui-this"><a href="#">单机</a></li>
            <li class="layui-nav-item"><a href="#">集群</a></li>
            <li class="layui-nav-item"><a href="#">登录</a></li>
            <li class="layui-nav-item"><a href="#">注册</a></li>
        </ul>
    </div>
</header>
<div class="layui-main container">
    <div class="layui-layer-content">
        <div class="content">
            <div>
                <div class="welcome layui-unselect"><h1>Redis Web Client</h1></div>
                <div class="start layui-unselect">START LINK REDIS SERVER</div>
                <!-- 隐藏div，用于获取publicKey -->
                <input id="pk" type="hidden" value="${publicKey}">
            </div>
            <div class="layui-form">
                <div class="layui-form-item">
                    <input id="host" class="layui-input" type="text" name="host" placeholder="地址" required lay-verify="required">
                </div>
                <div class="layui-form-item">
                    <input id="port" class="layui-input" type="text" name="port" placeholder="端口，默认6379" required lay-verify="required">
                </div>
                <div class="layui-form-item">
                    <input id="password" class="layui-input" type="password" name="password" placeholder="密码，没有请忽略" required lay-verify="required">
                </div>
                <div class="layui-form-item">
                    <input id="timeout" class="layui-input" type="text" name="timeout" placeholder="超时时间，默认2000ms" required lay-verify="required">
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">保存信息</label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="switch" lay-skin="switch">
                    </div>
                </div>
                <div class="layui-form-item">
                    <button id="start" type="submit" name="提交" class="submit layui-btn layui-btn-fluid layui-btn-lg">START LINK</button>
                </div>
                <div class="layui-form-item error layui-unselect">
                    <div class="error-messgae layui-anim" data-anim="layui-anim-up"><span class="message"></span><i class="layui-icon layui-icon-close close"></i></div>
                </div>
            </div>
        </div>
    </div>
</div>
<#if msg??>
    <input id="msg" type="hidden" value="${msg}">
</#if>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Redis操作界面</title>
    <link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/crud.css">
    <script type="application/javascript" src="../js/jquery.min.js"></script>
    <script type="application/javascript" src="../layui/layui.js"></script>
    <script type="application/javascript" src="../js/crud.js"></script>
    <style>

    </style>
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
            <li class="layui-nav-item layui-this"><a href="#">首页</a></li>
            <li class="layui-nav-item"><a href="#">单机</a></li>
            <li class="layui-nav-item"><a href="#">集群</a></li>
            <li class="layui-nav-item"><a href="#">登录</a></li>
            <li class="layui-nav-item"><a href="#">注册</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">127.0.0.1</a>
                <dl class="layui-nav-child">
                    <dd><a href="/link">新连接</a></dd>
                    <dd><a href="#">意见反馈</a></dd>
                    <dd><a href="javascript:;">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</header>
<div class="nav-left">
    <div class="inner-left">
        <div class="dbs">
            <div class="layui-card">
                <div class="layui-card-header"><span class="list">对象列表</span>
                    <#if keySize??>
                        <span class="key-num">Keys: ${keySize}</span>
                    </#if>
                </div>
                <div class="layui-card-body">
                    <div class="layui-form dbnum">
                        <#if dbNum??>
                            <input type="hidden" id="dbNum" value="${dbNum}">
                        </#if>
                        <select class="dbOptions" name="dbnum" lay-search="" lay-filter="dbSelect""></select>
                    </div>
                </div>
            </div>
        </div>
        <div class="key-search">
            <div class="layui-card">
                <div class="layui-card-header">搜索</div>
                <div class="layui-card-body layui-form">
                    <input type="text" name="title" required placeholder="输入关键字，回车搜索" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="keys">
            <table class="layui-hide" id="allKey"></table>
        </div>
    </div>
</div>
<div class="nav-right">

</div>
</body>
</html>
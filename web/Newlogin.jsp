<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html><html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"><!--移动端和pc端各版本浏览器兼容-->
    <title>登录工大新闻网</title>
</head>

<script>
    function checknameframe() {
        var Regx = /^[A-Za-z0-9]*$/;
        var name = document.getElementById("name").value;
        if ((Regx.test(name)&&name.length>5&&name.length<11)||name =="") {
            return true;
        }
        else {
            document.getElementById("msg").innerHTML = "用户名格式不正确";
            return false;
        }
    }

    function checkpwdframe() {
        var Regx = /^[A-Za-z0-9]*$/;
        var pwd = document.getElementById("pwd").value;
        if ((Regx.test(pwd)&&pwd.length>5&&pwd.length<11)||pwd =="") {
            return true;
        }
        else {
            document.getElementById("msg").innerHTML = "密码格式不正确";
            return false;
        }
    }

    function checkform() {
        var namemsg = "请输入用户名";
        var pwdmsg = " 请输入密码";
        var name = document.getElementById("name").value;
        var pwd = document.getElementById("pwd").value;
        if (!checkpwdframe()||!checknameframe()) {return false;}
        if (name == ""){
            document.getElementById("msg").innerHTML= namemsg;
            return false;
        }else if (pwd == ""){
            document.getElementById("msg").innerHTML = pwdmsg;
            return false;
        }else{return  true;}
    }

    function checkNull() {
        document.getElementById("msg").innerHTML = " ";
    }

</script>

<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="boot_reg_a65c431.css">    <!--创建自己的css样式以便覆盖引入的库文件样式-->

<body>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <!--a class="navbar-brand" href="https://www.bilibili.com/video/av21061574?from=search&seid=17225233772752021614"></a-->                <!--图片文件一定要是跟html是同一级的目录才可直接引用，向上一级，在地址栏前加../-->
        </div>
        <label id="toggle-label" class="visible-xs-inline-block" for="toggle-checkbox">导航栏</label>            <!--映射下面的，将其隐藏，美观-->
        <input class="hidden" id="toggle-checkbox" type="checkbox">            <!--只用css完成JS效果，提高运行效率，单一小功能，后面有配合的样式-->
        <div class="hidden-xs">                <!--在超小屏幕下隐藏-->
            <ul class="nav navbar-nav">
                <li class="active"><a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'1'">首页</a></li>
            </ul><!--注意一定要在navbar-header的结构之外，才能完成指定的样式-->
        </div>
    </div>
</div>
<div class="container container-small">
    <h1>登录
        <small>没有账户？<a href="Newregister.jsp">注册</a></small>
    </h1>
    <form method="post" action="LoginServlet">
        <div class="form-group">
            <label>用户名/邮箱</label>
            <input type="text" class="form-control" id = "name" name="name" onchange ="return checkNull();" onblur="return checknameframe();">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" class="form-control" id = "pwd" name="pwd" onchange="return checkNull();" onblur="return checkpwdframe();">
            <span id = "msg" style="color: red"  onsubmit="return checkform();" >${msg1}</span>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" onclick="return checkform();">登录</button>
        </div>
        <div class="form-group">
            <a href="#">忘记密码</a>
        </div>
    </form>
</div>
<div class="footer">
    @2020 308宿舍专用   </div></body>

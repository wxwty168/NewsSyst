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
            document.getElementById("namemsg").innerHTML = "用户名格式不正确";
            return false;
        }
    }

    function checkpwdframe() {
        var Regx = /^[A-Za-z0-9]*$/;
        var pwd = document.getElementById("pwd").value;
        if ((Regx.test(pwd)&&pwd.length>5&&pwd.length<11)||pwd ==""||pwd =="") {
            return true;
        }
        else {
            document.getElementById("pwdmsg").innerHTML = "密码格式不正确";
            return false;
        }
    }
    function chkpwd() {
        var pwd = document.getElementById("pwd").value;
        var chkpwd = document.getElementById("chkpwd").value;
        if(pwd!= chkpwd){
            document.getElementById("chkpwdmsg").innerHTML="两次密码必须一致！" ;
            return false;
        }else{
            return true;
        }
    }

    function checkform() {
        var namemsg = "请输入用户名";
        var pwdmsg = " 请输入密码";
        var name = document.getElementById("name").value;
        var pwd = document.getElementById("pwd").value;
        var pwdchk =document.getElementById("pwdchk").value;
        if (!checkpwdframe()||!checknameframe()||!chkpwd()) {
            if (pwd == ""){
                document.getElementById("chkpwdmsg").innerHTML=" ";
            }
            alert("信息未填完！");
            return false;}
        if (name == ""){
            document.getElementById("namemsg").innerHTML= namemsg;
            alert("信息未填完！");
            return false;
        }else if (pwd == ""){
            alert("信息未填完！");
            document.getElementById("pwdmsg").innerHTML = pwdmsg;
            return false;
        }else{return  true;}
    }
    function SendCode() {
        var email = document.getElementById("email").value;
        window.location.href = "SendEmailServlet?email="+email;
    }
    function checkNullname() {
        document.getElementById("namemsg").innerHTML = " ";
    }
    function checkNullpwd() {
        document.getElementById("pwdmsg").innerHTML = " ";
    }
    function checkNullchkpwd() {
        document.getElementById("chkpwdmsg").innerHTML = " ";
    }
    function createXml() {
        //创建ajax依赖的对象
        try{
            return new XMLHttpRequest();
        }catch (e) {
            try{
                return new ActiveXObject("Microsoft.XMLHttp");
            }catch (e) {
                alert("浏览器版本过低!");
            }
        }
    }
    function tips() {
        var msg = "输入用户名";
        document.getElementById("TANGRAM__PSP_4__userNameTip").style ="display: block;";
    }
    function chkname() {
        var uname = document.getElementById("username").value;
        //ajax访问后台可以局部刷新页面 其它位置不发生变化
        var xmlh = createXml();
        xmlh.open("post","ExistServlet?uname"+uname,true);//调用后台,是否异步,true 的话后台与前台同时执行
        xmlh.send(null);
        var isTrue = true;
        xmlh.onreadystatechange= function () {
            if (xmlh.readyState == 4 && xmlh.status == 200){ //第一个码是执行过程 第二个码是执行结果
                var msg = xmlh.responseText;
                document.getElementById("namemsg").innerHTML = msg;
                if (msg = "用户名已被占用"){
                    isTrue=false;
                }
            }
        }  //检测后台状态码，发生变化就进入该function,此处后台前台不同时执行，即return 可能先完成
        return isTrue;
    }
</script>

<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="boot_reg_a65c431.css">    <!--创建自己的css样式以便覆盖引入的库文件样式-->

<body>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <!--a class="navbar-brand" href="https://www.bilibili.com/video/av21061574?from=search&seid=17225233772752021614"></a-->
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
    <h1>欢迎注册
        <small>已有账户？<a href="Newlogin.jsp">登录</a></small>
    </h1>
    <form method="post" action="SignUpServlet">
        <div class="form-group">
            <label>我是：</label><select name="isadmin" id="isadmin">
            <option value="0" selected="selected">普通用户</option>
            <option value="1">管理员</option>
        </select>
        </div>
        <div class="form-group">
            <label>用户名</label>
            <input    type="text" class="form-control" name = "name" id = "name" placeholder="设置后不可更改，仅限6-10位英文或数字"  onchange ="return checkNullname();" onblur="return checknameframe();">
            <span id = "namemsg" style="color: red"  onsubmit="return checkform();" ></span>
        </div>
        <div class="form-group">
            <label>密码</label>
            <input   type="password" class="form-control" name = "pwd" id="pwd" placeholder="仅限6-10位英文或数字"  onchange="return checkNullpwd();" onblur="return checkpwdframe();">
            <span id = "pwdmsg" style="color: red"  onsubmit="return checkform();" ></span>
        </div>
        <div class="form-group">
            <label>确认密码</label>
            <input  type="password" class="form-control" name = "chkpwd" id="chkpwd" placeholder="仅限6-10位英文或数字"  onblur="return chkpwd();" onchange="return checkNullchkpwd();">
            <span id = "chkpwdmsg" style="color: red"  onsubmit="return checkform();"></span>
        </div>
        <div class="form-group">
            <label>注册邮箱</label>
            <input   type="text" class="form-control" id = "email" name="email" placeholder="用于找回密码" onchange="return checkNull();"  onblur="return checkpwdframe();">
            <span id = "emailmsg" style="color: red"  ></span>
        </div>

        <div class="form-group">
            <div class="input-group">
                <input  type="text" class="form-control" name="verificationcode" placeholder="请输入验证码">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-warning" onclick="return SendCode();">获取验证码</button><!--验证码功能还没做-->
                </div>
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" onclick="return checkform();">注册</button>
        </div>
        <div class="form-group">
            <a href="#">忘记密码</a>
        </div>
    </form>
</div>
<div class="footer">
    @2020 308宿舍专用   </div></body>

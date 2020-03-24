<%--
  Created by IntelliJ IDEA.
  User: 红茶坊海风
  Date: 2020/1/3
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<div align="center">
    <h2>学生管理系统</h2>
    <form action="login" method="post"> <%--目的地，接受用户请求信息 --%>
        <h4>用户登录</h4>
        <hr width="600px">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username"> </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="pwd" > </td>
            </tr>
            <tr>
                <td colspan="2" align="center"> <input type="submit" value="登陆"></td> &nbsp;<!-- submit 提交表单按钮，即调用action nbsp为页面的空格-->
            </tr>
        </table>
    </form><br>
    <a href="/register.jsp"><button>注册</button></a>
    <div align="center" style="color:lightseagreen;size: revert">${msg}</div><br>
    <a href="Newlogin.jsp">新页面</a>
    <a href="MainPage.jsp">houye</a>
    <a href="Newregister.jsp">新注册</a>
    <a href="NewsPage.jsp">新闻页面</a>
    <a href="http://www.baidu.com">查找资料</a><br>
    <a href="NewPublic.jsp">新闻发布</a>
    <a href="Newseditor.jsp">新闻编辑</a>
    <a href="Newverify.jsp">新闻审核</a>
    <a href="FenYeTest.jsp">分页功能实现</a>
    <form action="NewsSpiderServlet" method="post"><input type="submit" > 查看新闻</form><br>
    <div align="center">${news}</div>
    <form action="time" method="get"> <input type="submit" value="查看时间"> </form>
</div>
<div id="times">    </div>
<script type="text/javascript"> function  getD1(){
    var date=new Date();
    var d1=date.toLocaleString();
    var div1=document.getElementById("times");
    div1.innerHTML =d1;      }
    setInterval("getD1();",1000);
    </script>
<div><form action="",method="post"><input type="file",name="file1"></form>
</div>
</body>
</html>


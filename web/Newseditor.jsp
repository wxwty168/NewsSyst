<%--
  Created by IntelliJ IDEA.
  User: 红茶坊海风
  Date: 2020/1/10
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">    <!--移动端和pc端各版本浏览器兼容-->
    <title>发布新闻</title>
</head>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
    function select() {
        var select_value = "${news.newstype}";
        var list = document.getElementsByName("selectbox");
        for(var i=0;i<list;i++){
            if(list[i].value == select_value){list[i].selected=true;}
        }
    }
    function checkid() {
        var id = ${id};
        if (id == "1"){
            document.getElementById("verify").style="display.block";
        }
        if(id != null){
            document.getElementById("login").style="display:none";
            document.getElementById("register").style="display:none";
            document.getElementById("welcome").style="display:block";
            document.getElementById("loginout").style="display:block";

        }
    }
</script>
<script type="text/javascript">
    function  getD1(){
        var date=new Date();
        var d1=date.toLocaleString();
        var div1=document.getElementById("times");
        div1.innerHTML =d1;      }
    setInterval("getD1();",1000);

    function checknewsform() {
        var checktitle = document.getElementById("newstitle").value;
        var checkcontent = document.getElementById("newscontent").value;
        if(checktitle="" || checkcontent==""){alert("您还有未填完的信息");return false;}else {if (confirm("确定发布这条新闻？")==true){ return true;}else {return false;}}
    }
    function loadeditornews() {
        var newstype = ${news.newstype};
        document.getElementById(newstype).selected = "selected";

    }

</script>
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="boot_reg_a65c431.css">
<link rel="stylesheet" type="text/css" href="bootstrap-select.css">
<body onload="checkid()">
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <!--a class="navbar-brand" href="https://www.bilibili.com/video/av21061574?from=search&seid=17225233772752021614"></a--><!--图片文件一定要是跟html是同一级的目录才可直接引用，向上一级，在地址栏前加../-->
        </div><label id="toggle-label" class="visible-xs-inline-block" for="toggle-checkbox">导航栏</label>            <!--映射下面的，将其隐藏，美观-->
        <input class="hidden" id="toggle-checkbox" type="checkbox">            <!--只用css完成JS效果，提高运行效率，单一小功能，后面有配合的样式-->
        <div class="hidden-xs">                <!--在超小屏幕下隐藏-->
            <ul class="nav navbar-nav">
                <li  name="top" id="top1"  ><a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'1'">首页</a></li>
                <li name="top" id="top2"><a href="javascript:location.href='GetAddedNewsToVerifyServlet?verifytype='+'1'" id="verify" style="display: none" >审核</a></li>
                <li name="top" id="top4" class="active"><a href="javascript:location.href='ResetRealPageServlet?pageName='+'1'" style="display: block" >发布</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Newlogin.jsp" style="display: block" id="login">登录</a></li>
                <li><a href="Newregister.jsp" style="display: block" id="register">注册</a></li>
                <li><a href="" style="display: none" id="welcome">欢迎${name}</a></li>
                <li><a href="javascript:location.href='LogoutServlet'" style="display: none" id="loginout">注销</a></li>
            </ul><!--注意一定要在navbar-header的结构之外，才能完成指定的样式-->
        </div>
    </div>
</div>
<div class="container">
    <form method="post" action="SubmitUpdateNewsServlet">
        <div class="col-md-8">
            <h1 class="news-title" ><input type="text" style="width: 600px" placeholder="请输入您的新闻标题" name="newstitle" id="newstitle" value="${news.title}"></h1>
            <div class="new-info">发布时间：${news.createdate}&nbsp&nbsp&nbsp 当前时间： <span id="times"></span>&nbsp&nbsp&nbsp&nbsp新闻类别：
                <select name="newstype" id="newstype" onload="return select()">
                    <option name = "selectbox" value="1">科研工作</option>
                    <option name = "selectbox" value="2">学生工作</option>
                    <option name = "selectbox" value="3">党建工作</option>
                    <option name = "selectbox" value="4">对外交流</option>
                    <option name = "selectbox" value="5">校园生活</option>
                </select>
            </div>
            <div class="news-content">
                <textarea class="form-control" rows="30" cols="10" placeholder="请输入您的新闻内容" name="newscontent" id ="newscontent">${news.content}</textarea>
            </div><br>
            <table class="table" align="center">
                <tr>
                    <td align="center">
                        <button type="submit" class="btn btn-warning" onclick="return checknewsform();">编辑</button>
                    </td>
                    <td align="center">
                        <button type="reset" class="btn btn-danger">重置</button>
                    </td>
                </tr>

            </table>
        </div>
    </form>


    <div class="col-md-4">
        <div class="side-bar-card">
            <div class="card-title">热门新闻</div>
            <div class="card-body">
                <div class="list">
                    <c:forEach items="${topthree}" var="news" >
                        <div class="item clearfix">
                            <div class="col-xs-5 clear-image-padding">
                                <img src="${news.imamgeurl}">
                            </div>
                            <div class="col-xs-7">
                                <div class="title">${news.title}</div>
                                <div class="dest">${news.clicks}次阅读</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="footer">
    @2020 308专用
</div>
</body>
</html>


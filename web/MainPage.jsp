<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 红茶坊海风
  Date: 2020/1/8
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>    <meta charset="UTF-8">    <meta name="viewport" content="width=device-width,initial-scale=1.0">    <meta http-equiv="X-UA-Compatible" content="ie=edge">    <!--移动端和pc端各版本浏览器兼容-->
    <title>哈工大新闻</title>
</head>
<script>
    function getChangeType(Lnewstype){
        var newstype = Lnewstype;
        window.location.href = "ChangeTypeValueServlet?Lnewstype=" + Lnewstype; //String snos = request.getParameter("snos"); Servlet使用方式
    }
    function getChangeNews(News_id) {
        var Newsid = News_id;
        window.location.href="GetNewsToNewsPageServlet?newsid="+Newsid;
    }
   function loading() {
        checkid();
        var topid = ${topid};
        var Lnewstype ="1";
        var flag =${flag};
        if(flag=="1") {window.location.href="GetANewsTypeServlet?Lnewstype="+Lnewstype;}
    }
    function checkid() {
        var id = ${topid};
        if (id == "1"){
            document.getElementById("verify").style="display:block";
        }
        if(id != null){
            document.getElementById("login").style="display:none";
            document.getElementById("register").style="display:none";
            document.getElementById("welcome").style="display:block";
        }
    }
    /*function activeClass(type) {
        var newstype = type.id;
        var news = document.getElementsByName("Lnewstype");
        for (var i=0; i<news.length; i++){
            if (news[i].id == newstype){
                news[i].class="list-group-item active";
            }else {news[i].class="list-group-item ";}
        }
    }*/

</script>
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="boot_reg_a65c431.css">    <!--创建自己的css样式以便覆盖引入的库文件样式-->
<link rel="stylesheet" type="text/css" href="reg_new_7ebbf19.css">
<body onload="loading()">
<div class="navbar navbar-default"> <!--导航栏部分-->
    <div class="container">
        <div class="navbar-header">
            <!--a class="navbar-brand" href="https://www.bilibili.com/video/av21061574?from=search&seid=17225233772752021614"></a--> <!--图片文件一定要是跟html是同一级的目录才可直接引用，向上一级，在地址栏前加../-->
        </div>
        <label id="toggle-label" class="visible-xs-inline-block" for="toggle-checkbox">导航栏</label>              <!--映射下面的，将其隐藏，美观-->
        <input class="hidden" id="toggle-checkbox" type="checkbox">              <!--只用css完成JS效果，提高运行效率，单一小功能，后面有配合的样式-->
        <div class="hidden-xs">                  <!--在超小屏幕下隐藏-->
            <ul class="nav navbar-nav">
                <li  name="top" id="top1" class="active" ><a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'1'">首页</a></li>
                <li name="top" id="top2"><a href="javascript:location.href='GetAddedNewsToVerifyServlet?verifytype='+'1'" id="verify" style="display: none" >审核</a></li>
                <li name="top" id="top4"><a href="javascript:location.href='ResetRealPageServlet?pageName='+'1'" style="display: block" >发布</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Newlogin.jsp" style="display: block" id="login">登录</a></li>
                <li><a href="Newregister.jsp" style="display: block" id="register">注册</a></li>
                <li><a href="" style="display: none" id="welcome">欢迎${name}</a></li>
            </ul>                  <!--注意一定要在navbar-header的结构之外，才能完成指定的样式-->
        </div>
    </div>
</div>
<div class="container">
    <div class="row">                            <!--用来抵消，下面栅格的内部，跟标题保持一样的样式-->
        <div class="col-sm-2">
            <div class="hidden-xs list-group side-bar">
                <a  href="javascript:location.href='NewSetPageServlet?Lnewstype='+'1'" class="list-group-item list-group-item-warning" >科研工作</a>
                <a  href="javascript:location.href='NewSetPageServlet?Lnewstype='+'2'"  class="list-group-item ${type2}" >学生工作</a>
                <a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'3'" class="list-group-item ${type3}" >党建工作</a>
                <a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'4'" class="list-group-item ${type4}" >对外交流</a>
                <a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'5'" class="list-group-item ${type5}" >校园生活</a>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="news-list">
                <c:forEach items="${list}" var="news">    <!--新闻组-->
                    <div class="news-list-item clearfix">
                        <div class="col-xs-5">
                            <img src="${news.imamgeurl}" alt="">   <!-- 先空着-->
                        </div>
                        <div class="col-xs-7">
                            <a href="javascript:location.href='GetNewsToNewsPageServlet?Newsid='+${news.id}" class="title" id="${news.id}" >${news.title}</a>
                            <div class="info">
                                <span>${news.author}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <!--用户还可以设置头像-->
                                <span>${news.createdate}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="search-bar">
                <form method="post" action="SearchNewsServlet">
                    <table>
                        <tr align="center">
                            <td>
                                <input name ="serchtitle" type="search" class="form-control" placeholder="关键字查询" style="width: 200px"> </td> <td> <button type="submit" class="btn btn-warning" style="width:60px;">搜索</button> <!--搜索栏-->
                        </td>
                        </tr>
                    </table>
                </form>
            </div>

        </div>
    </div>
</div>
<div class="footer">          @2020 308宿舍专用      </div> <!--脚-->
</body>
</html>

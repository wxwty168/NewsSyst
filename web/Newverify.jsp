<%--
  Created by IntelliJ IDEA.
  User: 红茶坊海风
  Date: 2020/1/10
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>    <meta charset="UTF-8">    <meta name="viewport" content="width=device-width,initial-scale=1.0">    <meta http-equiv="X-UA-Compatible" content="ie=edge">    <!--移动端和pc端各版本浏览器兼容-->
    <title>哈工大新闻</title>
    <style>
        .wrap{
            width: 150px;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        }
    </style>
</head>

<script>
    function checkid() {
        var id = "${id}";
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
    function getChangeNews(News_id) {
        var Newsid = News_id.id;
        window.location.href="GetNewsServlet?newsid="+Newsid;
    }
    function checkAll() {
        var node = document.getElementById("chkAll");
        var chks = document.getElementsByName("chk");
        if(node.checked){
            for(var i=0;i<chks.length;i++){
                chks[i].checked = true;
            }
        }else{
            for(var i=0;i<chks.length;i++){
                chks[i].checked = false;
            }

        }
    }
    function unchk(chk_s) {
        //获取全选框
        var chkall = document.getElementById("chkAll");
        if (chk_s.checked == false){
            //当前复选框没有选中，全选框也不被选中
            chkall.checked= false;
        }else{
            var chks = document.getElementsByName("chk");
            var isAll = true;//标记目前是否全选
            for(var i=0; i<chks.length;i++){
                if (chks[i].checked == false){
                    isAll = false;
                    break;
                }
            }
            chkall.checked = isAll;
        }
    }








    function PassNews() {
        var chks = document.getElementsByName("chk");
        var news_id = ""; //用来存储要删除学生的学号
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked == true) {
                news_id += chks[i].value;
                news_id += ",";
            }
        }
        if (news_id == "") {
            alert("请选择要通过的新闻!");
        } else {
            if (confirm("确认通过审核？")) {
                //调用后台执行删除操作 get method
                if("${serchtype1}"=="display:block"){window.location.href = "VerifyAddedNewsServlet?news_id=" + news_id;}
                if("${serchtype2}"=="display:block"){window.location.href = "VerifyUpdateNewsServlet?news_id=" + news_id;}
                if("${serchtype3}"=="display:block"){}
            }
        }
    }
    function DelNews() {
        var chks = document.getElementsByName("chk");
        var news_id = ""; //用来存储要删除学生的学号
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked == true) {
                news_id += chks[i].value;
                news_id += ",";
            }
        }
        if (news_id == "") {
            alert("请选择要删除的记录!");
        } else {
            if (confirm("确定删除？")) {
                //调用后台执行删除操作 get method
                if("${serchtype1}"=="display:block") {var type= "1";window.location.href = "DelNewsServlet?news_id=" + news_id+'&type='+type;}
                if("${serchtype2}"=="display:block") {var type= "2";window.location.href = "DelUpdateNewsServlet?news_id=" + news_id+'&type='+type;}
                if("${serchtype3}"=="display:block") {var type= "3";window.location.href = "DelNewsServlet?news_id=" + news_id+'&type='+type;}

            }
        }
    }
</script>
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="boot_reg_a65c431.css">    <!--创建自己的css样式以便覆盖引入的库文件样式-->
<link rel="stylesheet" type="text/css" href="reg_new_7ebbf19.css">
<body onload="checkid()">
<div class="navbar navbar-default"> <!--导航栏部分-->
    <div class="container">
        <div class="navbar-header">
            <!--a class="navbar-brand" href="https://www.bilibili.com/video/av21061574?from=search&seid=17225233772752021614"></a--> <!--图片文件一定要是跟html是同一级的目录才可直接引用，向上一级，在地址栏前加../-->
        </div>
        <label id="toggle-label" class="visible-xs-inline-block" for="toggle-checkbox">导航栏</label>              <!--映射下面的，将其隐藏，美观-->
        <input class="hidden" id="toggle-checkbox" type="checkbox">              <!--只用css完成JS效果，提高运行效率，单一小功能，后面有配合的样式-->
        <div class="hidden-xs">                  <!--在超小屏幕下隐藏-->
            <ul class="nav navbar-nav">
                <li  name="top" id="top1"  ><a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'1'">首页</a></li>
                <li name="top" id="top2" class="active"><a href="javascript:location.href='GetAddedNewsToVerifyServlet?verifytype='+'1'" id="verify" style="display: none" >审核</a></li>
                <li name="top" id="top4"><a href="javascript:location.href='ResetRealPageServlet?pageName='+'1'" style="display: block" >发布</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Newlogin.jsp" style="display: block" id="login">登录</a></li>
                <li><a href="Newregister.jsp" style="display: block" id="register">注册</a></li>
                <li><a href="" style="display: none" id="welcome">欢迎${name}</a></li>
                <li><a href="javascript:location.href='LogoutServlet'" style="display: none" id="loginout">注销</a></li>
            </ul>                  <!--注意一定要在navbar-header的结构之外，才能完成指定的样式-->
        </div>
    </div>
</div>



<div class="container">
    <div class="row">                            <!--用来抵消，下面栅格的内部，跟标题保持一样的样式-->
        <div class="col-sm-2">
            <div class="hidden-xs list-group side-bar">
                <a href="javascript:location.href='GetAddedNewsToVerifyServlet?verifytype='+'1'" class=" ${veritype1}">待审核新增新闻</a>
                <a href="javascript:location.href='GetUpdateNewsToVerifyServlet?verifytype='+'2'" class=" ${veritype2}">待审核更新新闻</a>
                <a href="javascript:location.href='GetAllVerifiedNewsTitleServlet?verifytype='+'3'" class=" ${veritype3}">已审核新闻</a>

            </div>
        </div>
        <div class="col-sm-7">
            <table class="table table-condensed"><tr align="right">
                <td><button class="btn btn-warning" style="height: 20px;" onclick="javascript:location.href='SetPagePreServlet'">《</button></td><td>第${page_current}页</td><td><button class="btn btn-warning" style="height: 20px" onclick="javascript:location.href='SetPageServlet'">》</button></td><td>共${page_total}页</td>
            </tr></table>
            <table class="table table-striped table-hover">
                <tr  class="warning" >
                    <td><input type="checkbox" onclick="checkAll()" id="chkAll"></td>
                    <td>新闻标题</td>
                    <td>新闻类型</td>
                    <td>发布者</td>
                    <td>发布日期</td>
                    <td></td>
                </tr>
                <c:forEach items="${listver}" var="news" begin="${page_begin}" end="${page_end}">
                    <tr>
                        <td><input class="checkbox" type="checkbox" value="${news.id}" name="chk" onclick="unchk(this)"></td>
                        <td><div class="wrap" title="${news.title}" style="display:inline-block"> ${news.title}</div></td>
                        <td>${news.newstype}</td>
                        <td><div class="wrap" title="${news.author}" style="display:inline-block"> ${news.author}</div></td>
                        <td>${news.createdate}</td>
                        <td><button class="btn btn-info"  onclick="javascript:location.href='GetNewsToEditPageServlet?Newsid='+${news.id}">查看</button></td>
                    </tr>
                </c:forEach>
            </table>
            </form>
            <table class="table" align="center">
                <tr align="center">
                    <td><button  type="button"  class="btn btn-warning" onclick="PassNews()" style="height: 30px;">通过</button></td><td></td><td><button class="btn btn-danger" type="button"  onclick="DelNews()" style="height: 30px;">删除</button></td>
                </tr>
            </table>
        </div>


        <div class="col-sm-3" >
            <div class="search-bar" style="${serchtype1}">
                <form method="post" action="SearchAddedNewsServlet">
                    <table>
                        <tr align="center">
                            <td>
                                <input name ="serchtitle" type="search" class="form-control" placeholder="新增关键字查询" style="width: 200px"> </td> <td> <button type="submit" class="btn btn-warning" style="width:60px;">搜索</button> <!--搜索栏-->
                        </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="search-bar" style="${serchtype2}">
                <form method="post" action="SearchUpdateNewsServlet">
                    <table>
                        <tr align="center">
                            <td>
                                <input name ="serchtitle" type="search" class="form-control" placeholder="更新关键字查询" style="width: 200px"> </td> <td> <button type="submit" class="btn btn-warning" style="width:60px;">搜索</button> <!--搜索栏-->
                        </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="search-bar" style="${serchtype3}">
                <form method="post" action="SearchNewsForVerifyServlet">
                    <table>
                        <tr align="center">
                            <td>
                                <input name ="serchtitle" type="search" class="form-control" placeholder="已审核关键字查询" style="width: 200px"> </td> <td> <button type="submit" class="btn btn-warning" style="width:60px;">搜索</button> <!--搜索栏-->
                        </td>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="side-bar-card police clearfix"><!--清除浮动-->
                <div class="col-xs-5">
                    <img src="/IMG.gif" alt="举报图片">
                </div>
                <div class="col-xs-7">
                    <div class="note">308宿舍专用</div>
                    <div>如有疑问</div>
                    <div>qq:1762277418</div>
                </div>
            </div>
            <div class="side-bar-card ">
                <div class="card-title">热门新闻</div>
                <div class="card-body">
                    <div class="list">
                    <c:forEach items="${topthree}" var="news" >
                        <div class="item" >
                            <a href="javascript:location.href='GetNewsToNewsPageServlet?Newsid='+${news.id}" class="title" id="${news.id}" >${news.title}</a>
                           <%-- <div class="title">${news.title}</div>--%>
                            <div class="desc">${news.clicks}次浏览</div>
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="footer">
    @2020 308宿舍专用
</div>
</body>
</html>

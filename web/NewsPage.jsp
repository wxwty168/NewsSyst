<%--
  Created by IntelliJ IDEA.
  User: 红茶坊海风
  Date: 2020/1/9
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">    <!--移动端和pc端各版本浏览器兼容-->
    <title>新闻页<%--${news.title}--%></title>
</head>
<script>

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
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="boot_reg_a65c431.css">
<body onload="checkid()">
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <!--a class="navbar-brand" href="https://www.bilibili.com/video/av21061574?from=search&seid=17225233772752021614"></a--><!--图片文件一定要是跟html是同一级的目录才可直接引用，向上一级，在地址栏前加../-->
        </div><label id="toggle-label" class="visible-xs-inline-block" for="toggle-checkbox">导航栏</label>            <!--映射下面的，将其隐藏，美观-->
        <input class="hidden" id="toggle-checkbox" type="checkbox">            <!--只用css完成JS效果，提高运行效率，单一小功能，后面有配合的样式-->
        <div class="hidden-xs">                <!--在超小屏幕下隐藏-->
            <ul class="nav navbar-nav">
                <li  name="top" id="top1" class="active" ><a href="javascript:location.href='NewSetPageServlet?Lnewstype='+'1'">首页</a></li>
                <li name="top" id="top2"><a href="javascript:location.href='GetAddedNewsToVerifyServlet'" id="verify" style="display: none" >审核</a></li>
                <li name="top" id="top4"><a href="javascript:location.href='ResetRealPageServlet?pageName='+'1'" style="display: block" >发布</a></li>
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
    <div class="col-md-8">
        <h1 class="news-title">${news.title}</h1><!--搜索引擎，h1爬取权重较高-->
        <div class="new-info">发布时间：${news.createdate} 最后修改：${news.modifydate}
            <div class="label label-default">${news.newstype}</div>
        </div>

        <%--<div class="news-content">
            <div class="wp_articlecontent"><p style="background:#ffffff;margin:0px 0px 10px;padding:0px;text-align:left;line-height:150%;text-indent:28px;-ms-text-autospace:ideograph-numeric;mso-char-indent-count:0.0000;mso-pagination:widow-orphan;"><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;"><span style="font-family:宋体;">在邓小平同志诞辰</span>114周年之际，第十一届中国青少年科技创新奖颁奖大会</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">于</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">2018年8月</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">24日在人民大会堂举行。中共中央政治局委员、国务院副总理孙春兰出席并讲话。</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">团中央书记处书记贺军科主持会议。我校区团委书记李新和</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">HRT车队学生代表王月娇赴京参加颁奖大会。</span></p><p style="background:#ffffff;margin:0px 0px 10px;padding:0px;text-align:center;line-height:150%;-ms-text-autospace:ideograph-numeric;mso-pagination:widow-orphan;"><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;"></span></p><p style="text-align:center;"><img style="float:none;" src="http://news.hitwh.edu.cn/_upload/article/images/89/89/adb9a950487487e02e029b618694/07e3167f-00c4-4a58-a91a-ef780f725b12.jpg" sudyfile-attr="{'title':'图1_副本.jpg'}" data-layer="photo"></p><p style="background:#ffffff;margin:0px 0px 10px;padding:0px;text-align:center;line-height:150%;-ms-text-autospace:ideograph-numeric;mso-pagination:widow-orphan;"><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;"></span><br></p><p style="background:#ffffff;margin:0px 0px 10px;padding:0px;text-align:center;line-height:150%;-ms-text-autospace:ideograph-numeric;mso-pagination:widow-orphan;"><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;"></span></p><p style="text-align:center;"><img style="float:none;" src="/_upload/article/images/89/89/adb9a950487487e02e029b618694/de6a558e-1756-4de6-88cf-9bab1f955cda.jpg" sudyfile-attr="{'title':'图2_副本.jpg'}" data-layer="photo"></p><p style="background:#ffffff;margin:0px 0px 10px;padding:0px;text-align:center;line-height:150%;-ms-text-autospace:ideograph-numeric;mso-pagination:widow-orphan;"><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;"></span><br></p><p style="background:#ffffff;margin:0px 0px 10px;padding:0px;text-align:left;line-height:150%;text-indent:28px;-ms-text-autospace:ideograph-numeric;mso-char-indent-count:0.0000;mso-pagination:widow-orphan;"><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">颁奖大会上，</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;"><span style="font-family:宋体;">孙春兰指出，科技是国之利器，世界上的现代化强国无一不是科技强国、创新强国。青少年是祖国的未来，科学的希望。习近平总书记对青少年投身科技创新寄予殷切期望，强调要</span>“让科技工作成为富有吸引力的工作、成为孩子们尊崇向往的职业，给孩子们的梦想插上科技的翅膀”。孙春兰希望广大青少年把个人理想融入到国家和民族事业中，继承前辈们科学报国的优良传统，坚持追求真理、勇攀高峰的科学精神，保持踏实奋斗的心态，掌握科学的研究方法，取得无愧于新时代的新发现、新发明、新技术，扛起振兴国家科技事业、建设世界科技强国的大旗。</span></p><p style="margin:10px 0px;line-height:150%;text-indent:28px;-ms-text-autospace:ideograph-numeric;mso-char-indent-count:0.0000;mso-pagination:widow-orphan;"><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;">中国</span><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;"><span style="font-family:宋体;">青少年科技创新奖是</span>2004年邓小平同志诞辰100周年之际，邓小平同志亲属按照他的遗愿，</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">捐献出他生前全部稿费，委托共青团中央、全国青联、全国学联、全国少工委共同设立的</span><span style="background:#ffffff;color:#000000;text-transform:none;letter-spacing:0px;font-family:宋体;font-size:16px;font-style:normal;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;mso-shading:#ffffff;">公益性基金，</span><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;">用于鼓励青少年科技创新</span><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;">。</span><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;">2014年，该基金首次设立大学生“小平科技创新团队”项目，在全国高校中遴选出在学术研究、科技竞赛、成果转化等方面取得突出成绩或显示较大潜力的大学生科技创新团队，支持其开展科技创新研究。</span></p><p style="margin:10px 0px;line-height:150%;text-indent:28px;-ms-text-autospace:ideograph-numeric;mso-char-indent-count:0.0000;mso-pagination:widow-orphan;"><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;"><span style="font-family:宋体;">威海校区</span>HRT车队</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;">于</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;">2017年获评</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;"><span style="font-family:宋体;">大学生</span>“小平科技创新团队”并于2018年7月顺利通过中国青少年科技创新奖励基金2017年支持项目考核验收。当年全国共有50个团队获此殊荣。</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;">HRT车队成立于2009年，</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;">目前</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;"><span style="font-family:宋体;">下设燃油方程式赛车队、电动方程式赛车队、巴哈越野赛车队和无人车队</span>4支队伍，共有成员200余人。HRT车队始终秉承</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;">哈工大</span><span style="font-family:宋体;font-size:16px;font-weight:normal;mso-spacerun:yes;mso-font-kerning:0px;">“规格严格、功夫到家”校训传统，逐渐形成了特有的团队精神，成功自主设计和制造出13辆大学生方程式赛车和3辆巴哈赛车。车队累计获</span><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;"><span style="font-family:宋体;">得赛车设计、高速避障、</span>ANSYS设计和轻量化设计第一名等国内外70个奖项。2016年，HRT电车队和HRT巴哈车队分别取得了全国总冠军的成绩，标志着HRT车队成为中国大学生方程式赛事史上首个“双冠王”。2017年，HRT巴哈赛车、电动方程式赛车和燃油方程式赛车代表中国参加美国大学生巴哈赛车比赛和日本大学生方程式赛车，取得赛车设计第三名、效率测试第一名等好成绩</span><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:1px;">。2018年，最新的2018中国汽车工程学会巴哈大赛襄阳站上，HRT巴哈车队再次取得本科组冠军的成绩。</span><br></p><p style="margin:10px 0px;text-align:center;line-height:150%;-ms-text-autospace:ideograph-numeric;mso-pagination:widow-orphan;"><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;"></span></p><p style="text-align:center;"><img style="float:none;" src="/_upload/article/images/89/89/adb9a950487487e02e029b618694/77175109-57cc-4e86-adec-adb9ee8498e2.jpg" sudyfile-attr="{'title':'图3.jpg'}" data-layer="photo"></p><p style="margin:10px 0px;text-align:center;line-height:150%;-ms-text-autospace:ideograph-numeric;mso-pagination:widow-orphan;"><span style="font-family:宋体;font-size:16px;mso-spacerun:yes;mso-font-kerning:0px;"></span><br></p><p><br></p></div>
        </div>--%>
        ${news.content}

    </div>
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
</div>
<div class="footer">
    @2020 308宿舍专用
</div>
</body>
</html>
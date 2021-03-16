<%@ page import="com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ" %><%--
  Created by IntelliJ IDEA.
  User: 11307
  Date: 2020/4/17
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path =request.getContextPath()+"/index.action";
    String articlePath = request.getContextPath()+"/article/detail/";
    pageContext.setAttribute("articlePath",articlePath);
    pageContext.setAttribute("path",path);

    //List数据 服务端
   /* Object articles = request.getAttribute("articles");*/
    //利用EL表达式可以直接获取 作用域内的数据
       //例如  ${totalPage}  获取作用域中key为totalPage的数据
    //当前第几页
/*    int currPage = (int) request.getAttribute("currPage");
    int totalPage = (int) request.getAttribute("totalPage");
    int nextPage = currPage + 1;*/
/*  if (articles==null){
  // request.getRequestDispatcher(request.getContextPath()+path).forward(request,response);
  }*/

%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>舒新胜的博客</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nprogress.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/icon/icon.png">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon/head.ico">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/nprogress.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.lazyload.min.js"></script>
    <!--[if gte IE 9]>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/html5shiv.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/selectivizr-min.js" type="text/javascript"></script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script>window.location.href = 'upgrade-browser.jsp';</script>
    <![endif]-->

</head>

<body class="user-select">
<%@ include file="_header.jsp" %>

<section class="container">
    <div class="content-wrap">
        <div class="content">
            <div class="jumbotron">
                <h1>欢迎访问舒新胜的博客</h1>
                <p>在这里可以看到前端技术，后端程序，网站内容管理系统等文章，还有我的程序人生！</p>
            </div>
            <div id="focusslide" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#focusslide" data-slide-to="0" class="active"></li>
                    <li data-target="#focusslide" data-slide-to="1"></li>

                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active"><a href="" target="_top"><img src="${pageContext.request.contextPath}/images/banner/banner_01.jpg" alt=""
                                                                             class="img-responsive"></a>
                        <!--<div class="carousel-caption"> </div>-->
                    </div>
                    <div class="item"><a href="" target="_top"><img src="${pageContext.request.contextPath}/images/banner/banner_02.jpg" alt=""
                                                                      class="img-responsive"></a>
                        <!--<div class="carousel-caption"> </div>-->
                    </div>

                </div>
                <a class="left carousel-control" href="#focusslide" role="button" data-slide="prev" rel="nofollow">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
                        class="sr-only">上一个</span> </a> <a class="right carousel-control" href="#focusslide"
                                                           role="button" data-slide="next" rel="nofollow"> <span
                    class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span
                    class="sr-only">下一个</span> </a></div>
            <article class="excerpt-minic excerpt-minic-index">
                <h2><span class="red">【今日推荐】</span><a href="" title="">从下载看我们该如何做事</a></h2>
                <p class="note">一次我下载几部电影，发现如果同时下载多部要等上几个小时，然后我把最想看的做个先后排序，去设置同时只能下载一部，结果是不到一杯茶功夫我就能看到最想看的电影。
                    这就像我们一段时间内想干成很多事情，是同时干还是有选择有顺序的干，结果很不一样。同时...</p>
            </article>

            <div class="title">
                <h3>最新发布</h3>
                <div class="more">
                  <%--  <a href="">JAVA</a>
                    <a href="">MySQL</a>
                    <a href="">TomCat</a>--%>
                    <a href="${pageContext.request.contextPath}/index.action?categoryId=-1" data-id="-1">全部</a>
                    <c:forEach items="${categories}" var="cg">
                        <a href="${pageContext.request.contextPath}/index.action?categoryId=${cg.id}" data-id="${cg.id}">${cg.categoryName}</a>
                    </c:forEach>
                    <a style="display: none" data-currCategoryId="${currCategoryId}" id="currCategoryId"></a>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/category.js"></script>
            <%-- 遍历--%>
            <c:forEach items="${articles}" var="ar">

                <a href="${articlePath}${ar.id}.action" target="_top">  <article class="excerpt excerpt-1" style="cursor: pointer;">
                    <a class="focus" href="${articlePath}${ar.id}.action" title="" target="_top">
                        <img class="thumb" data-original="${ar.imagePath}" src="${pageContext.request.contextPath}/images/logo.png" alt="">
                    </a>
                    <header><a class="cat" href="program" target="_top">${ar.categoryName}<i></i></a>
                        <h2><a href="${articlePath}${ar.id}.action" title="" target="_top">${ar.title}</a></h2>
                    </header>
                    <p class="meta">
                        <time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${ar.updateDate}" pattern="yyyy-MM-dd" /></time>
                        <span class="views"><i class="glyphicon glyphicon-eye-open"></i> 共${ar.visitors}人围观</span> <a
                            class="comment" href="${articlePath}${ar.id}.action" target="_top"><i
                            class="glyphicon glyphicon-comment"></i> ${ar.commNum} 评论</a></p>
                     <p class="note">${ar.description} ...</p>
                </article>
                </a>
            </c:forEach>

            <nav class="pagination" id="pagination_index" style="display: none;">
                <ul>
                    <li class="prev-page"></li>
                    <li class="active"><span id="currPage">${currPage}</span></li>
                    <%--当前页数--%>
                    <li><a href="${path}?page=${nextPage}&categoryId=${currCategoryId}" target="_top">${nextPage}
                    </a></li>
                    <%--下一页数字--%>
                    <li class="next-page"><a id="slnext" href="${path}?page=${nextPage}&categoryId=${currCategoryId}">下一页</a>
                    </li>
                    <%--下页--%>
                    <li><span id="totalPage">${totalPage}</span></li>
                    <%--总页数--%>
                </ul>
            </nav>
        </div>
    </div>
    <%@ include file="_sidebar.jsp" %>
</section>
<%@ include file="_footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.ias.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/ias.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/snow/snow.js"></script>--%>


</body>
</html>

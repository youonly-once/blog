<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String articleHtml=request.getAttribute("html")+"";
%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>舒新胜博客文章页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nprogress.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clipboard.css">
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
    <style type="text/css" media="screen">
        html, body {
            height: 100%;
        }

        body {
            margin: 0;
            padding: 0;
            overflow: auto;
        }

        p{
            color: black;
        }
        h1{
            font-size: 24px;
            padding:10px 0;
        }
        h2{
            font-size: 20px;
            text-indent:30px;
            padding:10px 0;
        }
        h3{
            font-size: 18px;
            text-indent:45px;
            padding: 5px 0;
        }
        .container{
            max-width: 1500px;
        }
        .s0 { color: #d73a49;}
        .s1 { color: #6f42c1;}
        .s2 { color: #808080;}
        .s3 { color: #6a737d; }
        .s4 { color: #d73a49; font-style: italic;}
        .s5 { color: #6a737d;}
        .s6 { color: #005cc5;}
       .subtitle{
            text-indent: 15px;
        }
        .preImg{
          /*  padding-left: 60px;*/
            padding-bottom:20px;
        }



    </style>

</head>

<%--<body class="user-select single">--%>
<%--user-select 是禁止选泽网页内容的css--%>
<body class="single">
<%@ include file="_header.jsp" %>
<section class="container">
    <div class="content-wrap">
        <div class="content">
            <header class="article-header">
                <h1 class="article-title"><a href="#" id="articleTitle"> ${articleInfo.title}
                </a></h1>
                <div class="article-meta"> <span class="item article-meta-time">
          <time class="time" data-toggle="tooltip" data-placement="bottom"
                title="时间：<fmt:formatDate value="${articleInfo.updateDate}" pattern="yyyy-MM-dd"/>"><i
                  class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${articleInfo.updateDate}" pattern="yyyy-MM-dd" /></time>
          </span>
                    <span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="来源：${articleInfo.nickname}">
          <i class="glyphicon glyphicon-globe"></i> ${articleInfo.nickname}</span>
                    <span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom"
                          title="栏目：${articleInfo.categoryName}">
            <i class="glyphicon glyphicon-list"></i>
            <a href="program" title="">${articleInfo.categoryName}</a>
          </span>
                    <span class="item article-meta-views" data-toggle="tooltip" data-placement="bottom"
                          title="查看： ${articleInfo.visitors}">
            <i class="glyphicon glyphicon-eye-open"></i> 共${articleInfo.visitors}人围观</span>
                    <span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom"
                          title="评论：${articleInfo.commNum}">
            <i class="glyphicon glyphicon-comment"></i> ${articleInfo.commNum} 评论</span></div>
            </header>


            <script type="text/javascript" src="${pageContext.request.contextPath}/js/flexpaper/flexpaper_flash.js"></script>

            <article class="article-content">

                <c:import url="<%= articleHtml%>" charEncoding="UTF-8"/>
               <%-- <c:import var="data" url="https://www.bejson.com/"/>
                <c:out value="${data}"/>--%>
               <%-- <jsp:include page="<%= articleHtml%>"/>--%>
            </article>
            <div class="article-tags">标签：<a href="" rel="tag">${articleInfo.categoryName}
            </a></div>
            <div class="relates">
                <div class="title">
                    <h3>相关推荐</h3>
                </div>
                <ul id="recommendArticles">

                </ul>
            </div>
            <div class="title" id="comment">
                <h3>评论 <small>抢沙发</small></h3>
            </div>
            <!--<div id="respond">
              <div class="comment-signarea">
                <h3 class="text-muted">评论前必须登录！</h3>
                <p> <a href="javascript:;" class="btn btn-primary login" rel="nofollow">立即登录</a> &nbsp; <a href="javascript:;" class="btn btn-default register" rel="nofollow">注册</a> </p>
                <h3 class="text-muted">当前文章禁止评论</h3>
              </div>
            </div>-->
            <div id="respond">
                <form action="" method="post" id="comment-form">
                    <div class="comment">
                        <div class="comment-title"><img class="avatar" src="${pageContext.request.contextPath}/images/icon/head.jpg" alt=""/></div>
                        <div class="comment-box">
                            <textarea placeholder="您的评论可以一针见血" name="comment" id="comment-textarea" cols="100%" rows="3"
                                      tabindex="1"></textarea>
                            <div class="comment-ctrl"><span class="emotion"><img src="${pageContext.request.contextPath}/images/face/5.png" width="20"
                                                                                 height="20" alt=""/>表情</span>
                                <div class="comment-prompt"><i class="fa fa-spin fa-circle-o-notch"></i> <span
                                        class="comment-prompt-text"></span></div>
                                <input type="hidden" value="${articleInfo.id}" class="articleid"/>
                                <button type="submit" name="comment-submit" id="comment-submit" tabindex="5"
                                        articleid="${articleInfo.id}">评论
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="postcomments">
                <ol class="commentlist" id="commentlist">
                    <%--                  <c:forEach items="${articleComments}" var="co">
                            <li class="comment-content"><span class="comment-f">#1</span>
                                <div class="comment-avatar"><img class="avatar" src="${pageContext.request.contextPath}/images/icon/head.jpg" alt=""/></div>
                                <div class="comment-main">
                                    <p>来自用户<span class="address">${co.account}</span>的评论<span class="time">(${co.createdate})</span><br/>
                                            ${co.comment}</p>
                                </div>
                            </li>
                        </c:forEach>--%>
                </ol>
                <%--  文章id，js获取--%>
                <input style="display:none;" id="articleid" value="${articleInfo.id}"/>
                <%-- 评论总数--%>
                <input style="display:none;" id="commentnum" value="${articleInfo.commNum}"/>
                <%--每页显示数量--%>
                <input style="display:none;" id="commenpagenum" value="${pageCommNum}"/>

                <div class="quotes" id="quotes">
                    <%--       <span class="disabled" id="homepage">首页</span>
                           <span class="disabled" id="previouspage">上一页</span>
                           <a class="current" id="currentpage" >1</a>
                           <a href="">2</a>
                           <span class="disabled" id="nextpage">下一页</span>
                           <span class="disabled" id="lastpage">尾页</span>--%>
                </div>

            </div>
            <script src="${pageContext.request.contextPath}/js/commentPage.js"></script>
        </div>
    </div>
    <%@ include file="_sidebar.jsp" %>
</section>
<%@ include file="_footer.jsp" %>


<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.ias.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.qqFace.js"></script>
<script src="${pageContext.request.contextPath}/js/recommendArticle.js"></script>
<!-- 代码块复制功能 -->
<script src="${pageContext.request.contextPath}/js/clipboard/clipboard.min.js"></script>
<script src="${pageContext.request.contextPath}/js/clipboard/clipboard-use.js"></script>
<script type="text/javascript">
    $(function () {
        $('.emotion').qqFace({
            id: 'facebox',
            assign: 'comment-textarea',
            path: '/images/arclist/'	//表情存放的路径
        });
    });
</script>

</body>
</html>
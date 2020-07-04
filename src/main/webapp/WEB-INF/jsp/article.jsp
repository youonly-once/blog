<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

        #flashContent {
            display: none;
        }
    </style>

</head>

<body class="user-select single">
<%@ include file="_header.jsp" %>
<section class="container">
    <div class="content-wrap">
        <div class="content">
            <header class="article-header">
                <h1 class="article-title"><a href="#" id="articleTitle"> ${articleInfo.title}
                </a></h1>
                <div class="article-meta"> <span class="item article-meta-time">
          <time class="time" data-toggle="tooltip" data-placement="bottom"
                title="时间：${articleInfo.updateDate}"><i
                  class="glyphicon glyphicon-time"></i> ${articleInfo.updateDate}</time>
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
                <div id="getflash" style="display: none;text-align: center;margin-top: 150px;margin-bottom: 20px;">
                    <!-- <a href="http://www.adobe.com/go/getflashplayer" rel="nofollow" target="_blank" title="升级Flash插件">启用flash</a> -->
                    <%--<p><i class="iconfont_video_details" style="font-size: 30px;">&#xe625;</i></p>--%>
                    <a href="https://get.adobe.com/cn/flashplayer/" target="_blank"
                       style="color:#3399CC;font-size: 18px;">您尚未安装或未允许浏览器运行Flash，点击允许即可浏览文档</a>
                </div>
                <a id="viewerPlaceHolder"<%-- style="width:860px;height:720px;display:block;"--%>></a>
                <SCRIPT src="${pageContext.request.contextPath}/js/swfobject.js"></SCRIPT>
                <script type="text/javascript">

                    var swfVersionStr = "10.0.0";

                    var xiSwfUrlStr = "playerProductInstall.swf";

                    var flashvars = {
                        SwfFile: escape('${pageContext.request.contextPath}${articleInfo.targetFilePath}'),
                        Scale: 0.6,
                        ZoomTransition: 'easeOut',
                        ZoomTime: 0.5,
                        ZoomInterval: 0.2,
                        FitPageOnLoad: false,
                        FitWidthOnLoad: true,
                        PrintEnabled: false,
                        //是否支持全屏,当设置为true的时候，单击全屏按钮会打开一个flexpaper最大化的新窗口而不是全屏，当由于fla　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
                        // sh播放器因为安全而禁止全屏，而使用flexpaper作为独立的flash播放器的时候设置为true是个优先选择。
                        FullScreenAsMaxWindow: false,
                        //当设置为true的时候，展示文档时不会加载完整个文档，而是逐步加载，但是需要将文档转化为9以上的flash版本（使　　　　　
                        //用pdf2swf的时候使用-T 9 标签）。
                        ProgressiveLoading: true,
                        //最小的缩放比例。
                        MinZoomSize: 0.2,
                        //设置最大的缩放比例。
                        MaxZoomSize: 5,
                        //设置为true的时候，单击搜索所有符合条件的地方高亮显示。
                        SearchMatchAll: true,
                        //启动模式
                        InitViewMode: 'TwoPage',
                        //工具栏上是否显示样式选择框(就是显示缩略图或分页显示的工具)
                        VMode: 'window',

                        ViewModeToolsVisible: true,
                        //工具栏上是否显示缩放工具
                        ZoomToolsVisible: true,
                        //工具栏上是否显示导航工具(也就是页码工具)
                        NavToolsVisible: true,
                        //工具栏上是否显示光标工具
                        CursorToolsVisible: false,
                        //工具栏上是否显示搜索
                        SearchToolsVisible: true,

                        //localeChain: 'en_US'
                        localeChain: 'zh_CN'
                    }
                    /*          var fp = new FlexPaperViewer(
                                      'FlexPaperViewer',
                                      'viewerPlaceHolder', { config : flashvars});*/
                    var params = {}
                    params.quality = "high";
                    params.bgcolor = "#ffffff";
                    params.allowscriptaccess = "sameDomain";
                    params.allowfullscreen = "true";
                    var attributes = {};
                    attributes.id = "FlexPaperViewer";
                    attributes.name = "FlexPaperViewer";
                    swfobject.embedSWF(
                        "${pageContext.request.contextPath}/FlexPaperViewer.swf", "viewerPlaceHolder",
                        "100%", "800",
                        swfVersionStr, xiSwfUrlStr,
                        flashvars, params, attributes);
                    swfobject.createCSS("#flashContent", "display:block;text-align:left;");

                </script>
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

<script>
    //判断是否安装或允许flash
    function flashChecker() {
        var hasFlash = 0;　　　　 //是否安装了flash
        var flashVersion = 0;　　 //flash版本
        if (document.all) {
            var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
            if (swf) {
                hasFlash = 1;
                VSwf = swf.GetVariable("$version");
                flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
            }
        } else {
            if (navigator.plugins && navigator.plugins.length > 0) {
                var swf = navigator.plugins["Shockwave Flash"];
                if (swf) {
                    hasFlash = 1;
                    var words = swf.description.split(" ");
                    for (var i = 0; i < words.length; ++i) {
                        if (isNaN(parseInt(words[i]))) continue;
                        flashVersion = parseInt(words[i]);
                    }
                }
            }
        }
        return {f: hasFlash, v: flashVersion};
    }

    var fls = flashChecker();
    var s = "";
    if (!fls.f) {
        $("#getflash").show();
        $(".video-box-1").hide();
    }

</script>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.ias.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.qqFace.js"></script>
<script src="${pageContext.request.contextPath}/js/recommendArticle.js"></script>
<script type="text/javascript">
    $(function () {
        $('.emotion').qqFace({
            id: 'facebox',
            assign: 'comment-textarea',
            path: '${pageContext.request.contextPath}/images/arclist/'	//表情存放的路径
        });
        //禁止滚动条滚动
        function unScroll() {
            var top = $(document).scrollTop();
            $(document).on('scroll.unable',function (e) {
                $(document).scrollTop(top);
            })
        }
        //移除禁止滚动条滚动
        function removeUnScroll() {
            $(document).unbind("scroll.unable");
        }
        $(".article-content").mouseover(function () {
            //禁用滚动条
            $('body').css({
                "overflow-y":"hidden"
            });
        });
        $(".article-content").mouseout(function () {
            //开启滚动条
            $('body').css({
                "overflow-y":"auto"
            });
        });

    });
</script>
<div id="flashContent">
    <p>
    </p>
</div>
</body>

</html>
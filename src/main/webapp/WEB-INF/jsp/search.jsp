<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>舒新胜博客栏目页面</title>
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
  <script>window.location.href='upgrade-browser.jsp';</script>
<![endif]-->
</head>

<body class="user-select">
<%@ include file="_header.jsp"%>
<section class="container">
  <div class="content-wrap">
    <div class="content">
      <div class="widget widget_search" >
        <form class="navbar-form"  id="search_form">
          <div class="input-group">
            <input type="text" name="searchStr" id="searchStr" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off">
            <span class="input-group-btn">
            <button class="btn btn-default btn-search"  type="submit" id="search_bt">搜索</button>
            </span> </div>
        </form>
      </div>
<%--      <div class="title" >
        <h3>搜索</h3>
      </div>--%>




<%--      <nav class="pagination" id="pagination_index" style="display: none;">
        <ul>
          <li class="prev-page"></li>
          <li class="active"><span id="currPage"></span></li>
          &lt;%&ndash;当前页数&ndash;%&gt;
          <li><a href="" target="_blank"></a></li>
          &lt;%&ndash;下一页数字&ndash;%&gt;
          <li class="next-page"><a id="slnext" href="">下一页</a>
          </li>
          &lt;%&ndash;下页&ndash;%&gt;
          <li><span id="totalPage"></span></li>
          &lt;%&ndash;总页数&ndash;%&gt;
        </ul>
      </nav>--%>
        <%-- 评论总数--%>
        <input style="display:none;" id="searchnum" value=""/>
        <%--每页显示数量--%>
        <input style="display:none;" id="searchpagenum" value=""/>
        <div class="quotes" id="quotes">
            <%--       <span class="disabled" id="homepage">首页</span>
                   <span class="disabled" id="previouspage">上一页</span>
                   <a class="current" id="currentpage" >1</a>
                   <a href="">2</a>
                   <span class="disabled" id="nextpage">下一页</span>
                   <span class="disabled" id="lastpage">尾页</span>--%>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/searchPage.js"></script>
  </div>
  <%@ include file="_sidebar.jsp"%>
</section>
<%@ include file="_footer.jsp"%>

<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery/jquery.ias.js"></script>
<script src="/js/scripts.js"></script>
<%--<script src="/js/search.js"></script>--%>
<%--<script src="/js/ias.js"></script>--%>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>404错误！很抱歉，您要找的页面不存在</title>
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
<style type="text/css">
.panel{
	padding:80px 20px 0px;
	min-height:500px;
	cursor:default;
}
.text-center {
	margin:0 auto;
    text-align: center;
	border-radius:10px;
	max-width:900px;
	-moz-box-shadow: 0px 0px 5px rgba(0,0,0,.3);
	-webkit-box-shadow: 0px 0px 5px rgba(0,0,0,.3);
	box-shadow: 0px 0px 5px rgba(0,0,0,.1);
	
}
.float-left {
    float: left !important;
}
.float-right {
    float: right !important;
}
img {
    border: 0;
    vertical-align: bottom;
}
h2 {
    padding-top: 20px;
	font-size: 20px;
}
.padding-big {
    padding: 20px;
}
.alert {
    border-radius: 5px;
    padding: 15px;
    border: solid 1px #ddd;
    background-color: #f5f5f5;
}
</style>
</head>

<body class="user-select">
<%@include file="_header.jsp"%>
<section class="container">
  <div class="panel">
    <div class="text-center">
      <h2><stong>404错误！很抱歉，您要找的页面不存在</stong></h2>
      <div>
        <div class="float-left"> <img src="${pageContext.request.contextPath}/images/404/left.gif" alt="" />
          <div class="alert"> 卧槽！页面不见了！ </div>
        </div>
        <div class="float-right"> <img src="${pageContext.request.contextPath}/images/404/right.png" width="260" class="hidden-xs" alt="" /> </div>
      </div>
      <div class="padding-big"> <a href="${pageContext.request.contextPath}/" class="btn btn-primary">返回首页</a> <a href="feedback" class="btn btn-default">保证不打死管理员</a> </div>
    </div>
  </div>
</section>
<%@ include file="_footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.ias.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
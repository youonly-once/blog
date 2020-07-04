<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>--%>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
<header class="header">
    <nav class="navbar navbar-default" id="navbar">
        <div class="container">
            <div class="header-topbar hidden-xs link-border">
                <ul class="site-nav topmenu">
                    <li><a href="${pageContext.request.contextPath}/header/tags.action">标签云</a></li>
                    <li><a href="${pageContext.request.contextPath}/header/readers.action" rel="nofollow">读者墙</a></li>
                    <li><a href="${pageContext.request.contextPath}/header/links.action" rel="nofollow">友情链接</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button"
                                            aria-haspopup="true" aria-expanded="false" rel="nofollow">关注我 <span
                            class="caret"></span></a>
                        <ul class="dropdown-menu header-topbar-dropdown-menu">
                            <li><a data-toggle="modal" data-target="#WeChat" rel="nofollow"><i class="fa fa-weixin"></i>
                                微信</a></li>
                            <li><a href="#" rel="nofollow"><i class="fa fa-weibo"></i> 微博</a></li>

                        </ul>
                    </li>
                </ul>
                <%-- 判断，如果session 中有user，说明已经登录 --%>
                <c:if test="${empty user}"> <%-- 从作用域中获取user 判断是否为空--%>
                  <a data-toggle="modal" data-target="#loginModal" class="login" rel="nofollow">Hi,请登录</a>&nbsp;&nbsp;
                 <a href="${pageContext.request.contextPath}/register/register.html" class="register" rel="nofollow" target="_blank">我要注册</a>
                </c:if>

                <c:if test="${!empty user}"> <%-- 从作用域中获取user 判断是否不为空--%>
                    欢迎 <a href="" style="color:#3399CC;"> ${user.getNickname()} !</>
                    <a href="${pageContext.request.contextPath}/user/logout.action">&nbsp注销</a>
                </c:if>
                &nbsp;&nbsp&nbsp<a href="" rel="nofollow">找回密码</a>
            </div>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#header-navbar" aria-expanded="false"><span class="sr-only"></span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
                <h1 class="logo hvr-bounce-in"><a href="${pageContext.request.contextPath}/" title="首页"><img
                        src="${pageContext.request.contextPath}/images/logo.png" alt=""></a></h1>
            </div>
            <div class="collapse navbar-collapse" id="header-navbar">
                <ul class="nav navbar-nav navbar-right">
                    <%--  <li class="hidden-index active"><a data-cont="舒新胜首页" href="${pageContext.request.contextPath}/index">舒新胜首页</a></li>--%>
                   <%-- <li><a href="#">前端技术</a></li>
                    <li><a href="#">后端程序</a></li>--%>
                    <li><a href="#">程序人生</a></li>
                </ul>
                <form class="navbar-form visible-xs" action="${pageContext.request.contextPath}/search.action" method="get">
                    <div class="input-group">
                        <input type="text" name="searchStr" class="form-control" placeholder="请输入关键字" maxlength="20"
                               autocomplete="off">
                        <span class="input-group-btn">
            <button class="btn btn-default btn-search"  type="submit" >搜索</button>
            </span></div>
                </form>
            </div>
        </div>
    </nav>
</header>


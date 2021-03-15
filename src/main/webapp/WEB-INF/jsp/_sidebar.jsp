<%--
  Created by IntelliJ IDEA.
  User: 11307
  Date: 2020/4/18
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="sidebar">
    <div class="fixed">
        <div class="widget widget-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#notice" aria-controls="notice" role="tab" data-toggle="tab">网站公告</a></li>
                <li role="presentation"><a href="#centre" aria-controls="centre" role="tab" data-toggle="tab">会员中心</a></li>
                <li role="presentation"><a href="#contact" aria-controls="contact" role="tab" data-toggle="tab">联系站长</a></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane notice active" id="notice">
                    <ul>
                        <li>
                            <time datetime="2019-01-04">01-04</time>
                            <a href="" target="_top">欢迎访问舒新胜博客</a></li>
                        <li>
                            <time datetime="2019-01-04">01-04</time>
                            <a target="_top" href="">在这里可以看到我的学习心得！</a></li>

                    </ul>
                </div>
                <div role="tabpanel" class="tab-pane centre" id="centre">
                    <c:if test="${empty user}">
                    <h4>需要登录才能进入会员中心</h4>
                    <p> <a data-toggle="modal" data-target="#loginModal" class="btn btn-primary">立即登录</a>
                        <a href="${pageContext.request.contextPath}/register/register.html" class="btn btn-default" target="_top">现在注册</a> </p>
                    </c:if>
                </div>
                <div role="tabpanel" class="tab-pane contact" id="contact">
                    <h2>Email:<br />
                        <a href="mailto:1130716354@qq.com" data-toggle="tooltip" data-placement="bottom" title="1130716354@qq.com">1130716354@qq.com</a></h2>
                </div>
            </div>
        </div>
        <div class="widget widget_search">
            <form class="navbar-form" action="${pageContext.request.contextPath}/search.action" method="get">
                <div class="input-group">
                    <input type="text" name="searchStr" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off">
                    <span class="input-group-btn">
            <button class="btn btn-default btn-search"  type="submit">搜索</button>
            </span> </div>
            </form>
        </div>
    </div>
    <div class="widget widget_sentence">
        <h3>每日一句</h3>
        <div class="widget-sentence-content">
            <h4>2019年01月05日星期二</h4>
            <p>Do not let what you cannot do interfere with what you can do.<br />
                别让你不能做的事妨碍到你能做的事。（John Wooden）</p>
        </div>
    </div>
    <div class="widget widget_hot" >
        <h3>热门文章</h3>
        <ul id="hotArticles">

        </ul>
    </div>

</aside>
<script src="${pageContext.request.contextPath}/js/sidebar/loadHotArticle.js"></script>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 11307
  Date: 2020/4/18
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
    <div class="container">
        <p>&copy; 2019 <a href="">shuxinsheng.com</a> &nbsp;</p>
    </div>
    <div id="gotop"><a class="gotop"></a></div>
</footer>
<!--微信二维码模态框-->
<div class="modal fade user-select" id="WeChat" tabindex="-1" role="dialog" aria-labelledby="WeChatModalLabel">
    <div class="modal-dialog" role="document" style="margin-top:120px;max-width:280px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="WeChatModalLabel" style="cursor:default;">微信扫一扫</h4>
            </div>
            <div class="modal-body" style="text-align:center"> <img src="${pageContext.request.contextPath}/images/weixin.jpg" alt="" style="cursor:pointer"/> </div>
        </div>
    </div>
</div>
<!--该功能正在日以继夜的开发中-->
<div class="modal fade user-select" id="areDeveloping" tabindex="-1" role="dialog" aria-labelledby="areDevelopingModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="areDevelopingModalLabel" style="cursor:default;">该功能正在日以继夜的开发中…</h4>
            </div>
            <div class="modal-body"> <img src="${pageContext.request.contextPath}/images/baoman/baoman_01.gif" alt="深思熟虑" />
                <p style="padding:15px 15px 15px 100px; position:absolute; top:15px; cursor:default;">很抱歉，程序猿正在日以继夜的开发此功能，本程序将会在以后的版本中持续完善！</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">朕已阅</button>
            </div>
        </div>
    </div>
</div>
<!--登录注册模态框-->
<div class="modal fade user-select" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="" method="post" onsubmit="return false">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="loginModalLabel">登录</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="loginModalUserNmae">用户名</label>

                        <input type="text" class="form-control" id="loginModalUserNmae" name="username" placeholder="请输入用户名" value="${cookie.username.value}" autofocus maxlength="15" autocomplete="off" required>
                    </div>
                    <div class="form-group">
                        <label for="loginModalUserPwd">密码</label>
                        <input type="password" class="form-control" id="loginModalUserPwd" name="password" placeholder="请输入密码" value="${cookie.password.value}" maxlength="18" autocomplete="off" required>
                    </div>
                </div>
                <div class="modal-checkbox">

                        <input type="checkbox" name="remname" id="loginModalRemName" value="true" ${empty cookie.username.value ? "":"checked='checked'"}/>
                    <label>记住用户名</label>

                        <input type="checkbox" name="autologin" id="loginModalAuto" value="true" ${empty cookie.password.value ? "":"checked='checked'"}/>
                    <label>自动登录</label>
                </div>
                <div class="modal-footer">
                    <span style="color:red;float:left;" id="loginMsg"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button  class="btn btn-primary" id="login">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--右键菜单列表-->
<div id="rightClickMenu">
    <ul class="list-group rightClickMenuList">
        <li class="list-group-item disabled">欢迎访问舒新胜的博客</li>
        <li class="list-group-item"><span>IP：</span></li>
        <li class="list-group-item"><span>地址：</span></li>
        <li class="list-group-item"><span>系统：</span> </li>
        <li class="list-group-item"><span>浏览器：</span></li>
    </ul>
</div>
<script src="${pageContext.request.contextPath}/js/login/login.js"></script>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--保存每页显示的评论数量--%>

    <c:forEach items="${articleComments}" var="co">
        <li class="comment-content"><span class="comment-f">#1</span>
            <div class="comment-avatar"><img class="avatar" src="${pageContext.request.contextPath}/images/icon/head.jpg" alt=""/></div>
            <div class="comment-main">
                <p>来自用户<span class="address">${co.user.nickname}</span>的评论<span class="time">(${co.createDate})</span><br/>
                        ${co.comment}</p>
            </div>
        </li>
    </c:forEach>




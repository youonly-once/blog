
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>

<d:forEach items="${articles}" var="har">
    <li><a href="${pageContext.request.contextPath}/article/detail/${har.id}.action" target="_top"><span class="thumbnail">
    <img class="thumb" data-original="${pageContext.request.contextPath}/${har.imagePath}" src="${pageContext.request.contextPath}/${har.imagePath}" alt="">
    </span>
        <span class="text">${har.title}</span>
        <span class="muted"><i class="glyphicon glyphicon-time"></i> ${har.updateDate} </span>
        <span class="muted"><i class="glyphicon glyphicon-eye-open"></i> ${har.visitors}</span>
        <span class="text">${har.description}</span>
    </a></li>


</d:forEach>

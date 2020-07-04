<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${recommendArticles}" var="re">
    <li><a href="${pageContext.request.contextPath}/article/detail/${re.id}.action" target="_blank">${re.title}</a></li>
</c:forEach>

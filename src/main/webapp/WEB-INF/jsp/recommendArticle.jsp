<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:forEach items="${recommendArticles}" var="re">
    <li>
        <div style="display:flex;justify-content:space-between;">
            <a href="${pageContext.request.contextPath}/article/detail/${re.id}.action" target="_top">${re.title}</a>
            <span> <fmt:formatDate value="${re.updateDate}" pattern="yyyy-MM-dd" /></span>
        </div>
    </li>
</c:forEach>

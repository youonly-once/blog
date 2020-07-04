<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/regist/regist.js"></script>
</head>
<body>
<form onsubmit="return checkForm()" action="/RegistServlet" method="POST">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr>
            <td colspan="2" style="text-align:center;" id="tip">
                ${msg}
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" value="${param.username}"/>
                <span></span>
            </td>
        </tr>

        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" value="${param.password}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" value="${param.password2}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname" value="${param.nickname}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" value="${param.email}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" />
                <img src="${pageContext.request.contextPath}//Captcha"  id="valiimg" />
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="注册用户"/>

            </td>
        </tr>
    </table>
</form>
</body>
</html>

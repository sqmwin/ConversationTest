<%--
  Created by IntelliJ IDEA.
  User: sqm
  Date: 2017-11-16
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>
<div>
    <form action="/test/checkservlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="verificationCode" id="verificationCode"></td>
        </tr>
        <tr>
            <td><a href="#" onclick="changeCode()">
                <img src="/test/verificationcodeservlet" alt="验证码" id="code"/>换一张
            </a></td>
        </tr>
        <tr>
            <td><h3>${requestScope.msg}</h3></td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" id="submit" value="登 陆"></td>
        </tr>
    </table>
    </form>
</div>

</body>
<script type="text/javascript">
    function changeCode() {
        var code = document.getElementById("code");
        code.src = "/test/verificationcodeservlet?" + new Date().getTime();

    }
</script>
</html>

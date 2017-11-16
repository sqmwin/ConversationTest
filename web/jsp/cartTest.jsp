<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%--
  购物车结算页面
  User: sqm
  Date: 2017-11-15
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车结算页面</title>
</head>
<body>
<%
    /*创建Map对象接收购物车的内容,cart存储在session域中*/
    Map<String,Integer> cart = (Map<String,Integer>)request.getSession().getAttribute("cart");
    /*判断购物车对象是否存在*/
    if (cart == null){
        //如果不存在,返回提示,你的购物车是空的
%>
<h3>你的购物车为空</h3>
<%
    }else{
        //如果存在,依次遍历返回购物车的商品名和数量
        Set<String> nameSet = cart.keySet();
        for(String name:nameSet) {
            int count = cart.get(name);
%>
        <h3>商品名:<%=name%>,数量:<%=count%></h3>
<%
        }
%>
<h3><a href="/test/cleanallservlet">清空购物车</a></h3>
<%
    }
%>

<h3><a href="shopTest.jsp">返回购物页面</a></h3>

</body>
</html>

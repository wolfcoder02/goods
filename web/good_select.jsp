<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/8/10
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查询页面</title>
    <style>
        table {
            margin: auto;
            margin-top: 50px;
        }

        td {
            border: 1px solid black;
        }

    </style>
</head>
<body>
<form action="goodsServlet" method="post">
    <table cellspacing="0">
        <c:if test="${list==null}">
            <input type="hidden" name="opr" value="select">
            <input type="hidden" name="flag" value="1">
            <tr>
                <td>商品序号</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>商品名称</td>
                <td><input type="text" name="goodsInfoName"/></td>
            </tr>
            <tr>
                <td>商品图片</td>
                <td><input type="text" name="goodsInfoPic"/></td>
            </tr>
            <tr>
                <td>商品描述</td>
                <td><input type="text" name="goodsInfoDescription"/></td>
            </tr>
            <tr>
                <td>商品库存</td>
                <td><input type="text" name="goodsInfoStock"/></td>
            </tr>
            <tr>
                <td>创建时间</td>
                <td><input type="text" name="createdDate"/></td>
            </tr>
            <tr>
                <td>创建人</td>
                <td><input type="text" name="created"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"></td>
                <td><input type="button" value="返回" onclick="history.back()"></td>
            </tr>
        </c:if>
        <c:if test="${list!=null}">
            <tr>
                <th>商品序号</th>
                <th>商品名字</th>
                <th>商品图片</th>
                <th>商品价格</th>
                <th>商品描述</th>
                <th>商品库存</th>
                <th>创建人</th>
                <th>创建时间</th>
            </tr>
            <c:forEach items="${list}" var="good" varStatus="s">
                <tr <c:if test="${s.count%2==0}">style="background-color: yellow"</c:if>>
                    <td>${good.id}</td>
                    <td>${good.goodsInfoName}</td>
                    <td>${good.goodsInfoPic}</td>
                    <td>${good.goodsInfoPrice}</td>
                    <td>${good.goodsInfoDescription}</td>
                    <td>${good.goodsInfoStock}</td>
                    <td>${good.created}</td>
                    <td>${good.createdDate}</td>
                </tr>
                <tr>
                    <td><input type="submit" value="主页面"></td>
                    <td><input type="button" value="返回" onclick="history.back()"></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</form>
</body>
</html>

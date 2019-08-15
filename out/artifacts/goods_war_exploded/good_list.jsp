<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL_DEMO</title>
    <style>
        body{
            background-color: lightgray;
        }
        table{
            margin: auto;
            margin-top: 100px;
            border-radius: 10px;
            background-color: white;
        }
        th,td{
            border:1px solid black;
            text-align: center;
            width: 80px;
            height: 50px;

        }
        .select{
            padding-left: 50px;
            text-align: center;
        }
        caption{
            font-size: 30px;
            text-shadow: 5px 5px 5px orange;

        }
        .opr{
            width:200px;
        }
    </style>
</head>
<body>
<table border="1" cellspacing="0">
    <input type="hidden" name="flag" value="1">
    <caption>商品信息表</caption>
    <thead>
        <tr>
            <th>商品序号</th>
            <th>商品名字</th>
            <th>商品图片</th>
            <th>商品价格</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
    <c:if test="${list!=null}">
        <c:forEach items="${list}" var="good" varStatus="s">
            <tr>
                <td>${good.id}</td>
                <td>${good.goodsInfoName}</td>
                <td>

                    <img src="upload/${good.goodsInfoPic}" alt="找不到图片" title="${fileName}" />
<%--                    <img src="upload/柠檬.jpg" alt="找不到图片" style="width: 50px; height: 50px" title="${fileName}" />--%>
                </td>
                <td>${good.goodsInfoPrice}</td>
                <td class="opr">
                    <a href="good_add.jsp">添加</a>
                    <a href="goodsServlet?opr=delete&id=${good.id}" onclick="return confirm('确定删除吗')">删除</a>
                    <a href="goodsServlet?opr=update&id=${good.id}">编辑</a>
                    <a href="goodsServlet?opr=view&id=${good.id}">详情</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" class="select"><a href="good_select.jsp">查询</a></td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>

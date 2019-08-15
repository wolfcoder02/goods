<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/8/12
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品详情</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        body{
            background-color: lightgray;
        }
        table{
              margin: auto;
             margin-top: 50px;
        }
        td{
            border: 1px solid black;
            padding: 0;
            width:150px;
            height: 40px;
        }
        caption{
            font-size: 30px;

        }

    </style>
</head>
<body>
    <table cellspacing="0">
              <caption>商品详情</caption>
              <tr>
                  <td>商品序号</td>
                  <td>${good.id}</td>
              </tr>
              <tr>
                  <td>商品名字</td>
                  <td>${good.goodsInfoName}</td>
              </tr>
              <tr>
                  <td>商品图片</td>
                  <td>
                      <a href="fileDownloadServlet?fileName=${fileName}">
                      <img src="upload/${fileName}" alt="找不到图片" title="${fileName}" />
                        </a>
                  </td>
              </tr>
              <tr>
                  <td>商品价格</td>
                  <td>${good.goodsInfoPrice}</td>
              </tr>
              <tr>
                  <td>商品描述</td>
                  <td>${good.goodsInfoDescription}</td>
              </tr>
              <tr>
                  <td>商品库存</td>
                  <td>${good.goodsInfoStock}</td>
              </tr>
              <tr>
                  <td>商品权限</td>
                  <td>${good.flag}</td>
              </tr>
              <tr>
                  <td>商品创建人</td>
                  <td>${good.created}</td>
              </tr>
              <tr>
                  <td>创建日期</td>
                  <td>${good.createdDate}</td>
    </table>
</body>
</html>

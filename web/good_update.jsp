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
            margin-top: 100px;
            border-radius: 10px;
            background-color: white;
        }
        td{
            border:1px solid black;
            text-align: center;
            width: 150px;
            height:40px;
            text-align: center;
            font-size:20px;
        }
        .td_right{
            width: 320px;
        }
        caption{
            font-size: 30px;
            text-shadow: 5px 5px 5px orange;
        }

        .in{
            border-radius: 30px;
            width:300px;
            height:30px;
            text-align: center;
            font-size:20px;
        }

        .button{
            font-size: 20px;
        }
        scan{
            color:red;
        }
    </style>
</head>
<body>
<form action="goodsServlet?opr=update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="k" value="1">
    <table cellspacing="0" >
        <caption>商品修改</caption>
              <tr>
              <td class="td_left">商品序号</td>
                  <td class="td_right"><input type="text" name="id" class="in"  value="${good.id}"></td>
              </tr>
              <tr>
              <td class="td_left">商品名字</td>
              <td class="td_right"><input type="text" name="goodsInfoName" class="in"  value="${good.goodsInfoName}"></td>
              </tr>
              <tr id="tr_img">
              <td class="td_left">商品图片</td>
              <td class="td_right"><input type="file" name="upload"></td>
              </tr>
              <tr>
              <td class="td_left">商品价格</td>
              <td class="td_right"><input type="text" name="goodsInfoPrice" class="in" value="${good.goodsInfoPrice}"></td>
              </tr>
              <tr>
              <td class="td_left">商品描述</td>
              <td class="td_right"><input type="text" name="goodsInfoDescription" class="in" value="${good.goodsInfoDescription}"></td>
              </tr>
              <tr>
              <td class="td_left">商品库存</td>
              <td class="td_right"><input type="text" name="goodsInfoStock"  class="in" value="${good.goodsInfoStock}"></td>
              </tr>
              <tr>
              <td class="td_left">商品权限</td>
              <td class="td_right"><input type="text" name="flag" class="in" value="${good.flag}"></td>
              </tr>
        <tr>
            <td colspan="2" >
                <input type="submit" value="修改" class="button">
                <input type="button" value="返回" onclick="history.back()" class="button">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

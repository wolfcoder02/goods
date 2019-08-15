<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL_DEMO</title>
    <style>
        body{
            background-color: lightgray;
        }
        *{
            margin: 0px;
            padding:0px;
        }
        td{
            border:1px solid black;
            text-align: center;
            width:300px;
            height: 40px;
        }
        table{
            margin:auto;
            margin-top: 50px;
        }
        caption {
            font-size: 30px;
            text-shadow: 5px 5px 5px blue;
        }
        .button{
            font-size: 30px;
        }
        scan{
            color: red;
        }
    </style>
</head>
<body>
    <form action="goodsServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="opr" value="add" />
        <input type="hidden" name="flag" value="1">
        <table>
            <caption>添加商品信息</caption>
            <tr>
                <td>商品序号</td>
                <td><input type="text" name="id" class="in" placeholder="请输入序号:" /><scan>*</scan></td>
            </tr>
            <tr>
                <td>商品名称</td>
                <td><input type="text" name="goodsInfoName" class="in" placeholder="请输入商品名称:" /><scan>*</scan></td>
            </tr>
            <tr>
                <td>商品图片</td>
                <td><input type="file" name="upload" ><scan>*</scan></td>
            </tr>
            <tr>
                <td>商品价格</td>
                <td><input type="text" name="goodsInfoPrice" class="in" placeholder="请输入商品价格:" /><scan>*</scan></td>
            </tr>
            <tr>
                <td>商品描述</td>
                <td><input type="text" name="goodsInfoDescription" class="in" placeholder="请输入描述:"/><scan>*</scan></td>
            </tr>
            <tr>
                <td>商品库存</td>
                <td><input type="text" name="goodsInfoStock" class="in" placeholder="请输入商品库存:"/><scan>*</scan></td>
            </tr>
            <tr>
                <td>创建人</td>
                <td><input type="text" name="created" class="in" placeholder="请输入创建人:"/><scan>*</scan></td>
            </tr>
                <td colspan="2" class="button">
                    <input type="submit" value="保存">
                    <input type="button" value="返回" onclick="history.back()">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>

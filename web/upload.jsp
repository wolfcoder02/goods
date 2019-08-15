<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 2019/8/8
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="fileUploadServlet" method="post" enctype="multipart/form-data">
        标题：<input name="title"><br/>
        上传文件：<input type="file" name="upload"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>

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
    <a href="fileDownloadServlet?fileName=${fileName}">
        <img src="upload/${fileName}" alt="找不到图片" title="${fileName}" />
    </a>
</body>
</html>

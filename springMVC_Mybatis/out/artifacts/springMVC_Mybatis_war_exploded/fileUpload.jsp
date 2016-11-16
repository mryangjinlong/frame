<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/16
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload/doUploadSingle.html" method="post" enctype="multipart/form-data">

    file: <input type="file" name="file"/><input type="submit" value="上传">
</form>
<form action="/upload/doUploadMultipart.html" method="post" enctype="multipart/form-data">

    files: <input type="file" name="files"/>
    files: <input type="file" name="files"/>
    <input type="submit" value="上传">
</form>
</body>
</html>

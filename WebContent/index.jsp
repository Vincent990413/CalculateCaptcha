<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>验证码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        function reloadedCode() {
            var time = new Date().getTime(); //防止由于缓存而不更新图片
            document.getElementById("imagecode").src = "<%=request.getContextPath()%>/servlet/ImageServlet?d=" + time;
        }
    </script>
</head>
<body>
<form action="<%=request.getContextPath()%>/servlet/LoginServlet" method="get">
    <div>
        验证码：<input type="text" name="checkcode"/>

        <img alt="验证码" id="imagecode" src="<%=request.getContextPath()%>/servlet/ImageServlet"/>
        <a href="javascript:reloadedCode();">换验证码</a><br>
    </div>

    <div>
        <input class="btn btn-primary btn-block" type="submit" value="提交">
    </div>
</form>

</body>
</html>
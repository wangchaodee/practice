<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- JSP中的表达式被当成字符串处理 isELIgnored属性用来指定是否忽略 -->
<%@ page isELIgnored ="false"%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP file</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h2>Hello World!</h2>
        <p>
            This is a simple JSP file.
        </p>
        <p>hello ${name}</p>
        <p>show number  ${number}</p>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form CheckBox</title>
</head>
<body>
<h2>Form CheckBox</h2>


    <c:if test = "${adult == 'on'}">
        <p>name : ${uname} is adult</p>
        <p>age is ${uage} </p>
    </c:if>

    <c:choose>
<%--        {adult==null}   {adult==''}   {empty adult}   不起效果 ">--%>
        <c:when test="${adult == null or adult == ''} ">
            <p>name : ${uname} is minor</p>
            <p>age is ${uage} </p>
        </c:when>
        <c:otherwise>
            <p>otherwise name : ${uname} is minor</p>
            <p>age is ${uage} </p>
        </c:otherwise>
    </c:choose>

</body>
</html>

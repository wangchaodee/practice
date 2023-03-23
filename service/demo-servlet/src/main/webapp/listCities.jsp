<%--
  Created by IntelliJ IDEA.
  User: chaowang5
  Date: 2023/3/23
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cities</title>
</head>
<body>
<h2>Cities</h2>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Population</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${cities}" var="city">
        <tr>
            <td>${city.id}</td>
            <td>${city.name}</td>
            <td>${city.population}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

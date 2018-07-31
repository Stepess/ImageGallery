<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<div align="center">

    <p><span style="color: green;"> <c:out value="${success}"/></span></p>
    <c:forEach var="error" items="${requestScope.errors}">
        <p><span style="color: red; "><c:out value="${error}"/></span> </p>
    </c:forEach>

    <c:set var="map" value="${requestScope.data}"/>

    <form method="post" action="/add">
        Name
        <label><input type=text" name="name" value="${map['name']}"></label><br>
        Format
        <label><input type="text" name="format" value="${map['format']}"></label><br>
        Weight (mb)
        <label><input type="text" name="weight" value="${map['weight']}"></label><br>
        Tag
        <label><input type="text" name="tag" value="${map['tag']}"></label><br>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>

    <a href="/home/getAll">Back to the main page</a>
</div>




</body>
</html>

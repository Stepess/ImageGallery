<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add</title>
</head>
<body>

<c:forEach var="error" items="${requestScope.errors}">
    <p><span style="color: red; "><c:out value="${error}"/></span> </p>
</c:forEach>

<c:set var="map" value="${requestScope.data}"/>

<form method="post" action="/add">
    <label><input type=text" name="name" value="${map['name']}"></label>Name<br>
    <label><input type="text" name="format" value="${map['format']}"></label>Format<br>
    <label><input type="text" name="weight" value="${map['weight']}"></label>Weight (mb)<br>
    <label><input type="text" name="tag" value="${map['tag']}"></label>Tag<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>

<a href="/">Back to the main page</a>

<c:out value="${requestScope.imageReg}"/>

</body>
</html>

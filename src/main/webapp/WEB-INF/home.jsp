<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<c:forEach var="image" items="${requestScope.images}">
        <ol>
            <li>Name: <c:out value="${image.name}"/></li>
            <li>Format: <c:out value="${image.format}"/></li>
            <li>Weight: <c:out value="${image.weightInMb}"/></li>
            <li>Time: <c:out value="${image.timeOfLastEdit}"/></li>
            <li>Tag: <c:out value="${image.tag}"/></li>
        </ol>
    <br>
</c:forEach>

<!---<c:set var="images" value="${requestScope.images}"/>

<c:forEach var="i" begin="0" end="2">
    <p><c:out value="${images[i]}"/></p>
    <br>
</c:forEach>--->

<a href="/add">Click to add</a>
</body>
</html>

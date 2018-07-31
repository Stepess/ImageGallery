<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Search</title>
</head>
<body>

<div style="text-align: center;">
    <h1>Images Management</h1>
</div>
<div align="center">

    <c:forEach var="error" items="${requestScope.errors}">
        <p><span style="color: red; "><c:out value="${error}"/></span> </p>
    </c:forEach>

    <form method="post" action="/search">
        <p>Time range</p>
        <label><input type="datetime-local" name="leftTimeBoundary" value="1984-01-01T00:00:00"
                     min="1984-01-01T00:00:00" max="2020-02-14T00:00:00" step="1"/></label>
        <label><input type="datetime-local" name="rightTimeBoundary" value="2020-02-14T00:00:00"
                     min="1984-01-01T00:00:00" max="2020-02-14T00:00:00" step="1"/></label>
        <br>
        <p>Weight range</p>
        <br>
        <label><input type="number" name="leftWeightBoundary" value="0.0" step="0.0001"></label>
        <label><input type="number" name="rightWeightBoundary" value="100.0" step="0.0001"></label>
        <br>
        <p>Tag range</p>
        <br>
        <label><input type="text" name="tag"></label>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>
    <a href="/home">View result</a>
</div>



<!---<c:set var="images" value="${requestScope.images}"/>

<c:forEach var="i" begin="0" end="2">
    <p><c:out value="${images[i]}"/></p>
    <br>
</c:forEach>--->

</body>
</html>

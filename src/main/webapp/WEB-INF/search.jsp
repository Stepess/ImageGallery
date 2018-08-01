<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Search</title>
</head>
<body>

<div style="text-align: center;">
    <h1>Search images</h1>
    <h4><a href="/">Result</a></h4>
    <p>tip: please, enter values that you want use for search</p>
</div>
<div align="center">
    <c:forEach var="error" items="${requestScope.errors}">
        <p><span style="color: red; "><c:out value="${error}"/></span></p>
    </c:forEach>

    <form method="post" action="/search">
        <table border="1" cellpadding="5">
            <tr>
                <td><p>Time range</p></td>
                <td><input type="datetime-local" name="leftTimeBoundary" value="1984-01-01T00:00:00"
                           min="1984-01-01T00:00:00" max="2020-02-14T00:00:00" step="1"/></td>
                <td><input type="datetime-local" name="rightTimeBoundary" value="2020-02-14T00:00:00"
                           min="1984-01-01T00:00:00" max="2020-02-14T00:00:00" step="1"/></td>
            </tr>
            <tr>
                <td><p>Weight range</p></td>
                <td><input type="number" name="leftWeightBoundary" value="0.0" step="0.0001"></td>
                <td><input type="number" name="rightWeightBoundary" value="1000.0" step="0.0001"></td>
            </tr>
            <tr>
                <td><p>Tag range</p></td>
                <td><input type="text" name="tag"></td>
            </tr>
        </table>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</div>
</body>
</html>

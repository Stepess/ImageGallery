<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="/createSlideShow">
    <h2><a href="/">Back</a></h2>
    <p><span style="color: green;"> <c:out value="${success}"/></span></p>
    <c:forEach var="error" items="${requestScope.errors}">
        <p><span style="color: red; "><c:out value="${error}"/></span></p>
    </c:forEach>
    <table border="1" cellpadding="5">
        <caption><h2>List of Images</h2></caption>
        <tr>
            <th>Name</th>
            <th>Format</th>
            <th>Weight</th>
            <th>Date and time of last edit</th>
            <th>Tag</th>
            <th>Add to Slide Show</th>
        </tr>
        <c:forEach var="image" items="${requestScope.images}">
            <tr>
                <td><c:out value="${image.name}"/></td>
                <td><c:out value="${image.format}"/></td>
                <td><c:out value="${image.weightInMb}"/></td>
                <td><c:out value="${image.timeOfLastEdit}"/></td>
                <td><c:out value="${image.tag}"/></td>
                <td>
                    <input type="checkbox" name="${image.name}">
                </td>
            </tr>
        </c:forEach>
    </table>
    <table cellpadding="5">
        <tr>
            <td><p>Name</p></td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td><p>Format</p></td>
            <td>
                <select name="format">
                    <c:forEach var="format" items="${requestScope.formats}">
                        <option value="${format}"><c:out value="${format}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Create slideshow" name="Ok"><br>
</form>
</body>
</html>

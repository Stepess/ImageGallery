<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<div align="center">
    <h2>
        Add image
    </h2>
    <h4>
        <a href="/home/getAll">Back to the main page</a>
    </h4>
    <p><span style="color: green;"> <c:out value="${success}"/></span></p>
    <c:forEach var="error" items="${requestScope.errors}">
        <p><span style="color: red; "><c:out value="${error}"/></span> </p>
    </c:forEach>
    <c:set var="map" value="${requestScope.data}"/>
    <form method="post" action="/add">
        <table border="1" cellpadding="5">
            <tr>
                <td>Name</td>
                <td><input type=text" name="name" value="${map['name']}"></td>
            </tr>
            <tr>
                <td>Format</td>
                <td><select name="format">
                    <c:forEach var="format" items="${requestScope.formats}">
                        <option value="${format}"><c:out value="${format}"/></option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td>Weight (mb)</td>
                <td><input type="text" name="weight" value="${map['weight']}"></td>
            </tr>
            <tr>
                <td>Tag</td>
                <td><input type="text" name="tag" value="${map['tag']}"></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</div>
</body>
</html>

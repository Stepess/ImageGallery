<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
</head>
<body>

<div style="text-align: center;">
    <h1>Images Management</h1>
    <h2>
        <a href="/add">Add New Image</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/">At the start</a>
    </h2>
    <h3>
        <a href="/home?sort=byWeight">Sort images by weight</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/home?sort=byTime">Sort images by Date and time of last edit</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/home?sort=byTag">Sort images by Tag</a>
    </h3>
    <h3>
        <a href="/search">Search</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/home?search=cancel">Load all images</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/add">Click to add</a>
    </h3>
    <c:forEach var="error" items="${requestScope.errors}">
        <p><span style="color: red; "><c:out value="${error}"/></span> </p>
    </c:forEach>
</div>
<div align="center">
    <form method="POST" action="/home">
    <table border="1" cellpadding="5">
        <caption><h2>List of Images</h2></caption>
        <tr>
            <th>Name</th>
            <th>Format</th>
            <th>Weight</th>
            <th>Date and time of last edit</th>
            <th>Tag</th>
            <th>Actions</th>
            <th>Add to Slide Show</th>
        </tr>
        <c:forEach var="image" items="${requestScope.images}">

            <tr>
                <td><c:out value="${image.name}" /></td>
                <td><c:out value="${image.format}" /></td>
                <td><c:out value="${image.weightInMb}" /></td>
                <td><c:out value="${image.timeOfLastEdit}" /></td>
                <td><c:out value="${image.tag}" /></td>
                <td>
                    <a href="/home?delete=<c:out value='${image.name}' />">Delete</a>
                </td>
                <td>
                    <input type="checkbox" name="${image.name}" >
                </td>
            </tr>

        </c:forEach>
    </table>
        <label><input type="text" name="name">Name</label>
        <label><input type="format" name="format">Format</label>
        <input type="submit" value="Create slideshow" name="Ok"><br>
    </form>
    <br>
    <br>

    <table>
        <table border="1" cellpadding="5">
            <caption><h2>List of Slideshows</h2></caption>
            <tr>
                <th>Name</th>
                <th>Format</th>
                <th>Weight</th>
                <th>Date and time of last edit</th>
                <th>Tags</th>
                <th>Frames</th>
            </tr>
            <c:forEach var="slideshow" items="${requestScope.slideshows}">

                <tr>
                    <td><c:out value="${slideshow.name}" /></td>
                    <td><c:out value="${slideshow.format}" /></td>
                    <td><c:out value="${slideshow.weightInMb}" /></td>
                    <td><c:out value="${slideshow.timeOfLastEdit}" /></td>
                    <td><c:out value="${slideshow.tags}" /></td>
                    <td><c:out value="${slideshow.frames}" /></td>

                </tr>

            </c:forEach>

    </table>
</div>



<!---<c:set var="images" value="${requestScope.images}"/>

<c:forEach var="i" begin="0" end="2">
    <p><c:out value="${images[i]}"/></p>
    <br>
</c:forEach>--->


</body>
</html>

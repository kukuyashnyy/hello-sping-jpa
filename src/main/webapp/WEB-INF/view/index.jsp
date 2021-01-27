<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello World!</h1>
<form method="post">
    <label>Title: </label>
    <input name="title"/>
    <label>Importance: </label>
    <input name="importance"/>
    <input type="hidden" name="context" value="create">
    <input type="submit" value="Create">
</form>
<br>
<div>
    <table border="1px">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Importance</th>
            <th>Deadline</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.importance}</td>
                <td>${task.deadline}</td>
                <td>
                    <a href="/delete/${task.id}"><button>X</button></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<div>
    <p>For update:</p>
    <form method="post" >
        <label>Id:</label>
        <input name="id">
        <label>Title:</label>
        <input name="title"/>
        <label>Importance: </label>
        <input name="importance"/>
        <input type="hidden" name="context" value="update">
        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>

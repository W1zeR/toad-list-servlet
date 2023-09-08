<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--@elvariable id="toadList" type="java.util.List"--%>
<html>
<head>
    <title>Toad List</title>
    <link rel="stylesheet" href="<c:url value="/styles/toads.css"/>">
</head>
<body>
<div class="logout">
    <a href="<c:url value="/user/logout"/>">Logout</a>
</div>
<h1>Your Toad List</h1>
<div class="btn-container">
    <a href="toads/new" class="btn">Add new toad</a>
</div>
<c:if test="${empty toadList}">
    <div class="empty">It's empty!</div>
</c:if>
<c:if test="${not empty toadList}">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Weight (in grams)</th>
            <th>Length (in centimeters)</th>
            <th>Birthday</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${toadList}" var="toad">
            <tr>
                <td><c:out value="${toad.getName()}"/></td>
                <td><c:out value="${toad.getType()}"/></td>
                <td><c:out value="${toad.getWeight()}"/></td>
                <td><c:out value="${toad.getLength()}"/></td>
                <td><c:out value="${toad.getBirthday()}"/></td>
                <td><c:out value="${toad.getDescription()}"/></td>
                <td>
                    <form action="toads/edit" method="get">
                        <input type="hidden" name="idToad" value="<c:out value="${toad.getId()}"/>"><br>
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="toads/delete" method="post">
                        <input type="hidden" name="idToad" value="<c:out value="${toad.getId()}"/>"><br>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--@elvariable id="toad" type="main.com.w1zer.entity.Toad"--%>
<html>
<head>
    <title>Edit toad</title>
    <link rel="stylesheet" href="<c:url value="/styles/form.css"/>">
</head>
<body>
<div class="flex-container">
    <h1>EDIT TOAD</h1>
    <form action="<c:url value="/user/toads/edit"/>" method="post">
        <input type="hidden" name="idToad" value="<c:out value="${toad.getId()}"/>"><br>
        <label>Name:<br>
            <input type="text" name="name" maxlength="50" size="30" value="<c:out value="${toad.getName()}"/>"
                   required >
        </label>
        <label>Type:<br>
            <input type="text" name="type" maxlength="50" size="30" value="<c:out value="${toad.getType()}"/>">
        </label>
        <label>Weight (in grams):<br>
            <input type="number" name="weight" placeholder="50" max="2000" min="1"
                   value="<c:out value="${toad.getWeight()}"/>">
        </label>
        <label>Length (in centimeters):<br>
            <input type="number" name="length" placeholder="5.00" step="0.01" min="1" max="500"
                   value="<c:out value="${toad.getLength()}"/>">
        </label>
        <label>Birthday:<br>
            <input type="date" name="birthday" value="<c:out value="${toad.getBirthday()}"/>">
        </label>
        <label>Description:<br>
            <input type="text" name="description" maxlength="150" size="30"
                   value="<c:out value="${toad.getDescription()}"/>">
        </label>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>

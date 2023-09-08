<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New toad</title>
    <link rel="stylesheet" href="<c:url value="/styles/form.css"/>">
</head>
<body>
<div class="flex-container">
    <h1>NEW TOAD</h1>
    <form action="<c:url value="/user/toads/new"/>" method="post">
        <label>Name:<br>
            <input type="text" name="name" maxlength="50" size="30" required>
        </label>
        <label>Type:<br>
            <input type="text" name="type" maxlength="50" size="30">
        </label>
        <label>Weight (in grams):<br>
            <input type="number" name="weight" placeholder="50" max="2000" min="1">
        </label>
        <label>Length (in centimeters):<br>
            <input type="number" name="length" placeholder="5.00" step="0.01" min="1" max="500">
        </label>
        <label>Birthday:<br>
            <input type="date" name="birthday">
        </label>
        <label>Description:<br>
            <input type="text" name="description" maxlength="150" size="30">
        </label>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>

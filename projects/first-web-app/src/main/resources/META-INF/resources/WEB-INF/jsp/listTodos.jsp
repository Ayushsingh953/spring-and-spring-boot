<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1 style="text-align: center">Welcome ${name}!</h1>
<hr>
<h1 style="text-align: center">Your Todos :</h1>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Target Date</th>
            <th>Completed ?</th>
        </tr>
    </thead>
    <tbody>
        `<c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.completed}</td>
            </tr>
        </c:forEach>`
    </tbody>
</table>
<c:forEach items="${todos}" var="todo">

</c:forEach>
</body>
</html>
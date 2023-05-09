<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Task managing app</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/jsp/authorized_header.jsp"/>

<div class="container">
        <div class="row">
            <div class="col-3 themed-grid-col">Name</div>
            <div class="col-2 themed-grid-col">Creation</div>
            <div class="col-2 themed-grid-col">Deadline</div>
            <div class="col-2 themed-grid-col">Priority</div>
            <div class="col-3 themed-grid-col">Status</div>
        </div>
        <hr class="my-4">
        <c:forEach items = "${taskList}" var = "task">
            <div class="row mb-3">
                <div class="col-3 themed-grid-col"><a href = "/task?id=${task.id}">${task.name}</a></div>
                <div class="col-2 themed-grid-col">${task.creationDate}</div>
                <div class="col-2 themed-grid-col">${task.deadline}</div>
                <div class="col-2 themed-grid-col"><div style="color:rgb(${task.priority},0,${255 - task.priority})">&#9632; ${task.priority}</div></div>
                <div class="col-3 themed-grid-col">${task.status.name}</div>
            </div>
        </c:forEach>
</div>

</body>
</html>
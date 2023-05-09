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
        <div class="row g-3">
        <h2>${task.name}</h2>
        <div class="d-flex p-2">
            <c:forEach items="${task.tags}" var="tag">
            <span class="badge d-flex p-2 align-items-center text-bg-primary rounded-pill">
                <span class="px-1">${tag.name}</span>
                <a href="#"><svg class="bi ms-1" width="16" height="16"><use xlink:href="#x-circle-fill"></use></svg></a>
            </span>
            </c:forEach>
        </div>
        <div>
        <h5>Description</h5>
        ${task.description}
        </div>
        <div>
        Creation date : ${task.creationDate}
        </div>
        <div>
        Deadline : ${task.deadline}
        </div>
        <div>
        Status :  ${task.status.name}
        </div>
        <div class = "col-3">Priority :</div>
        <div class="col-9" style="color:rgb(${task.priority},0,${255 - task.priority})">&#9632; ${task.priority}</div>
        <div>
            Users:
            <c:forEach items="${task.taskUsers}" var="taskUser">
                <div>${taskUser.user.name}</div>
            </c:forEach>
        </div>
        <a class="w-50 btn btn-primary btn-sm" role="back-button" href="/edittask?id=${task.id}">Change</a>
    </div>

</body>
</html>
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
    <form method = "POST" action = "/edittask">
        <input type="hidden" id="id" name="id" value="${task.id}">
        <input type="hidden" id="creationDate" name="creationDate" value="${task.creationDate}">
            <div class="row g-3">
                <div class="col-sm-12">
                    <label for = "Name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name = "name" value="${task.name}"/>
                </div>
                <div class="col-sm-12">
                    <label for = "Description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name = "description" rows = 3>${task.description}</textarea>
                </div>
                <div class="col-sm-12">
                    <label for="Creation date" class="form-label">Creation date : ${task.creationDate}</label>
                </div>
                <div class="col-sm-12">
                    <label for="deadline" class="form-label">Deadline</label>
                    <input type="text" id="deadline" name="deadline" value="${task.deadline}">
                </div>
                <div class="col-6">
                    <label for="TaskStatus" class="form-label">Task Status</label>
                    <select name = "taskStatusId" class="form-select">
                        <c:forEach items="${taskStatusList}" var="taskStatus">
                            <option value="${taskStatus.id}"><c:out value="${taskStatus.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-6">
                    <label for="Priority" class="form-label">Priority(from 0 to 255)</label>
                    <input type="number" class="form-control" id="priority" name="priority" min="0" max="255" value="${task.priority}">
                </div>
                <div class="col-3">
                    <button class="w-100 btn btn-primary btn-sm" type="submit">Update</button>
                </div>
                <div class="col-6">
                    <a class="w-50 btn btn-secondary btn-sm" role="back-button" href="/task?id=${task.id}">Back</a>
                </div>
            </div>
    </form>
</div>

</body>
</html>
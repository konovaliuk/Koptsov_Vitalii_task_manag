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
<c:if test="${empty sessionScope.User}">
  <jsp:include page="/jsp/unauthorized_header.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.User}">
  <jsp:include page="/jsp/authorized_header.jsp"/>
</c:if>
<br>
<div class="container">
        <form method = "POST" action = "/login">
            <div class="row g-3">
                <div class="col-sm-12">
                    <label for="login" class="form-label">Login</label>
                    <input type="text" class="form-control" id="login" name = "login" placeholder="Login">
                </div>
                <div class="col-sm-12">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name = "password" placeholder="Password">
                </div>
                <hr class="my-4">
                <button class="w-100 btn btn-primary btn-lg">Login</button>
            </div>
        </form>
        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger mt-3" role="alert">
                <c:out value="${requestScope.error}"/>
            </div>
        </c:if>
    </div>
</body>
</html>
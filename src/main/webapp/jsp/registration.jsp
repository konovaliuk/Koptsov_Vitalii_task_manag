<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
<br>
    <div class="container">
        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger mt-3" role="alert">
                <c:out value="${requestScope.error}"/>
            </div>
        </c:if>
        <form method = "POST" action = "/registration" novalidate>
            <div class="row g-3">
                <div class="col-sm-4">
                    <label for="FirstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name = "firstName" placeholder="First Name">
                </div>
                <div class="col-sm-4">
                    <label for="SecondName" class="form-label">Second Name</label>
                    <input type="text" class="form-control" id="lastName" name = "lastName" placeholder="Second Name">
                </div>
                <div class="col-sm-4">
                    <label for="MiddleName" class="form-label">Middle Name</label>
                    <input type="text" class="form-control" id="middleName" name = "middleName" placeholder="Middle Name">
                </div>
                <div class="col-sm-6">
                    <label for="Group" class="form-label">Group</label>
                    <input type="text" class="form-control" id="group" name = "group" placeholder="XX-00">
                </div>
                <div class="col-sm-6">
                    <label for="Faculty" class="form-label">Faculty</label>
                    <input type="text" class="form-control" id="faculty" name = "faculty" placeholder="ФІОТ">
                </div>
                <div class="col-sm-12">
                    <label for="TelegramTag" class="form-label">TelegramTag</label>
                    <input type="text" class="form-control" id="telegramTag" name = "telegramTag" placeholder="@jiji">
                </div>
                <div class="col-sm-12">
                    <label for="Email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name = "email" placeholder="example@example.com">
                </div>
                <div class="col-sm-12">
                    <label for="Phone" class="form-label">Phone number</label>
                    <input type="tel" class="form-control" id="phoneNumber" name = "phoneNumber" placeholder="xxxxxxxxxx" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" >
                </div>
                <div class="col-sm-6">
                    <label for="birthday" class="form-label">Birthday date</label>
                    <input type="date" class="form-control" id="birthday" name = "birthday">
                </div>
                <div class="col-sm-6">
                    <label for="admissionDay" class="form-label">Addmision date</label>
                    <input type="date" class="form-control" id="admissionDay" name = "admissionDay">
                </div>
                <hr class="my-4">
                <div class="col-sm-12">
                    <label for="Role" class="form-label">Role</label>
                    <select name = "roleId" class="form-select">
                        <c:forEach items="${roleList}" var="role">
                            <option value="${role.id}"><c:out value="${role.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-12">
                    <label for="login" class="form-label">Login</label>
                    <input type="text" class="form-control" id="login" name = "login" placeholder="Login">
                </div>
                <div class="col-sm-12">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name = "password" placeholder="Password">
                </div>
                <div class="col-sm-12">
                    <label for="passwordConfirmation" class="form-label">Confirm password</label>
                    <input type="password" class="form-control" id="passwordConfirmation" name = "passwordConfirmation" placeholder="Confirm password">
                </div>
                <hr class="my-4">
                <button class="w-100 btn btn-primary btn-lg" type="submit">Register</button>
            </div>
        </form>
    </div>
</body>
</html>
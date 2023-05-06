<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Task managing app</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${empty sessionScope.user}">
  <jsp:include page="/jsp/unauthorized_header.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.user}">
  <jsp:include page="/jsp/authorized_header.jsp"/>
</c:if>

<div class="container">
  <div class="jumbotron text-center">
    <h1>Welcome to the best Beauty Salon in Kyiv!</h1>
    <p>This is a simple example of a JSP page with a navbar.</p>
  </div>
</div>

</body>
</html>
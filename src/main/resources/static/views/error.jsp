<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import = "com.example.capstone.login.model.UserModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error Page</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css" />

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

  <script src="${pageContext.request.contextPath}/javascript/min-theme.js"></script>
</head>

<body>
<nav>
  <div class="nav-content">
    <div class="nav-img">
      <img src="<c:url value='/images/logo.svg' />" />
      <span class="random">BLURB</span>
    </div>
    <div class="nav-login">
      <a href="<c:url value='/login' />"><button class="nav-login-login" id="toLogin">Login</button></a>
      <a href="<c:url value='/signup' />"><button class="nav-login-register" id="toSignup">Register</button></a>
    </div>
    <div id="nav-dropdown" class="nav-dropdown">
      <img id="hamburger" class="hamburger" src="<c:url value='/images/logo.svg' />" onclick="hamburgers()">
    </div>
  </div>
</nav>

<div class="main">
  <h1>BLURB</h1>
  <p>
    You are a potato - an error has occurred.
    <c:out value="${requestScope['javax.servlet.error.status_code']}" />
  </p>
</div>


<footer>
  <div class="footer-content">
    <div class="footer-image">
      <img src="<c:url value='/images/logo.svg' />" alt="Logo" />
      <span class="random">BLURB</span>
    </div>
    <p class="footer-copyright">
      Copyright of Cool Dudes &copy;2024. All Rights Reserved.
    </p>
  </div>
</footer>
</body>

</html>

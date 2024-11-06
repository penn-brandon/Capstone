<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">
</head>

<body>
<nav>
    <div class="nav-content">
        <a href="${pageContext.request.contextPath}">
            <div class="nav-img">
                <img src="${pageContext.request.contextPath}/images/logo.svg" alt="Logo"/>
                <span class="nav-logo">BLURB</span>
            </div>
        </a>
    </div>
</nav>

<div class="main">
    <h1>BLURB</h1>
    <p>
        You are a potato - an error has occurred.
        <c:out value="${requestScope['javax.servlet.error.status_code']}"/>
    </p>
</div>


<footer>
    <div class="footer-div">
        <p class="footer-copyright">
            Copyright of Cool Dudes &copy;2024. All Rights Reserved.
        </p>
    </div>
</footer>
</body>

</html>

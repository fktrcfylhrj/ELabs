<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<body>
	<h1>${topic}</h1>
    <h2>${message}</h2>
    <c:if test="${where == \"main\"}">
        <a href="main.html">Back</a>
    </c:if>
    <c:if test="${where == \"tour\"}">
        <a href="tourList.html">Back</a>
    </c:if>
    <c:if test="${where == \"users\"}">
        <a href="list.html">Back</a>
    </c:if>
</body>
</html>

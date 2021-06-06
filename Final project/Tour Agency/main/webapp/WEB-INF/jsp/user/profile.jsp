<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}"><jsp:useBean id="user" class="domain.User"/></c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Your profile</title>
    </head>
    <body>
        <h1>Your profile - ${user.login}</h1>
        <h3>Your role - ${role}</h3>
        <form action="profileSave.html" method="post">

        <label for="login">login:</label>
        <input id="login" name="login" value="${user.login}">

        <c:if test="${role == \"travelAgent\"}">
            <label for="surnameAndName">Surname and name:</label>
            <input id="surnameAndName" name="surnameAndName" value="${agent.surnameAndName}">
        </c:if>
        <c:if test="${role == \"customer\"}">
            <label for="surname">Surname:</label>
            <input id="surname" name="surname" value="${cus.surname}">
            <label for="name">Name:</label>
            <input id="name" name="name" value="${cus.name}">
            <label for="patronymic">Patronymic:</label>
            <input id="patronymic" name="patronymic" value="${cus.patronymic}">
        </c:if>
        <br><br>
        <button class="save">Save</button><br><br>

        <button class="password-reset" formaction="reset.html" formmethod="post">Reset the password</button>
        </form>
        <br><br>
        <a href="main.html">Back</a>
    </body>
</html>
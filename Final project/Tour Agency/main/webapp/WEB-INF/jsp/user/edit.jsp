<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}"><jsp:useBean id="user" class="domain.User"/></c:if>
<c:choose>
    <c:when test="${not empty user.id}"><c:set var="title" value="Edit user data "/></c:when>
    <c:otherwise><c:set var="title" value="Add a new user "/></c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
<!--        <link href="../main.css" rel="stylesheet">  -->
    </head>
    <body>
        <h1>Edit new user</h1>
        <h2>${title}</h2>
        <form action="save.html" method="post">
            <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
            </c:if>
            <label for="login">Name:</label>
            <input id="login" name="login" value="${user.login}">
            <label for="role">Role:</label>
            <select id="role" name="role">
                <c:forEach var="role" items="${roles}">
                    <c:choose>
                        <c:when test="${role == user.role}"><c:set var="selected" value="selected"/></c:when>
                        <c:otherwise><c:remove var="selected"/></c:otherwise>
                    </c:choose>
                    <option value="${role.id}" ${selected}>${role.name}</option>
                </c:forEach>
            </select>
            <button class="save">Save</button>

            <c:if test="${not empty user.id}">
                <c:if test="${not userCanBeDeleted}"><c:set var="disabled" value="disabled"/></c:if>
            <button class="delete" formaction="delete.html" formmethod="post" ${disabled}>Delete</button>

            </c:if>
            <a class="back" href="list.html">Cancel</a>
        </form>
    </body>
</html>
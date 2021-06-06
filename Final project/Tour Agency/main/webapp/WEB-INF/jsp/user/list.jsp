<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list of users</title>

</head>

<body>
	<h1>Tour Agency</h1>
        <h2>List of users</h2>
        <table>
            <tr>
                <th>Username</th>
                <th>Role</th>
                <td>&nbsp;</td>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td class="content">${user.login}</td>
                <td class="content">${user.role}</td>
                <td><a href="edit.html?id=${user.id}">edit</a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="edit.html" class="add-button">Add</a>
        <a href="main.html" class="back-to-index">cancel</a>
</body>
</html>

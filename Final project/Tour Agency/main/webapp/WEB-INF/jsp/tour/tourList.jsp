<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list of tours</title>

</head>

<body>
	<h1>Tour Agency</h1>
        <h2>List of tours</h2>
        <table>
            <tr>
                <th>The date of the beginning </th>
                <th>End date</th>
                <th>Burning</th>
                <th>Customer</th>
                <th>Agent</th>
                <th>Recreation cost</th>
                <th>Excursion cost</th>
                <th>Shopping cost</th>
                <th>Other expenses</th>
                <td>&nbsp;</td>
            </tr>
            <c:forEach var="tour" items="${tours}">
            <tr>
                <td class="content">${tour.dateStart}</td>
                <td class="content">${tour.dateEnd}</td>
                <!-- подправить вывод для burning -->
                <c:choose>
                    <c:when test="${tour.burning == true}">
                         <td class="content">yes</td>
                    </c:when>
                    <c:otherwise>
                        <td class="content">no</td>
                    </c:otherwise>
                </c:choose>
                <td class="content">${tour.customer.name}</td>
                <td class="content">${tour.travelAgent.surnameAndName}</td>
                <td class="content">${tour.recreationCost}</td>
                <td class="content">${tour.excursionCost}</td>
                <td class="content">${tour.shoppingCost}</td>
                <td class="content">${tour.otherExpenses}</td>
                <td><a href="editTour.html?id=${tour.id}">edit</a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="editTour.html" class="add-button">Add</a>
        <a href="main.html" class="back-to-index">cancel</a>
</body>
</html>

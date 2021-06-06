<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty tour}"><jsp:useBean id="tour" class="domain.Tour"/></c:if>
<c:choose>
    <c:when test="${not empty tour.id}"><c:set var="title" value="Edit tour data "/></c:when>
    <c:otherwise><c:set var="title" value="Add a new tour "/></c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>

    </head>
    <body>

        <h1>${title}</h1>
        <form action="saveTour.html" method="post">
            <c:if test="${not empty tour.id}">
            <input name="id" value="${tour.id}" type="hidden">
            </c:if>
            <label for="dateStart">The date of the beginning:</label>
            <input id="dateStart" name="dateStart" value="${tour.dateStart}">

            <label for="dateEnd">End date:</label>
            <input id="dateEnd" name="dateEnd" value="${tour.dateEnd}">

            <c:choose>
                <c:when test="${tour.burning == true}">
                    <label for="burning">Burning</label>
					<input type="checkbox" id="burning" name="burning" checked/>
                </c:when>
                <c:otherwise>
					<label for="burning">Burning</label>
                    <input type="checkbox" id="burning" name="burning" />
                </c:otherwise>
            </c:choose>
			<br>
            <label for="cusName">customer name:</label>
            <input id="cusName" name="cusName" value="${tour.customer.name}">
			<br>
            <label for="agentName">agent name:</label>
            <input id="agentName" name="agentName" value="${tour.travelAgent.surnameAndName}">
			<br>
            <label for="recCost">Recreation cost:</label>
            <input id="recCost" name="recCost" value="${tour.recreationCost}">
			<br>
            <label for="excCost">Excursion cost:</label>
            <input id="excCost" name="excCost" value="${tour.excursionCost}">
			<br>
            <label for="shopCost">Shopping  cost:</label>
            <input id="shopCost" name="shopCost" value="${tour.shoppingCost}">
			<br>
            <label for="otherCost">Other expenses:</label>
             <input id="otherCost" name="otherCost" value="${tour.otherExpenses}">

			<br>
            <button class="save">Save</button>

			<c:if test="${not empty tour.id}">
                <button class="delete" formaction="tourDelete.html" formmethod="post">Delete</button>
            </c:if>

            <a class="back" href="tourList.html">Cancel</a>
        </form>
    </body>
</html>
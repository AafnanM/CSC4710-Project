<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>Birthday</th>
                <th>Role</th>
                <th>Card number</th>
                <th>Card expiration date</th>
                <th>Card security code</th>
                <th>Picture 1</th>
                <th>Picture 2</th>
                <th>Picture 3</th>
                <th>Tree size</th>
                <th>Tree height</th>
                <th>Tree location</th>
                <th>How near</th>
                <th>Client's note</th>
                <th>David's quote response</th>
                <th>David's note</th>
                <th>Client's quote response</th>
                <th>Quote price</th>
                <th>Work start date</th>
                <th>Work end date</th>
                <th>Bill cost</th>
                <th>Bill status</th>
                <th>Bill issue date</th>
                <th>Bill payment date</th>
                <th>Order completion date</th>
                <th>Tree cut dates</th>
                <th>Trees cut</th>
                <th>Total trees cut</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value="${users.role}" /></td>
                    <td><c:out value="${users.cardNumber}" /></td>
                    <td><c:out value="${users.cardExpiration}" /></td>
                    <td><c:out value="${users.cardSecurityCode}" /></td>
                    <td><c:out value="${users.pic1}" /></td>
                    <td><c:out value="${users.pic2}" /></td>
                    <td><c:out value="${users.pic3}" /></td>
                    <td><c:out value="${users.treeSize}" /></td>
                    <td><c:out value="${users.treeHeight}" /></td>
                    <td><c:out value="${users.location}" /></td>
                    <td><c:out value="${users.howNear}" /></td>
                    <td><c:out value="${users.clientNote}" /></td>
                    <td><c:out value="${users.quoteDavidAccept}" /></td>
                    <td><c:out value="${users.davidNote}" /></td>
                    <td><c:out value="${users.quoteClientAccept}" /></td>
                    <td><c:out value="${users.price}" /></td>
                    <td><c:out value="${users.workStart}" /></td>
                    <td><c:out value="${users.workEnd}" /></td>
                    <td><c:out value="${users.billCost}" /></td>
                    <td><c:out value="${users.billStatus}" /></td>
                    <td><c:out value="${users.billGiven}" /></td>
                    <td><c:out value="${users.billPaid}" /></td>
                    <td><c:out value="${users.orderCompleted}" /></td>
                    <td><c:out value="${users.treeCutDates}" /></td>
                    <td><c:out value="${users.treesCut}" /></td>
                    <td><c:out value="${users.totalTreesCut}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>
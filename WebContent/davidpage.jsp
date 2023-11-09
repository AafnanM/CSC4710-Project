<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith page</title>
</head>

	<center><h1>Welcome! You have been successfully logged in as David Smith</h1> </center>

 
	<body>
		<div align="center">
        	<table border="1" cellpadding="6">
            	<caption><h2>List of Quotes</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Picture 1</th>
	                <th>Picture 2</th>
	                <th>Picture 3</th>
	                <th>Tree size</th>
	                <th>Tree height</th>
	                <th>Tree location</th>
	                <th>How near</th>
	                <th>Client's note</th>
	                <th>Quote status</th>
	                <th>David's note</th>
	                <th>Price</th>
            	</tr>
            	<c:forEach var="users" items="${listQuote}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.pic1}" /></td>
	                    <td><c:out value="${users.pic2}" /></td>
	                    <td><c:out value="${users.pic3}" /></td>
	                    <td><c:out value="${users.treeSize}" /></td>
	                    <td><c:out value="${users.treeHeight}" /></td>
	                    <td><c:out value="${users.location}" /></td>
	                    <td><c:out value="${users.howNear}" /></td>
	                    <td><c:out value="${users.clientNote}" /></td>
	                    <td><c:out value="${users.accepted}" /></td>
	                    <td><c:out value="${users.davidNote}" /></td>
	                    <td><c:out value="${users.price}" /></td>
	            </c:forEach>
        	</table>
        	<br>
        	<p> ${errorOne } </p>
        	<form action="david">
        		<table>
        			<caption><h2>Quote Response</h2></caption>
        			<tr>
						<th>Enter email of client: </th>
						<td align="center" colspan="3">
							<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
						</td>
					</tr>
        			<tr>
						<th>Accept/Decline Quote: </th>
						<td align="center" colspan="3">
							<input type="text" name="accepted" size="45"  value="Accepted/Declined" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Price if accepted: </th>
						<td align="center" colspan="3">
							<input type="text" name="price" size="45"  value="Enter price" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Additional notes: </th>
						<td align="center" colspan="3">
							<textarea id="text" rows="4" cols="43" name="davidNote" placeholder="Enter any additional information for the client" onfocus="this.value=''"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Submit"/>
						</td>
					</tr>
        		</table>
        		<a href="login.jsp" target="_self">Return to Login Page</a>
        	</form>
        </div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith page</title>
</head>

	<center>
		<h1>Welcome! You have been successfully logged in as David Smith</h1>
		<a href="login.jsp" target="_self">Return to Login Page</a>
	</center>
	<br>
 
	<body>
		<div align="center">
		
			<!-- QUOTE DASHBOARD -->
		
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
	                <th>No. of trees</th>
	                <th>Tree location</th>
	                <th>How near</th>
	                <th>Client's response</th>
	                <th>Client's note</th>
	                <th>David's response</th>
	                <th>David's note</th>
	                <th>Price</th>
	                <th>Work start date</th>
	                <th>Work finish date</th>
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
	                    <td><c:out value="${users.treesCut}" /></td>
	                    <td><c:out value="${users.location}" /></td>
	                    <td><c:out value="${users.howNear}" /></td>
	                    <td><c:out value="${users.quoteClientAccept}" /></td>
	                    <td><c:out value="${users.clientNote}" /></td>
	                    <td><c:out value="${users.quoteDavidAccept}" /></td>
	                    <td><c:out value="${users.davidNote}" /></td>
	                    <td><c:out value="${users.price}" /></td>
	                    <td><c:out value="${users.workStart}" /></td>
	                    <td><c:out value="${users.workEnd}" /></td>
	            </c:forEach>
        	</table>
        	<br>
        	<p> ${errorOne } </p>
        	<!-- RESPOND TO A QUOTE -->
        	<form action="davidQuoteRespond">
        		<table>
        			<caption><h3>Quote Dashboard</h3></caption>
        			<tr>
						<th>Enter email of client: </th>
						<td align="center" colspan="3">
							<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
						</td>
					</tr>
        			<tr>
						<th>Accept/Decline Quote: </th>
						<td align="center" colspan="3">
							<input type="text" name="quoteDavidAccept" size="45"  value="Accepted/Declined" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Price if accepted ($): </th>
						<td align="center" colspan="3">
							<input type="text" name="price" size="45"  value="Enter price" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>When work will be started: </th>
						<td align="center" colspan="3">
							<input type="text" name="workStart" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>When work will be finished: </th>
						<td align="center" colspan="3">
							<input type="text" name="workEnd" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
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
        	</form>
        	<!-- CANCEL A QUOTE -->
        	<form action="davidQuoteCancel">
        		<table>
        			<caption><h3>Cancel a Quote</h3></caption>
        			<tr>
						<th>Enter email of client: </th>
						<td align="center" colspan="3">
							<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Submit"/>
						</td>
					</tr>
        		</table>
        	</form>
        	
        	<!-- BILL DASHBOARD -->
        	
        	<br>
        	<table border="1" cellpadding="6">
            	<caption><h2>Bill Dashboard</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Credit Card Number</th>
	                <th>Card Expiration Date</th>
	                <th>Card Security Code</th>
	                <th>Quote Price</th>                
	                <th>Bill Cost</th>
	                <th>Bill Status</th>
	                <th>Bill Issue Date</th>
	                <th>Bill Payment Date</th>
	                <th>Client's note</th>
	                <th>David's note</th>
            	</tr>
            	<c:forEach var="users" items="${listBill}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.cardNumber}" /></td>
	                    <td><c:out value="${users.cardExpiration}" /></td>
	                    <td><c:out value="${users.cardSecurityCode}" /></td>
	                    <td><c:out value="${users.price}" /></td>
	                    <td><c:out value="${users.billCost}" /></td>
	                    <td><c:out value="${users.billStatus}" /></td>
	                    <td><c:out value="${users.billGiven}" /></td>
	                    <td><c:out value="${users.billPaid}" /></td>
	                    <td><c:out value="${users.clientNote}" /></td>
	                    <td><c:out value="${users.davidNote}" /></td>
	            </c:forEach>
        	</table>        	
        	<br>
        	<p> ${errorTwo } </p>
        	<!-- ISSUE A BILL -->
        	<form action="davidBillSubmit">
        		<table>
        			<caption><h3>Issue a Bill</h3></caption>
        			<tr>
						<th>Enter email of client: </th>
						<td align="center" colspan="3">
							<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Enter bill cost ($): </th>
						<td align="center" colspan="3">
							<input type="text" name="billCost" size="45"  value="Enter amount in $" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Enter bill issue date: </th>
						<td align="center" colspan="3">
							<input type="text" name="billGiven" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
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
        	</form>
        	
        	<!-- ORDER DASHBOARD -->
        	
        	<br>
	 		<table border="1" cellpadding="6">
            	<caption><h2>Order Dashboard</h2></caption>
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
	                <th>Projected work start</th>
	                <th>Projected work finish</th>
	                <th>Order status</th>
	                <th>Tree cut dates</th>
            	</tr>
            	<c:forEach var="users" items="${listOrder}">
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
	                    <td><c:out value="${users.workStart}" /></td>
	                    <td><c:out value="${users.workEnd}" /></td>
	                    <td><c:out value="${users.orderCompleted}" /></td>
	                    <td><c:out value="${users.treeCutDates}" /></td>
	            </c:forEach>
        	</table>
        	<br>
        	<p> ${errorThree } </p>
        	<!-- COMPLETE AN ORDER -->
        	<form action="davidOrderComplete">
        		<table>
        			<caption><h3>Complete an Order</h3></caption>
        			<tr>
						<th>Enter email of client: </th>
						<td align="center" colspan="3">
							<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Tree cut dates: </th>
						<td align="center" colspan="3">
							<textarea id="text" rows="4" cols="43" name="treeCutDates" placeholder="Enter when each tree was cut" onfocus="this.value=''"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Submit"/>
						</td>
					</tr>
        		</table>
        	</form>
        	
        	<!-- REVENUE REPORT -->
        	
        	<br>
	 		<table border="1" cellpadding="6">
            	<caption><h2>Revenue Report</h2></caption>
            	<tr>
	                <th>Bill payment date</th>
	                <th>Bill cost</th>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Projected work start</th>
	                <th>Projected work finish</th>
	                <th>Order status</th>
            	</tr>
            	<c:forEach var="users" items="${listRevenue}">
	                <tr style="text-align:center">
	                	<td><c:out value="${users.billPaid}" /></td>
	                	<td><c:out value="${users.billCost}" /></td>
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.workStart}" /></td>
	                    <td><c:out value="${users.workEnd}" /></td>
	                    <td><c:out value="${users.orderCompleted}" /></td>
	            </c:forEach>
        	</table>
        	<br>
        	<!-- Enter dates for bill payment -->
        	<form action="davidRevenueReport">
        		<table>
        			<caption>Enter Revenue Period</caption>
        			<tr>
						<th>Start date: </th>
						<td align="center" colspan="3">
							<input type="text" name="revenueStart" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>End date: </th>
						<td align="center" colspan="3">
							<input type="text" name="revenueEnd" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Generate Revenue Report"/>
						</td>
					</tr>
        		</table>
        	</form>
        </div>
	</body>
</html>
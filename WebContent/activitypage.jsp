<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client page</title>
</head>

	<center>
		<h1>Welcome! You have been successfully logged in</h1>
		<a href="login.jsp" target="_self">logout</a>
	</center>
 
 	<br>
	<body>
	 	<div align="center">
	 	
	 		<!-- NEW QUOTE SUBMISSION -->
	 	
	 		<form action="clientQuoteSubmit">
				<table border="1" cellpadding="5">
					<caption><h2>Submit a New Quote</h2></caption>
					<tr>
						<th>Picture 1 (URL): </th>
						<td align="center" colspan="3">
							<input type="text" name="pic1" size="45"  value="link for first picture" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Picture 2 (URL): </th>
						<td align="center" colspan="3">
							<input type="text" name="pic2" size="45"  value="link for second picture" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Picture 3 (URL): </th>
						<td align="center" colspan="3">
							<input type="text" name="pic3" size="45"  value="link for third picture" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Tree size: </th>
						<td align="center" colspan="3">
							<input type="text" name="treeSize" size="45"  value="Size of tree" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Tree height: </th>
						<td align="center" colspan="3">
							<input type="text" name="treeHeight" size="45"  value="Height of tree" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>No. of trees: </th>
						<td align="center" colspan="3">
							<input type="text" name="treesCut" size="45"  value="Number of trees" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Location: </th>
						<td align="center" colspan="3">
							<input type="text" name="location" size="45"  value="Enter location" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>How near to location: </th>
						<td align="center" colspan="3">
							<input type="text" name="howNear" size="45"  value="Enter distance" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<th>Additional notes: </th>
						<td align="center" colspan="3">
							<textarea id="text" rows="4" cols="43" name="clientNote" placeholder="Enter any additional information for David" onfocus="this.value=''"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Submit"/>
						</td>
					</tr>
				</table>
	 		</form>
	 		
	 		<!-- VIEW AND NEGOTIATE EXISTING QUOTE -->
	 		
	 		<br>
	 		<table border="1" cellpadding="6">
            	<caption><h2>Your Quote</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Picture 1</th>
	                <th>Picture 2</th>
	                <th>Picture 3</th>
	                <th>Tree size</th>
	                <th>Tree height (m)</th>
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
        	<!-- QUOTE OPTIONS -->
        	<form action="clientQuoteAccept">
        		<table>
					<tr>
						<th>Click to accept the quote: </th>
						<td align="center" colspan="5">
							<input type="submit" value="Accept Quote"/>
						</td>
					</tr>
        		</table>
        	</form>
        	<form action="clientQuoteCancel">
        		<table>
					<tr>
						<th>Click to cancel your quote: </th>
						<td align="center" colspan="5">
							<input type="submit" value="Cancel Quote"/>
						</td>
					</tr>
        		</table>
        	</form>
        	<form action="clientQuoteNegotiate">
        		<table>
        			<caption><h3>Negotiate Quote</h3></caption>
        			<tr>
						<th>Price ($): </th>
						<td align="center" colspan="3">
							<input type="text" name="price" size="45"  value="Enter a price" onfocus="this.value=''">
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
							<textarea id="text" rows="4" cols="43" name="clientNote" placeholder="Enter any additional information for David" onfocus="this.value=''"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Submit"/>
						</td>
					</tr>
        		</table>
        	</form>
	 		
	 		<!-- VIEW AND RESPOND TO BILL -->
	 		
	 		<br>
        	<table border="1" cellpadding="6">
            	<caption><h2>Your Bill</h2></caption>
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
        	<!-- BILL OPTIONS -->
        	<form action="clientBillAccept">
        		<table>
        			<caption><h3>Pay Bill</h3></caption>
        			<tr>
						<th>Date of payment: </th>
						<td align="center" colspan="3">
							<input type="text" name="billPaid" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Pay Using Card"/>
						</td>
					</tr>
        		</table>
        	</form>
        	<form action="clientBillDecline">
        		<table>
        			<caption><h3>Decline Bill</h3></caption>
        			<tr>
						<th>Additional notes: </th>
						<td align="center" colspan="3">
							<textarea id="text" rows="4" cols="43" name="clientNote" placeholder="Enter any additional information for David" onfocus="this.value=''"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="5">
							<input type="submit" value="Submit"/>
						</td>
					</tr>
				</table>
			</form>
			
			<!-- VIEW ORDER STATUS -->
			
			<br>
	 		<table border="1" cellpadding="6">
            	<caption><h2>Your Order</h2></caption>
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
        	<br><br>
	 	</div>
	</body>
</html>
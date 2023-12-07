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

	<h1>Welcome to The Root Dashboard</h1><br>
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 
	
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Basic User Information</h2></caption>
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
            </c:forEach>
        </table>
        <table border="1" cellpadding="6">
            <caption><h2>Quote Information</h2></caption>
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
                <th>David's quote response</th>
                <th>David's note</th>
                <th>Client's quote response</th>
                <th>Quote price</th>
                <th>Work start date</th>
                <th>Work end date</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
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
                    <td><c:out value="${users.quoteDavidAccept}" /></td>
                    <td><c:out value="${users.davidNote}" /></td>
                    <td><c:out value="${users.quoteClientAccept}" /></td>
                    <td><c:out value="${users.price}" /></td>
                    <td><c:out value="${users.workStart}" /></td>
                    <td><c:out value="${users.workEnd}" /></td>
            </c:forEach>
        </table>
        <table border="1" cellpadding="6">
            <caption><h2>Bill and Order Information</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
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
        <br>
        
        <!-- STATISTICS -->
        <table border="1" cellpadding="6">
            	<caption><h2>Statistics</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>No. of trees cut</th>
	                <th>Amount due</th>
	                <th>Amount paid</th>
	                <th>Completion date for trees</th>
            	</tr>
            	<c:forEach var="users" items="${listUser}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.totalTreesCut}" /></td>
	                    <td><c:out value="${users.amountDue}" /></td>
	                    <td><c:out value="${users.amountPaid}" /></td>
	                    <td><c:out value="${users.treeCutDates}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        
        <!-- MOST TREES CUT -->
        <table border="1" cellpadding="6">
            	<caption><h2>Most Trees Cut</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
            	</tr>
            	<c:forEach var="users" items="${listMostTreesCut}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- HIGHEST TREE CUT -->
        <table border="1" cellpadding="6">
            	<caption><h2>Highest Tree Cut</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Tree Height</th>
            	</tr>
            	<c:forEach var="users" items="${listHighestTreeCut}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.treeHeight}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- EASY CLIENTS -->
        <table border="1" cellpadding="6">
            	<caption><h2>Easy Clients</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
            	</tr>
            	<c:forEach var="users" items="${listEasyClients}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- ONE TREE QUOTES -->
        <table border="1" cellpadding="6">
            	<caption><h2>One Tree Quotes</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
            	</tr>
            	<c:forEach var="users" items="${listOneTreeQuotes}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- PROSPECTIVE CLIENTS -->
        <table border="1" cellpadding="6">
            	<caption><h2>Prospective Clients</h2></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
            	</tr>
            	<c:forEach var="users" items="${listProspectiveClients}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- ENTER DATE FOR FOLLOWING FIELDS -->
        <form action="rootDate">
        	<table>
        		<caption>
        			<h2>Overdue Bills / Bad Clients / Good Clients</h2>
        			<h3>Enter Today's Date</h3>
        		</caption>
        		<tr>
					<th>Today's date: </th>
					<td align="center" colspan="3">
						<input type="text" name="rootDate" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Submit"/>
					</td>
				</tr>
        	</table>
        </form>
        
        <!-- OVERDUE BILLS -->
        <table border="1" cellpadding="6">
            	<caption><h3>Overdue Bills</h3></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Bill issue date</th>
	                <th>Bill payment date</th>
            	</tr>
            	<c:forEach var="users" items="${listOverdueBills}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.billGiven}" /></td>
	                    <td><c:out value="${users.billPaid}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- BAD CLIENTS -->
        <table border="1" cellpadding="6">
            	<caption><h3>Bad Clients</h3></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Bill issue date</th>
	                <th>Bill payment date</th>
            	</tr>
            	<c:forEach var="users" items="${listBadClients}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.billGiven}" /></td>
	                    <td><c:out value="${users.billPaid}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br>
        
        <!-- GOOD CLIENTS -->
        <table border="1" cellpadding="6">
            	<caption><h3>Good Clients</h3></caption>
            	<tr>
	                <th>Email</th>
	                <th>First name</th>
	                <th>Last name</th>
	                <th>Bill issue date</th>
	                <th>Bill payment date</th>
            	</tr>
            	<c:forEach var="users" items="${listGoodClients}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.email}" /></td>
	                    <td><c:out value="${users.firstName}" /></td>
	                    <td><c:out value="${users.lastName}" /></td>
	                    <td><c:out value="${users.billGiven}" /></td>
	                    <td><c:out value="${users.billPaid}" /></td>
	            	</tr>
	            </c:forEach>
        </table>
        <br><br>
	</div>
</div>

</body>
</html>
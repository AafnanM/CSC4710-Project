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
	</center>
 
	<body>
	 	<div align="center">
	 		<form action="client">
				<table border="1" cellpadding="5">
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
				<a href="login.jsp" target="_self">logout</a>
	 		</form>
	 	</div>
	</body>
</html>
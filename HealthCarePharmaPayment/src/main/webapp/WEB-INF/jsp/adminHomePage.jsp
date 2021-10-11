<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
	integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
	integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<style>
body {
	text-align: center;
	align: center;
	background: lavender;
}

.link {
	margin-top: 30px;
	margin-bottom: 30px;
}

td {
	font-weight: bold;
}

.row {
	margin-top: 50%;
	margin-bottom: 50%;
}

header {
	height: px;
    background: #ffffff;
}

table {
	display: inline-block;
	background: #F5F5DC;
	padding-right: 50px;
	padding-left: 50px;
}

a {
	color: black;
	font-weight: bold;
	text-decoration: none;
}

.space {

	padding-left: 10px;
	padding-right: 10px;
}

h3 {
	padding-top: 0.5em;
}

.alert {
	padding: 10px;
	border-color: red;
	background-color: #EEE8AA;
	color: red;
	width: 30%;
	margin: auto;
	margin-bottom: 5px;
}

.closebtn {
	margin-left: 15px;
	color: red;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}
</style>



<title>Admin Home Page</title>
</head>
<body>
	<header>
		<nav class="site-nav">
			<ul class="nav justify-content-center navbar-nav navbar-right">

				<ul class="nav nav-tabs justify-content-end space"
					style="padding-right: 7em">

					<li class="nav-item"><a class="nav-link" href="/home">Logout</a></li>

				</ul>
			</ul>
		</nav>
	</header>
	<h3>Hello ${username}, Welcome to admin home Page.</h3>
	<br>

	<c:set var="salary" scope="session" value="${value}" />
	<c:if test="${salary > 10}">
		<c:forEach var="item" items="${notification}">
			<div class="alert">
				<span class="closebtn"
					onclick="this.parentElement.style.display='none';">&times;</span> <strong>Alert
					: ${item}!</strong>
			</div>
		</c:forEach>
	</c:if>



	<table>
		<tr>
			<th></th>
			<td><a href="/medicineInformation" class="btn btn-success link">Medicine
					Information</a></td>
		</tr>
		<tr>
			<th></th>
			<td><a href="/updateStock" class="btn btn-success link"> Update
					Stock</a></td>
		</tr>
		<tr>
			<th></th>
			<td><a href="/viewStock" class="btn btn-success link"> View
					Stock </a></td>
		</tr>
		<tr>
			<th></th>
			<td><a href="/viewOrder" class="btn btn-success link"> View
					Order </a></td>
		</tr>
		<tr>
			<th></th>
			<td><a href="/generateReport" class="btn btn-success link">
					Generate Report </a></td>
		</tr>
		<tr>
			<th></th>
			<td><a href="/helpRequest" class="btn btn-success link"> Help
					Request </a></td>
		</tr>




	</table>

</body>
</html>
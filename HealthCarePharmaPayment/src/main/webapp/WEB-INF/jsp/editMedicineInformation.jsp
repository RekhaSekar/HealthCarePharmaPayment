<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Medicine</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
body {
	width: 40%;
	margin: auto;
}

.Container {
	padding: 1em;
	background-color: #F5F5DC;
}

.header {
	padding: 20px;
	background: #1abc9c;
	color: white;
	font-size: 30px;
	margin: auto;
}
</style>
</head>
<body>
	<h3>Edit Medicine Details</h3>
	<div>
		<form:form class="Container" action="/saveEditedMedicineInformation"
			modelAttribute="medicineDetails" name="form1" method="GET">
			<h4 style="color: green">${message}</h4>
			<div class="form-group">
				<label>Medicine Name</label><br>
				<form:input class="form-control"
					value="${medicine.getMedicineName()}" type="text"
					path="medicineName" id="name" required="required" />
			</div>
			<div class="form-group">
				<label>Manufacturer</label><br>
				<form:input class="form-control"
					value="${medicine.getManufacturer()}" type="text"
					path="manufacturer" id="manu" required="required" />
			</div>
			<div class="form-group">
				<label>Quantity Per Strip</label><br>
				<form:input class="form-control"
					value="${medicine.getQuantityPerStrip()}" type="text"
					path="quantityPerStrip" id="quanti" required="required" />
			</div>
			<div class="form-group">
				<label>Manufacturing Date</label><br>
				<form:input class="form-control"
					value="${medicine.getManufacturingDate()}" type="text"
					path="manufacturingDate" id="date" required="required" />
			</div>
			<div class="form-group">
				<label>Expiry Date</label><br>
				<form:input class="form-control" value="${medicine.getExpiryDate()}"
					type="text" path="expiryDate" id="exdate" required="required" />
			</div>
			<div class="form-group">
				<label>Type</label><br>
				<form:input class="form-control" value="${medicine.getType()}"
					type="text" path="type" id="type" required="required" />
			</div>
			<div class="form-group">
				<label>Disease</label><br>
				<form:input class="form-control" value="${medicine.getDisease()}"
					type="text" path="disease" id="type" required="required" />
			</div>

			<button type="submit" class="btn btn-success">Save</button>
			<a href="/medicineInformation"><input type="button"
				class="btn btn-primary" value="Back"></input></a>
		</form:form>
	</div>
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>
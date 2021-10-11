<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2>JavaScript HTML Events</h2>
	<p>Click the button to display the date.</p>
	<form method="post" action="#">
		<label>Enter your name:</label> <input type="text" name="name"
			placeholder="name" />

		<button type="submit" onclick="displayDate()">The time is?</button>
	</form>
	<script>
		function displayDate() {
			alert('Hello');
		}
	</script>

	<p id="demo"></p>




</body>
</html>
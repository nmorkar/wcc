
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">

<meta charset="UTF-8">
<title>Cric</title>

<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link href="css/bootstrap-responsive.css" rel="stylesheet">

</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="#">Welcome </a><span class="brand" id="uname">${uname}</span>
				<div style="float:right"><a class="brand" href="home.htm">Back</a></div>
			</div>
			
		</div>
	</div>


	<table border="1" cellpadding="5" cellspacing="5">
		<c:forEach var="mlist" items="${mlist}">
			<tr>
				<th colspan="2">${mlist.key}</th>
			</tr>
			<c:forEach var="player" items="${mlist.value}">
				<tr>
					<td>${player.intial}</td>
					<td>${player.selectedPlayers}</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>


</body>
</html>
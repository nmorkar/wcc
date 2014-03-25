 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<a class="brand" href="#">Welcome </a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><span id="uname">${uname}</span></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${isAdmin}">
<p><button type="submit" class="btn btn-success" id="messageSubmit">Start</button></p>
<p><button type="submit" class="btn btn-success" id="restSubmit">Rest</button></p>
<p><input type="text" id="mid"><button type="submit" class="btn btn-success" id="midSubmit">New</button></p>

	</c:if>
	<div id="main1" class="container">
		<div class="row">
			<div id="mainCric" class="span5"></div>
			<div id="mainCricRes" class="span5"></div>
		</div>
		<div class="row">
		<div id="circMessage" class="span6"></div>
		</div>
	</div>

	<!-- div id="main" class="container">
		<div class="row">
			<div class="span6">
				<h2>Write your message</h2>
				<textarea rows="3" cols="10" id="inputMessage"></textarea>
				<button type="submit" class="btn btn-success" id="messageSubmit">Send</button>
			</div>
			<div class="span6">
				<h2>Reverse Message</h2>
				<textarea rows="3" cols="10" id="outputMessage"></textarea>
			</div>
		</div>
	</div -->

	<footer> </footer>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/app.js">
		
	</script>
</body>

</html>
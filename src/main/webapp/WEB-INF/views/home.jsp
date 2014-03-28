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

.modal {
    /* some styles to position the modal at the center of the page */
    position: fixed;
    top: 50%;
    left: 50%;
    width: 300px;
    line-height: 200px;
    height: 200px;
    margin-left: -150px;
    margin-top: -100px;
    background-color: #f1c40f;
    text-align: center;
   
    /* needed styles for the overlay */
    z-index: 10; /* keep on top of other elements on the page */
    outline: 9999px solid rgba(0,0,0,0.5);
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
				<div><a class="brand" href="logout.htm">Logout</a></div>
			</div>
			
		</div>
	</div>
	<c:if test="${isAdmin}">
<!-- p><button type="submit" class="btn btn-success" id="messageSubmit">Start</button></p -->
<p>
	<input type="text" id="mid"> &nbsp; 
	<button type="submit" class="btn btn-success" id="midSubmit"> Add New </button> &nbsp;
	<button type="submit" class="btn btn-success" id="restSubmit">Rest</button>
</p>

	</c:if>
	<span>Select For : </span> <span id="midTxt" ></span>
	
	<div id="main1" class="container">
		<div class="row">
			<div id="mainCric" class="span5"></div>
			<div id="mainCricRes" class="span5"></div>
		</div>
	</div>

	<div id="circMessage" style="max-height:50px overflow-y: scroll" ></div>
	<div id="cricModal" ></div>

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
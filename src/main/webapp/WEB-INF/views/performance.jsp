
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
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>

<c:if test="${isAdmin}">

<script type="text/javascript">
	$(document).ready(function() {
		$("button#savePerfSubmit").on('click', function() {
			console.log('Save');
			var vData = {
				matchName : $("#matchName").val(),
				performance : $("#performance").val()
			};
			var jqxhr = $.ajax({
				type : "POST",
				url : "savePerformance.htm",
				data : vData
			}).done(function(data) {
				$("#message").text("Added succuess fully.");
				console.log("done");
			}).fail(function() {
				$("#message").text("Problem while saving.");
				console.log("error");
			}).always(function() {
				//console.log( "complete" );
			});
		});

	});
</script>

</c:if>
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
				<div style="float: right">
					<a class="brand" href="home.htm">Back</a>
				</div>
			</div>

		</div>
	</div>

	<!-- <div class="panel panel-default">
  Default panel contents
  <div class="panel-heading">Panel heading</div>

  Table
  <table class="table">
    ...
  </table>
</div> -->

<c:if test="${isAdmin}">
	<div>
		<form>
			<span id="message"></span> <input type="text" id="matchName">
			<textarea rows="3" cols="450" id="performance"></textarea>
			<button type="button" class="btn btn-success" id="savePerfSubmit">Save</button>
		</form>
	</div>
</c:if>
	<table border="1" cellpadding="0" cellspacing="0">
		<c:forEach var="mlist" items="${mlist}">
			<tr>
				<td>
					<table class="table">
						<tr>
							<th colspan="4">${mlist.matchName}</th>
						</tr>
						<tr>
							<c:forEach var="player" items="${mlist.players}">
								<td>
									<table class="table">
										<tr>
											<th>${player.intial}</th>
										</tr>
										<tr>
											<td>
												<table class="table">
													<c:forEach var="playerPerf"
														items="${player.playersPerformance}">
														${playerPerf}
													</c:forEach>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</c:forEach>
							<td>
								<table class="table">
									<c:forEach var="performance1" items="${mlist.performance}">
										${performance1}
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
	<title>Cric Login</title>
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<section class="loginform cf">
	<p style="color:red">${message}</p>
		<form name="login" action="validate.htm" method="post" accept-charset="utf-8">
			<ul>
				<li>
					<label for="usermail">Email</label>
					<input type="text" name="username" placeholder="name" required>
				</li>
				<li>
					<label for="password">Password</label>
					<input type="password" name="password" placeholder="password" required></li>
				<li>
					<input type="submit" value="Login">
				</li>
			</ul>
		</form>
	</section>
</body>
</html>
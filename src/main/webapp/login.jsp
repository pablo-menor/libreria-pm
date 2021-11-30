<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="./css/form.css"/>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
<p>${ mensaje }</p>
	<h1>LOGIN</h1>
		<form action="home?option=login" method="POST">
		<input type="text" placeholder="Username" name="username">
		<input type="text" placeholder="Contraseña" name="password">		
		<input type="submit" value="Entrar" class="submit">
		<a href="home?option=signUpPage">Crear una cuenta</a>
	</form>
</body>
</html>
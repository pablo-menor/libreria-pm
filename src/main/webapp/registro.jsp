<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
<link rel="stylesheet" type="text/css" href="./css/form.css"/>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
	<h1>Registro</h1>
	<h3>${ mensaje }</h3>
		<form action="home?option=signUp" method="POST">
		<input type="text" placeholder="Nombre" name="nombre">
		<input type="text" placeholder="Apellidos" name="apellidos">
		<input type="text" placeholder="Username" name="username">
		<input type="email" placeholder="Email" name="email">
		<input type="text" placeholder="Dirección" name="direccion">
		<input type="text" placeholder="Contraseña" name="password">		
		<input type="submit" value="Enviar" class="submit">
		<a href="home?option=loginPage">Ya tengo una cuenta</a>
	</form>
</body>
</html>
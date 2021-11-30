<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/form.css"/>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
<title>Admin Panel</title>
</head>
<body>

</body>
<h1>Nuevo admin</h1>
	<nav>
		<p>Hola <span>${ mensaje }<span></p>
		<a href="admin?option=panel" >Volver al panel principal</a>
		<a href="home?option=signOut" id="sign-out">Cerrar sesión</a>
	</nav>
	${ mensaje2 }
	<form action="admin?option=addAdmin" method="POST">
			<input type="text" placeholder="Nombre" name="nombre">
		<input type="text" placeholder="Apellidos" name="apellidos">
		<input type="text" placeholder="Username" name="username">
		<input type="email" placeholder="Email" name="email">
		<input type="text" placeholder="Dirección" name="direccion">
		<input type="text" placeholder="Contraseña" name="password">		
		<input type="submit" value="Guardar" class="submit">
	</form>
</html>
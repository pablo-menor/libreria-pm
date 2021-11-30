<%@page import="java.util.List"%>
<%@page import="model.beans.Tema"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Selección de temas</title>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
	<%List<Tema> list = (List<Tema>)request.getAttribute("listTopics"); %>
	<nav>
		<p>Hola <span>${ mensaje }<span></p>
		<a href="books?option=cart" id="carrito">Carrito</a>
		<a href="home?option=signOut" id="sign-out">Cerrar sesión</a>
	</nav>

	<section>
		<h1>Seleccione un tema</h1>
		<form action="books?option=topic" method="POST">
		
			<select name="topic">
				<%for(Tema ele: list) {%>
					 <option value=<%=ele.getIdTema()%>><%=ele.getDescTema()%></option>  
				<%}%>
			</select>
			<input type="submit" value="Ver libros" class="send">
		</form>
	</section>

</body>
</html>
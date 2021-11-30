<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.util.List"%>
<%@page import="model.beans.Tema"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="./css/form.css"/>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
<h1>Panel de administración</h1>
<nav>
		<p>Hola <span>${ mensaje }<span></p>
		<a href="nuevoAdministrador.jsp" >Nuevo Administrador</a>
		<a href="home?option=signOut" id="sign-out">Cerrar sesión</a>
	</nav>
<section id="panel">

	<form action="admin?option=newTopic" method="POST">
		<input type="text" placeholder="Tema" name="topic">
		<input type="text" placeholder="Abreviatura" name="abrev">		
		<input type="submit" value="Añadir tema" class="submit">

	</form>
	<form action="admin?option=newBook" method="POST">
		<input type="text" placeholder="ISBN" name="isbn">
		<input type="text" placeholder="Titulo" name="title">	
		<input type="text" placeholder="Autor" name="author">	
		<input type="text" placeholder="Precio" name="price">	
		<input type="text" placeholder="Stock" name="stock">		
		<input type="text" placeholder="Id del tema" name="idTopic">	
		<input type="submit" value="Añadir libro" class="submit">
	</form>
	<%List<Tema> list = (List<Tema>)request.getAttribute("listTopics"); %>
	<table id="idTema" >
		<tr>
			<th>TEMA</th>
			<th>ID</th>
		</tr>
		
		<%for(Tema ele: list) {%>
			<tr>
				<td><%=ele.getDescTema()%></td>
				<td><%=ele.getIdTema()%></td>
			</tr>
		<%}%>
	</table>
</section>

</body>
</html>
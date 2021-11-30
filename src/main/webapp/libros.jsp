<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
<%@page import="model.beans.Libro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libros</title>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
	<%List<Libro> list = (List<Libro>)request.getAttribute("listBooks"); %>
	<nav>
		<p>Hola <span>${ mensaje }<span></p>
		<a href="home?option=signOut" id="sign-out">Cerrar sesión</a>
	</nav>
	<a href="home?option=topics" id="volver-temas">Volver a temas</a>
	<section  >
		<form action="books?option=addToCart" method="POST" class="libros">
			<table id="tabla-libros">
				<tr>
					<th>TÍTULO</th>
					<th>AUTOR</th>
					<th>PRECIO</th>
					<th>Carrito</th>
				</tr>
				
				<%for(Libro ele: list) {%>
					<tr>
						<td><%=ele.getTitulo()%></td>
						<td><%=ele.getAutor()%></td>
						<td><%=ele.getPrecioUnitario()%></td>
						<td><input type="checkbox" name="isbn" value="<%=ele.getIsbn()%>"></td>
						<!--  <td><a href="books?option=add&isbn=<%= ele.getIsbn() %>">Añadir al carrito</a></td>-->
					</tr>
				<%}%>
			</table>
			<input type="submit" value="Añadir al carrito" class="send">
		  </form>
	  </section>
	  
	  <script type="text/javascript" src="./js/index.js"></script>
</body>
</html>
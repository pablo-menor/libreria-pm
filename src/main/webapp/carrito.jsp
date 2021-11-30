<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.util.List"%>
        <%@page import="model.beans.Libro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito</title>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
<%List<Libro> list = (List<Libro>)request.getSession().getAttribute("addedBooks"); %>
	<nav>
		<p>Hola <span>${ mensaje }<span></p>
		<a href="home?option=signOut" id="sign-out">Cerrar sesión</a>
	</nav>
		<table >
			<tr>
				<th>TÍTULO</th>
				<th>AUTOR</th>
				<th>PRECIO</th>
				<th>Eliminar</th>
			</tr>
			
			<%for(Libro ele: list) {%>
				<tr>
					<td><%=ele.getTitulo()%></td>
					<td><%=ele.getAutor()%></td>
					<td><%=ele.getPrecioUnitario()%></td>
					<td><a href="books?option=removeFromCart&isbn=<%=ele.getIsbn()%>">Eliminar</a></td>
				</tr>
			<%}%>
		</table>
		<a href="home?option=topics" id="volver-temas">Volver a temas</a>
		<a href="books?option=emptyCart" id="vaciar">Vaciar carrito</a>
		<a href="books?option=buyCart" id="vaciar">Comprar</a>
</body>
</html>
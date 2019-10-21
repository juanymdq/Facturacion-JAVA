<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Administrar Articulos</title>
</head>
<body>
	<center><h1>Lista Articulos</h1></center>
	<center><a href="adminArticulo?action=mostrar">Actualizar</a></center>
	<div class="container">
		<center><p style="color:red">${mensaje}<p></center>
		<table>
			<tr>
				<td><a href="adminArticulo?action=index" >Ir al menú</a> </td>
			</tr>
		</table>
		
		<table class="table table-striped">
			<tr>
			 <td>ID</td>
			 <td>NOMBRE</td>
			 <td>CATEGORIA</td>
			 <td>FECHA ALTA</td>
			 <td>PRECIO</td>
			 <td>CANTIDAD</td>		 
			 <td>ACCIONES</td>
			 <td><a href="adminArticulo?action=nuevo"><font size="6">+</font></a></td>	
			</tr>
			<c:forEach var="art" items="${lista}">
				<tr>
					<td><c:out value="${art.id_articulo}"/></td>							
					<td><c:out value="${art.nombre_articulo}"/></td>
					<td><c:out value="${art.cat.getNombre_categoria()}"/></td>					
					<td><c:out value="${art.fecha_alta}"/></td>
					<td><c:out value="${art.precio}"/></td>
					<td><c:out value="${art.cantidad}"/></td>
					<td><a href="adminArticulo?action=showedit&id=<c:out value="${art.id_articulo}" />">Editar</a></td>
					<td><a href="adminArticulo?action=eliminar&id=<c:out value="${art.id_articulo}"/>">Eliminar</a> </td>				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
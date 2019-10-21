<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Administrar Ciudades</title>
</head>
<body>
	<center><h1>Lista Ciudades</h1></center>
	<center><a href="adminCiudad?action=mostrar">Actualizar</a></center>
	<div class="container">
		<center><p style="color:red">${mensaje}<p></center>
		<table>
			<tr>
				<td><a href="adminProvincia?action=index" >Ir al menú</a> </td>
			</tr>
		</table>
		
		<table class="table table-striped">
			<tr>
			 <td>ID</td>
			 <td>NOMBRE</td>
			 <td>PROVINCIA</td>		 
			 <td>ACCIONES</td>
			 <td><a href="adminCiudad?action=nuevo"><font size="6">+</font></a></td>	
			</tr>
			<c:forEach var="ciudad" items="${lista}">
				<tr>
					<td><c:out value="${ciudad.id_ciudad}"/></td>			
					<td><c:out value="${ciudad.nombre_ciudad}"/></td>				
					<td><c:out value="${ciudad.nombre_provincia}"/></td>
					<td><a href="adminCiudad?action=showedit&id=<c:out value="${ciudad.id_ciudad}" />">Editar</a></td>
					<td><a href="adminCiudad?action=eliminar&id=<c:out value="${ciudad.id_ciudad}"/>">Eliminar</a> </td>				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
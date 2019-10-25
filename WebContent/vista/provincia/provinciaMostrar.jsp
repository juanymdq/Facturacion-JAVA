<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.container{
	font-size:18px;
	width:700px;
}

</style>
<title>Administrar Provincias</title>
</head>
<body>
	<center><h1>Lista  Provincias</h1></center>
	<center><a href="adminProvincia?action=mostrar" >Actualizar</a></center>
	<div class="container">
		<center><p style="color:red">${mensaje}<p></center>
		<a href="adminProvincia?action=nuevo" class="btn btn-success btn-lg">Nuevo Registro</a>
		<br>
		<center><a href="adminProvincia?action=index">Ir al menú</a></center>
		<table class="table table-sm">
			<tr>
			 <td> ID</td>
			 <td> NOMBRE</td>		 
			 <td colspan="2">ACCIONES</td>			
			</tr>
			<c:forEach var="provincia" items="${lista}">
				<tr>
					<td><c:out value="${provincia.id_provincia}"/></td>			
					<td><c:out value="${provincia.nombre_provincia}"/></td>				
					<td><a href="adminProvincia?action=showedit&id=<c:out value="${provincia.id_provincia}"/>" class="btn btn-warning btn-sm">Editar</a></td>
					<td><a href="adminProvincia?action=eliminar&id=<c:out value="${provincia.id_provincia}"/>" class="btn btn-danger btn-sm">Eliminar</a> </td>				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
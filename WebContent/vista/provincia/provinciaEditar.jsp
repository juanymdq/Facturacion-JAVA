<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Actualizar Artículo</title>
</head>
<body>
<!-- 
	<form action="adminProvincia?action=editar" method="post" >
		<table>
			<tr>
				<td><label>Id Provincia</label></td>
				<td><input type="text" name="id_provincia" value="<c:out value="${provincia.id_provincia}"></c:out>" ></td>
			</tr>
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="nombre_provincia" value='<c:out value="${provincia.nombre_provincia}"></c:out>' ></td>
			</tr>
			
		</table>
	
		<input type="submit" name="registrar" value="Guardar"> 
	</form>
 -->
	<div class="container">
		<h1>Editar Provincia</h1>
		<form action="adminProvincia?action=editar" method="post" >		
			<div class="col-xs-2">
			    <label for="ex1">Id Provincia</label>
			    <input  type="text" class="form-control" name ="id_provincia" value="<c:out value="${provincia.id_provincia}"></c:out>" >
	  		</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Nombre de Provincia</label>
			  <input type="text" name="nombre_provincia" class="form-control" value='<c:out value="${provincia.nombre_provincia}"></c:out>' >
			</div>
			<button type="submit" name="registrar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminProvincia?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
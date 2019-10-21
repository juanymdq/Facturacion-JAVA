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
	<div class="container">
		<center><h1>EditarArticulo</h1></center>
		<center><p style="color:red">${mensaje}<p></center>
		<form action="adminArticulo?action=editar" method="post" >		
			<div class="col-xs-2">
			    <label for="ex1">Id Articulo</label>
			    <input  type="text" class="form-control" name ="id_articulo" value="<c:out value="${articulos.id_articulo}"></c:out>" >
	  		</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Nombre de articulo</label>
			  <input type="text" name="nombre_articulo" class="form-control" value='<c:out value="${articulos.nombre_articulo}"></c:out>' >
			</div>
		<div class="form-group">
				<label for="formGroupExampleInput2">Fecha de alta</label>
				<input type="date" name="fecha_alta" value="${articulos.fecha_alta}">
				<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="myDate"/>
				<fmt:formatDate var="startFormat" pattern="yyyy-MM-dd"/>
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Precio</label>
			  <input type="text" name="precio" class="form-control" value='<c:out value="${articulos.precio}"></c:out>' >
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Cantidad</label>
			  <input type="text" name="cantidad" class="form-control" value='<c:out value="${articulos.cantidad}"></c:out>' >
			</div>			
			
			<button type="submit" name="registrar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminArticulo?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
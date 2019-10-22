<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Registrar Articulo</title>
</head>
<body>
	<div class="container">
		<h1>Registrar Articulo</h1>
		<form action="adminArticulo?action=register" method="post">		
			<div class="col-xs-2">
			    <label for="ex1">Id Articulo</label>			    
			    <input class="form-control" name ="id_articulo" id="ex1" type="text" placeholder="Ingrese id del articulo">	  		</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Nombre de articulo</label>
			  <input type="text" name="nombre_articulo" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese el nombre del articulo">
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Categoria</label>
			  <select name="categoria" id="formGroupExampleInput2">			  
			  	  	<c:forEach var="cat" items="${listaC}">
			  			<option value="${cat.id_categoria}">${cat.nombre_categoria}</option>
			  		</c:forEach>			  	
			  </select>			  
			</div>						
			<div class="form-group">
				<label for="formGroupExampleInput2">Fecha de alta</label>
				<input type="date" name="fecha_alta">
				<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="myDate"/>
				<fmt:formatDate var="startFormat" pattern="yyyy-MM-dd"/>
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Precio</label>
			  <input type="text" name="precio" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese el precio">
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Cantidad</label>
			  <input type="text" name="cantidad" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese la cantidad">
			</div>
			<button type="submit" name="agregar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminArticulo?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
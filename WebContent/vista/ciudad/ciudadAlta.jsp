<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>}
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Registrar Ciudad</title>
</head>
<body>
	<div class="container">
		<h1>Registrar Ciudad</h1>
		<form action="adminCiudad?action=register" method="post">		
			<div class="col-xs-2">
			    <label for="ex1">Id Ciudad</label>
			    <input class="form-control" name ="id_ciudad" id="ex1" type="text" placeholder="Ingrese id de la ciudad">
	  		</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Nombre de Ciudad</label>
			  <input type="text" name="nombre_ciudad" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese el nombre de la ciudad">
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Provincia</label>
			  <select name="id_provincia" id="formGroupExampleInput2">
			  	  	<c:forEach var="prov" items="${listap}">
			  			<option value="${prov.id_provincia}">${prov.nombre_provincia}</option>
			  		</c:forEach>			  	
			  </select>			  
			</div>
			<button type="submit" name="agregar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminCiudad?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
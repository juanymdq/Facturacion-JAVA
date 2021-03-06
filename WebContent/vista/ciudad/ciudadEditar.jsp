<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="recursos/estilos/estilos.css">
<title>Actualizar Ciudad</title>
</head>
<body>
	<div class="container">
		<div class="form-principal">
			<center><h1>Editar Ciudad</h1></center>
			<hr>
			<center><a href="adminCiudad?action=mostrar">Volver</a></center>
			<form action="adminCiudad?action=editar" method="post" >		
				<div class="form-group col-md-10" style="width:150px;">
				    <label for="exampleFormControlInput1">Id Ciudad</label>
				    <input  type="text" class="form-control" name ="id_ciudad" value="<c:out value="${ciudad.id_ciudad}"></c:out>" >
		  		</div>		
				<div class="form-group col-md-10" style="width:350px;">
				  <label for="exampleFormControlInput1">Nombre de Ciudad</label>
				  <input type="text" name="nombre_ciudad" class="form-control" value='<c:out value="${ciudad.nombre_ciudad}"></c:out>' >
				</div>
				<div class="form-group col-md-10" style="width:180px;">
				  <label for="exampleFormControlInput1">C&oacute;digo Postal</label>
				  <input type="text" name="cod_postal" class="form-control" value='<c:out value="${ciudad.cod_postal}"></c:out>' >
				</div>
				<div class="form-group col-md-10" style="width:450px;">
				  <label for="exampleFormControlInput1">Provincia</label>
				  <select name="id_provincia" id="formGroupExampleInput2"  class="form-control">
				  		<option value="${idProv}" selected>${nomProv}</option>
				  	  	<c:forEach var="prov" items="${listap}">
				  			<option value="${prov.id_provincia}">${prov.nombre_provincia}</option>
				  		</c:forEach>
				  					  	
				  </select>			  
				</div>
				<button type="submit" name="registrar" class="btn btn-lg btn-primary btn-block btn-signin">Guardar</button>					
			</form>
		</div>
	</div>
</body>
</html>
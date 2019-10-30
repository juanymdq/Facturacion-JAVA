<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="recursos/estilos/estilos.css">
<title>Actualizar Artículo</title>
</head>
<body>
	<div class="container">
		<div class="form-principal">
			<center><h1>Editar Provincia</h1></center>	
			<hr>
			<center><a href="adminProvincia?action=mostrar">Volver</a></center>
			<form action="adminProvincia?action=editar" method="post" >		
				<div class="form-group col-md-10" style="width:150px;">
				    <label for="exampleFormControlInput1">Id Provincia</label>
				    <input for="exampleFormControlInput1" type="text" class="form-control" name ="id_provincia" value="<c:out value="${provincia.id_provincia}"></c:out>" >
		  		</div>		
				<div class="form-group col-md-10" style="width:400px;">
				  <label for="exampleFormControlInput1">Nombre de Provincia</label>
				  <input for="exampleFormControlInput1" type="text" name="nombre_provincia" class="form-control" value='<c:out value="${provincia.nombre_provincia}"></c:out>' >
				</div>
				<button type="submit" name="registrar" class="btn btn-lg btn-primary btn-block btn-signin">Guardar</button>					
			</form>
		</div>
	</div>
</body>
</html>
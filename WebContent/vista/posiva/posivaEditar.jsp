<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Actualizar Posicion IVA</title>
</head>
<body>
	<div class="container">
		<center><h1>Editar Posicion IVA</h1></center>
		<form action="adminPosiva?action=editar" method="post" >		
			<div class="col-xs-2">
			    <label for="ex1">Id Posicion IVA</label>
			    <input  type="text" class="form-control" name ="id_posiva" value="<c:out value="${posicion.id_posiva}"></c:out>" >
	  		</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Nombre de Posicion IVA</label>
			  <input type="text" name="nombre_posiva" class="form-control" value='<c:out value="${posicion.nombre_posiva}"></c:out>' >
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Porcentaje de Posicion IVA</label>
			  <input type="text" name="porcentaje" class="form-control" value='<c:out value="${posicion.porcentaje}"></c:out>' >
			</div>
			<button type="submit" name="registrar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminPosiva?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="recursos/estilos/estilos.css">
<title>Actualizar Posicion IVA</title>
</head>
<body>
	<div class="container">
		<div class="form-principal">
			<center><h1>Registrar Posicion IVA</h1></center>	
			<hr>
			<center><a href="adminPosiva?action=mostrar">Volver</a></center>
			<form action="adminPosiva?action=editar" method="post" >		
				<div class="form-group col-md-10" style="width:150px;">
				    <label>Id Posicion IVA</label>
				    <input  type="text" class="form-control" name ="id_posiva" value="<c:out value="${posicion.id_posiva}"></c:out>" >
		  		</div>		
				<div class="form-group col-md-10" style="width:400px;">
				  <label>Nombre de Posicion IVA</label>
				  <input type="text" name="nombre_posiva" class="form-control" value='<c:out value="${posicion.nombre_posiva}"></c:out>' >
				</div>
				<div class="form-group col-md-6" style="width:200px;">
				  <label>Porcentaje de Posicion IVA</label>
				  <input type="text" name="porcentaje" class="form-control" value='<c:out value="${posicion.porcentaje}"></c:out>' >
				</div>
				<button type="submit" name="registrar" class="btn btn-lg btn-primary btn-block btn-signin">Guardar</button>					
			</form>
		</div>
	</div>
</body>
</html>
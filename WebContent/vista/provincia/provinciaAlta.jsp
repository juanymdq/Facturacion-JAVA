<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Registrar Provincia</title>
</head>
<body>
	<div class="container">
		<h1>Registrar Provincia</h1>	
		<form action="adminProvincia?action=register" method="post">				
			<div class="col-xs-2">
				<label for="ex1">Id Provincia</label>
				<input class="form-control" name ="id_provincia" id="ex1" type="text" placeholder="Ingrese id de Provincia">  
	  		</div>		
			<div class="form-group">
				<label for="formGroupExampleInput2">Nombre de Provincia</label>
			  	<input type="text" name="nombre_provincia" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese el nombre de la provincia">
			</div>			
			<button type="submit" name="agregar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminProvincia?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
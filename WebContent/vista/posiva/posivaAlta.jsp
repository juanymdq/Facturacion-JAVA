<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Registrar Posicion IVA</title>
</head>
<body>
	<div class="container">
		<center><h1>Registrar Posicion IVA</h1></center>	
		<form action="adminPosiva?action=register" method="post">				
			<div class="col-xs-2">
				<label for="ex1">Id Posicion IVA</label>
				<input class="form-control" name ="id_posiva" id="ex1" type="text" placeholder="Ingrese id de la Posicion IVA">  
	  		</div>		
			<div class="form-group">
				<label for="formGroupExampleInput2">Nombre de Posicion IVA</label>
			  	<input type="text" name="nombre_posiva" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese el nombre de la posicion IVA">
			</div>	
			<div class="form-group">
				<label for="formGroupExampleInput2">Porcentaje IVA</label>
			  	<input type="text" name="porcentaje" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese el porcentaje de la posicion IVA">
			</div>			
			<button type="submit" name="agregar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminPosiva?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
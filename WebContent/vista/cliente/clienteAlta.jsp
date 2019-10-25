<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
.container{
	font-size:12px
}
.form-principal{
	border:1px solid;
	heigth:600px
	padding: 40px 40px;
}
.form-row .form-group{
padding-left: 40px;
}

</style>
<title>Registrar Cliente</title>
</head>
<body>
	<div class="container">		
		<div class="form-principal">
			<center><h1>Registrar Cliente</h1></center>
			<center><a href="adminCliente?action=mostrar">Volver</a></center>
			<hr>			
			<form action="adminCliente?action=register" method="post">
				<div class="form-row">			
				    <div class="form-group col-md-2" style="width:200px;">
					     <label for="exampleFormControlInput1">Id Cliente</label>
					     <input for="exampleFormControlInput1" name ="id_cliente" type="text" class="form-control" placeholder="Ingrese id" >	  		
					</div>
					<div class="form-group col-md-4">		
						  <label for="exampleFormControlInput1">Nombre</label>
						  <input for="exampleFormControlInput1" type="text" name="nombre" class="form-control" placeholder="Ingrese nombre">
					</div>
					<div class="form-group col-md-4">	
						  <label for="exampleFormControlInput1">Apellido</label>
						  <input for="exampleFormControlInput1" type="text" name="apellido" class="form-control" placeholder="Ingrese apellido">
					 </div>
				</div>
				<br>
				<div class="form-row">
					 <div class="form-group col-md-4">	 
						    <label for="exampleFormControlInput1">Fecha Nacimiento</label>
							<input for="exampleFormControlInput1" type="date" name="fecha_nacimiento" class form-control>
							<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="myDate"/>
							<fmt:formatDate var="startFormat" pattern="yyyy-MM-dd"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-3">
						 <label for="exampleFormControlInput1">DNI</label>
						 <input type="text" name="dni" class="form-control" placeholder="Ingrese DNI">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						  <label for="exampleFormControlInput1">Domicilio</label>
						  <input for="exampleFormControlInput1" type="text" name="domicilio" class="form-control" placeholder="Ingrese domicilio">
					</div>
					<div class="form-group col-md-4" style="width:350px;">
					<label for="exampleFormControlSelect2">Ciudad</label>
					  <select for="exampleFormControlSelect2" name="id_ciudad" class="form-control">			  
					  	  	<c:forEach var="cd" items="${listaC}">
					  			<option value="${cd.id_ciudad}">${cd.nombre_ciudad}</option>
					  		</c:forEach>			  	
					  </select>			  
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						  <label for="exampleFormControlInput1">Email</label>
						  <input for="exampleFormControlInput1" type="email" name="email" class="form-control" placeholder="email@example.com">
					</div>
					<div class="form-group col-md-4" style="width:250px;">
						  <label for="exampleFormControlInput1">Telefono</label>
						  <input for="exampleFormControlInput1" type="text" name="telefono" class="form-control" placeholder="Ingrese telefono">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-4" style="width:300px;">
					<label for="exampleFormControlSelect2">Posicion IVA</label>
					  <select for="exampleFormControlSelect2" name="id_posiva" class="form-control">			  
					  	  	<c:forEach var="pos" items="${listaIva}">
					  			<option value="${pos.id_posiva}">${pos.nombre_posiva}</option>
					  		</c:forEach>			  	
					  </select>			  
					</div>
					<div class="form-group col-md-4" style="width:250px;">
						  <label for="exampleFormControlInput1">CUIT/CUIL</label>
						  <input for="exampleFormControlInput1" type="text" name="cuit" class="form-control" placeholder="Ingrese CUIT/CUIL">			
					</div>
				</div>
				<div class="botones">		
					<button type="submit" name="agregar" class="btn btn-lg btn-primary btn-block btn-signin">Guardar</button>
					
				</div>	
			</form>
		</div>
	</div>
</body>
</html>
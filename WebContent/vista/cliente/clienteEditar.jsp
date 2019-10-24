<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Actualizar Cliente</title>
</head>
<body>
	<div class="container">
		<center><h1>Editar Cliente</h1></center>
		<center><p style="color:red">${mensaje}<p></center>
		<form action="adminCliente?action=editar" method="post" >		
			<div class="col-xs-2">
			    <label for="ex1">Id Cliente</label>
			    <input  type="text" class="form-control" name ="id_cliente" value="<c:out value="${cliente.id_cliente}"></c:out>" >
	  		</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Nombre</label>
			  <input type="text" name="nombre" class="form-control" value='<c:out value="${cliente.nombre}"></c:out>' >
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Apellido</label>
			  <input type="text" name="apellido" class="form-control" value='<c:out value="${cliente.apellido}"></c:out>' >
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Fecha de Nacimiento</label>
				<input type="date" name="fecha_nacimiento" value='<c:out value="${cliente.fecha_nacimiento}"></c:out>' >
				<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="myDate"/>
				<fmt:formatDate var="startFormat" pattern="yyyy-MM-dd"/>
			</div>			
			<div class="form-group">
			  <label for="formGroupExampleInput2">DNI</label>
			  <input type="text" name="dni" class="form-control" value='<c:out value="${cliente.dni}"></c:out>' >
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Domicilio</label>
			  <input type="text" name="domicilio" class="form-control" value='<c:out value="${cliente.domicilio}"></c:out>' >
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Ciudad</label>
			  <select name="id_ciudad" id="formGroupExampleInput2">
			  		<option value="${id_c}" selected>${nomC}</option>
			  	  	<c:forEach var="cd" items="${listaC}">
			  			<option value="${cd.id_ciudad}">${cd.nombre_ciudad}</option>
			  		</c:forEach>			  	
			  </select>			  
			</div>
			<div class="form-group">
			  <label for="formGroupExampleInput2">Email</label>
			  <input type="text" name="email" class="form-control" value='<c:out value="${cliente.email}"></c:out>' >
			</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">Telefono</label>
			  <input type="text" name="telefono" class="form-control" value='<c:out value="${cliente.telefono}"></c:out>' >
			</div>			
			<div class="form-group">
			  <label for="formGroupExampleInput2">Posicion IVA</label>
			  <select name="id_posiva" id="formGroupExampleInput2">
			  		<option value="${id_iva}" selected>${nomiva}</option>
			  	  	<c:forEach var="iva" items="${listaI}">
			  			<option value="${iva.id_posiva}">${iva.nombre_posiva}</option>
			  		</c:forEach>			  	
			  </select>			  
			</div>		
			<div class="form-group">
			  <label for="formGroupExampleInput2">CUIT</label>
			  <input type="text" name="cuit" class="form-control" value='<c:out value="${cliente.cuit}"></c:out>' >
			</div>
						
			
			<button type="submit" name="registrar" class="btn btn-primary mb-2">Guardar</button>
			<a href="adminCliente?action=mostrar" >Volver</a>	
		</form>
	</div>
</body>
</html>
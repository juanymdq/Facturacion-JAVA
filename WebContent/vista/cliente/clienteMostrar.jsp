<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
.container{
	font-size:12px;
	margin:10px 0 0 0;
}
.table-striped>tbody>tr:nth-child(odd)>td, 
.table-striped>tbody>tr:nth-child(odd)>th {
 background-color: #C5DDF7;
}
.table-striped>tbody>tr:nth-child(even)>td, 
.table-striped>tbody>tr:nth-child(even)>th {
 background-color: #96C8FD;
}
.table-striped>thead>tr>th {
   background-color: #2784E7;
}
</style>
<title>Administrar Clientes</title>
</head>
<body>
	<center><h1>Lista Clientes</h1></center>
	<center><a href="adminCliente?action=mostrar">Actualizar</a></center>
	<div class="container">
		<center><p style="color:red">${mensaje}<p></center>	
		<a href="adminCliente?action=nuevo" class="btn btn-success btn-lg">Nuevo Registro</a>
		<br>		
		<center><a href="adminCliente?action=index">Ir al menú</a></center>
		<table class="table table-striped">
			<thead>
				<tr class="encabezados">
				 <th>ID</th>
				 <th>NOMBRE</th>
				 <th>APELLIDO</th>
				 <th>FECHA NACIMIENTO</th>
				 <th>DNI</th>
				 <th>DOMICILIO</th>
				 <th>CIUDAD</th>		 
				 <th>EMAIL</th>
				 <th>TELEFONO</th>
				 <th>POS IVA</th>
				 <th>CUIT</th>				 
				 <th colspan="2">ACCIONES</th>	
				</tr>
			</thead>
			<c:forEach var="cli" items="${listaCli}">
				<tbody>
					<tr class="cuerpo">
						<td><c:out value="${cli.id_cliente}"/></td>							
						<td><c:out value="${cli.nombre}"/></td>
						<td><c:out value="${cli.apellido}"/></td>					
						<td><c:out value="${cli.fecha_nacimiento}"/></td>
						<td><c:out value="${cli.dni}"/></td>
						<td><c:out value="${cli.domicilio}"/></td>
						<td><c:out value="${cli.ciudad.nombre_ciudad}"/></td>
						<td><c:out value="${cli.email}"/></td>
						<td><c:out value="${cli.telefono}"/></td>
						<td><c:out value="${cli.posIva.nombre_posiva}"/></td>
						<td><c:out value="${cli.cuit}"/></td>
						<td><a href="adminCliente?action=showedit&id=<c:out value="${cli.id_cliente}" />" class="btn btn-warning btn-sm">Editar</a></td>
						<td><a href="adminCliente?action=eliminar&id=<c:out value="${cli.id_cliente}"/>" class="btn btn-danger btn-sm">Eliminar</a> </td>				
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Administrar Clientes</title>
</head>
<body>
	<center><h1>Lista Clientes</h1></center>
	<center><a href="adminCliente?action=mostrar">Actualizar</a></center>
	<div class="container">
		<center><p style="color:red">${mensaje}<p></center>
		<table>
			<tr>
				<td><a href="adminCliente?action=index" >Ir al menú</a> </td>
			</tr>
		</table>
		
		<table class="table table-striped">
			<tr>
			 <td>ID</td>
			 <td>NOMBRE</td>
			 <td>APELLIDO</td>
			 <td>FECHA NACIMIENTO</td>
			 <td>DNI</td>
			 <td>DOMICILIO</td>
			 <td>CIUDAD</td>		 
			 <td>EMAIL</td>
			 <td>TELEFONO</td>
			 <td>POS IVA</td>
			 <td>CUIT</td>				 
			 <td>ACCIONES</td>
			 <td><a href="adminCliente?action=nuevo"><font size="6">+</font></a></td>	
			</tr>
			<c:forEach var="cli" items="${listaCli}">
				<tr>
					<td><c:out value="${cli.id_cliente}"/></td>							
					<td><c:out value="${cli.nombre}"/></td>
					<td><c:out value="${cli.apellido}"/></td>					
					<td><c:out value="${cli.fecha_nacimiento}"/></td>
					<td><c:out value="${cli.dni}"/></td>
					<td><c:out value="${cli.domicilio}"/></td>
					<td><c:out value="${cli.ciudad.nombre_ciudad}"/></td>
					<td><c:out value="${cli.email}"/></td>
					<td><c:out value="${cli.telefono}"/></td>
					<td><c:out value="${cli.posiva.nombre_posiva}"/></td>
					<td><c:out value="${cli.cuit}"/></td>
					<td><a href="adminCliente?action=showedit&id=<c:out value="${cli.id_cliente}" />">Editar</a></td>
					<td><a href="adminCliente?action=eliminar&id=<c:out value="${cli.id_cliente}}"/>">Eliminar</a> </td>				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
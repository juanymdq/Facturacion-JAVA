<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Administrar Posiciones IVA</title>
</head>
<body>
	<center><h1>Lista  Posiciones IVA</h1></center>
	<center><a href="adminPosiva?action=mostrar" >Actualizar</a></center>
	<div class="container">
		<center><p style="color:red">${mensaje}<p></center>
		<table>
			<tr>
				<td><a href="adminPosiva?action=index" >Ir al menú</a> </td>				
			</tr>
		</table>
		
		<table class="table table-striped">
			<tr>
			 <td>ID</td>
			 <td>NOMBRE</td>
			 <td>PORCENTAJE</td>		 
			 <td>ACCIONES</td>
			 <td><a href="adminPosiva?action=nuevo"><font size="6">+</font></a></td>	
			</tr>
			<c:forEach var="pos" items="${listaI}">
				<tr>
					<td><c:out value="${pos.id_posiva}"/></td>			
					<td><c:out value="${pos.nombre_posiva}"/></td>				
					<td><c:out value="${pos.porcentaje}"/></td>
					<td><a href="adminPosiva?action=showedit&id=<c:out value="${pos.id_posiva}" />">Editar</a></td>
					<td><a href="adminPosiva?action=eliminar&id=<c:out value="${pos.id_posiva}"/>">Eliminar</a> </td>				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
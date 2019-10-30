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
	width:100%;
	margin:10px 0 0 10px;
}
.form-principal{

	align:center;
	width:700px;
	margin:10px 0 0 300px;
}
.table{
	align:center;
}
.table-striped>tbody>tr:nth-child(odd)>td, 
.table-striped>tbody>tr:nth-child(odd)>th {
 background-color: #FDDF96;
}
.table-striped>tbody>tr:nth-child(even)>td, 
.table-striped>tbody>tr:nth-child(even)>th {
 background-color: #FDDF96;
}
.table-striped>thead>tr>th {
   background-color: #E7AC1F;
}
</style>
<title>Administrar Posiciones IVA</title>
</head>
<body>
	<center><h1>Lista  Posiciones IVA</h1></center>
	<center><a href="adminPosiva?action=mostrar" >Actualizar</a></center>
	<div class="container">
		<div class="form-principal">
			<center><p style="color:red">${mensaje}<p></center>
			<a href="adminPosiva?action=nuevo" class="btn btn-success btn-lg">Nuevo Registro</a>
			<br>
			<center><a href="adminPosiva?action=index">Ir al menú</a></center>
			<table class="table table-striped">
				<thead>
					<tr>
					 <th>ID</th>
					 <th>NOMBRE</th>
					 <th>PORCENTAJE</th>		 
					 <th>ACCIONES</th>
					 <th><a href="adminPosiva?action=nuevo"><font size="6">+</font></a></th>	
					</tr>
				</thead>
				<c:forEach var="pos" items="${listaI}">
					<tbody>
						<tr>
							<td><c:out value="${pos.id_posiva}"/></td>			
							<td><c:out value="${pos.nombre_posiva}"/></td>				
							<td><c:out value="${pos.porcentaje}"/></td>
							<td><a href="adminPosiva?action=showedit&id=<c:out value="${pos.id_posiva}" />" class="btn btn-warning btn-sm">Editar</a></td>
							<td><a href="adminPosiva?action=eliminar&id=<c:out value="${pos.id_posiva}"/>" class="btn btn-danger btn-sm">Eliminar</a> </td>				
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
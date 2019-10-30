<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script type="text/javascript" src="recursos/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="recursos/js/guardarCategoria.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<title>Actualizar Artículo</title>
</head>
<body>
	<div class="container">
		<div class="form-principal">
		<center><h1>Editar Articulo</h1></center>
		<center><p style="color:red">${mensaje}<p></center>
		<center><a href="adminArticulo?action=mostrar">Volver</a></center>
		<hr>	
		<form action="adminArticulo?action=editar" method="post" >
			<div class="form-row">
				<div class="form-group col-md-3" style="width:200px;">
				    <label for="ex1">Id Articulo</label>
				    <input  type="text" class="form-control" name ="id_articulo" value="<c:out value="${articulos.id_articulo}"></c:out>" >
		  		</div>		
				<div class="form-group col-md-4" style="width:200px;">
				  <label for="formGroupExampleInput2">Nombre de articulo</label>
				  <input type="text" name="nombre_articulo" class="form-control" value='<c:out value="${articulos.nombre_articulo}"></c:out>' >
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4" style="width:300px;">
				  <label >Categoria</label>
				   <select name="categoria" id="exampleFormControlInput1" style="width:200px;">		
				  		<option value="${id_c}" selected>${nomCat}</option>
				  	  	<c:forEach var="cat" items="${listaC}">
				  			<option value="${cat.id_categoria}">${cat.nombre_categoria}</option>
				  		</c:forEach>			  	
				  </select>			  
				</div>
				
				<div class="form-group col-md-2" style="width:200px;">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
					  Agregar Categoria
					</button>			
					<!-- ****************************** Modal ******************************************* -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					      	<h4 class="modal-title" id="myModalLabel">AGREGAR CATEGORIA</h4>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>				        
					      </div>				       
					      <div class="modal-body">
					      	  <div class="form-group col-md-4">
						      	<label for="formGroupExampleInput2">Id Categor&iacute;a:</label>			       
						        <input type="text" name="id_categoria" id="id_categoria" placeholder="Ingrese id">
						      </div>
						      <div class="form-group col-md-8" width="400px">
						        <label for="exampleFormControlInput1">Nombre de Categor&iacute;a</label>
						        <input for="exampleFormControlInput1" class="form-control"type="text" id="nombre_categoria" name="nombre_categoria" placeholder="Ingrese nombre">
						     </div>			        
					      </div>					     
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        <button type="button" id="btn_guardar" class="btn btn-primary">Guardar</button>					        
					      </div><!-- modal-footer -->				      
					    </div><!-- modal-content -->
					  </div><!-- modal-dialog -->
					</div><!-- modal fade -->
				</div><!-- form-group col-md-2  -->
			<!-- ****************************** Modal ******************************************* -->
			</div><!-- form-row  -->
				
			<div class="form-row">									
				<div class="form-group col-md-2" style="width:200px;">
					<label for="formGroupExampleInput2">Fecha de alta</label>
					<input type="date" name="fecha_alta" value="${articulos.fecha_alta}">
					<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="myDate"/>
					<fmt:formatDate var="startFormat" pattern="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-2" style="width:200px;">
				  <label for="formGroupExampleInput2">Precio</label>
				  <input type="text" name="precio" class="form-control" value='<c:out value="${articulos.precio}"></c:out>' >
				</div>
				<div class="form-group col-md-2" style="width:200px;">
				  <label for="formGroupExampleInput2">Cantidad</label>
				  <input type="text" name="cantidad" class="form-control" value='<c:out value="${articulos.cantidad}"></c:out>' >
				</div>			
			</div>
			<div class="botones">
				<button type="submit" name="registrar" class="btn btn-lg btn-primary btn-block btn-signin">Guardar</button>
			</div>	
		</form>
	</div><!-- form-principal -->
</body>
</html>
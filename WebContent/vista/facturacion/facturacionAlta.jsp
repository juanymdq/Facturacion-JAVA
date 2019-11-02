<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="recursos/js/jquery-3.4.1.js"></script>
<script src="recursos/js/insertarArticulo.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<style type="text/css">
.container{
	font-size:12px;
}
.form-group{

}
.form-control{
	font-size:12px;
}
#tabla-articulos{
	font-size:10px;
	width:700px
	 border-collapse: separate;
  border-spacing:  2px;
}
#body-detalle{
	height:300px
}
</style>
<script>
var contfila;
window.onload = function(){
	  var fecha = new Date(); //Fecha actual
	  var mes = fecha.getMonth()+1; //obteniendo mes
	  var dia = fecha.getDate(); //obteniendo dia
	  var ano = fecha.getFullYear(); //obteniendo año
	  if(dia<10)
	    dia='0'+dia; //agrega cero si el menor de 10
	  if(mes<10)
	    mes='0'+mes //agrega cero si el menor de 10
	  document.getElementById('fechaActual').value=ano+"-"+mes+"-"+dia;	 
	 // contfila = 0;
	 
}

$(document).ready(function(){	
	$( "#cliente" ).change(function() {				 
         //OBTENGO EL NOMBRE DE LA CIUDAD SELECCIONADA
         tempidp = document.getElementById('cliente').value;         
         //OBTIENE LA CANTIDAD DE POSIVA TOTALES DEL DROP OCULTO
         var num_c = document.getElementById('selectIva').length;         
         //INICIA EL CONTADOR DE IVA
         var i=0;
         //ITERA POR EL COMBO OCULTO QUE CONTIENE TODOS LOS POSIVA
         var Encontro = false;
         while(i<=num_c && !Encontro){
            idp = document.getElementById('selectIva').options[i].value;
            idnom = document.getElementById('selectIva').options[i].text;            
            if(idp==tempidp){
                //alert('encontro');
                Encontro=true;
            }
            i++;
         }
         if(Encontro){                
             document.getElementById('posiva').value = idnom;
         }		  
	});	
});
//******************************************************************************
// Write on keyup event of keyword input element
 $(document).ready(function(){
	 $("#search").keyup(function(){		
	 _this = this;
	 // Show only matching TR, hide rest of them
		 $.each($("#tabla-articulos tbody tr"), function() {			 
			 if($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1)
			 	$(this).hide();
			 else
			 	$(this).show();
		 });
 	});
});
</script>
<title>Registrar Factura</title>
</head>
<body>
	<div class="container">
		<div class="form-principal">
			<center><h3>Registrar Factura</h3></center>	
			<hr>
			<center><a href="adminFactura?action=mostrar">Volver</a></center>
			<form action="adminFactura?action=register" method="post">
				<div class="form-row">				
					<div class="form-group col-md-2" style="width:40px;">
						<label>Id Factura</label>
						<input id="id" class="form-control" name ="id_factura" for="exampleFormControlInput1" type="text" placeholder="Ingrese id">  
			  		</div>		
					<div class="form-group col-md-1" style="width:20px;">
						<label>Tipo</label>
					  	<select name="tipo_factura" class="form-control">				  	  	
				  			<option value="a">A</option>
				  			<option value="b">B</option>
				  			<option value="c">C</option>			  			
				  					  	
				  		</select>	
					</div>
					<div class="form-group col-md-2" style="width:50px;">						
						<label>Fecha de alta</label>
						<input type="date" id="fechaActual" name fecha value=""  class="form-control">										
			  		</div>
			  	</div>	
			  	<div class="form-row">		  		
					<div class="form-group col-md-8" style="width:100px;">
					  <label>Cliente</label>
					  <select name="cliente" id="cliente" class="form-control" style="width:600px;">			  
				  	  	<c:forEach var="cli" items="${listaCli}">				  	  	
				  			<option value="${cli.posIva.id_posiva}">${cli.id_cliente} - ${cli.nombre} ${cli.apellido} - Domicilio: ${cli.domicilio} - ${cli.ciudad.nombre_ciudad} - CUIT: ${cli.cuit}</option>
				  		</c:forEach>				  			  	
					  </select>
					 </div>
					 <div class="form-group col-md-2">
					 	<label>Posici&oacute;n IVA</label>
					  	<input type="text" id="posiva" style="width:220px;" class="form-control" disabled="true">
					 </div>
					 <div class="form-group col-md-1" style="width:20px;">
					  <select id="selectIva" name="posiva" class="form-control" style="width:20px;  visibility:hidden">		<!--  -->	  
				  	  	<c:forEach var="pos" items="${listaPosiva}">				  	  	
				  			<option value="${pos.id_posiva}">${pos.nombre_posiva}</option>
				  		</c:forEach>				  			  	
					  </select>			  
					</div>	
						<!-- SELECT DE ARTICULOS OCULTO -->
			       <div class="form-group col-md-1" style="width:20px;">
					  <select id="selectArt" name="articulo" class="form-control" style="width:20px; ">		<!-- visibility:hidden  -->	  
				  	  	<c:forEach var="art" items="${listaArt}">				  	  	
				  			<option value="${art.id_articulo}">${art.id_articulo}-${art.nombre_articulo}-${art.precio}</option>
				  		</c:forEach>				  			  	
					  </select>			  
					</div>					
				</div> <!-- form-row -->
			  	
			  	<div class="form-row">
			  		
				  	<table id="tablaDetalle" class="table table-striped">
						<thead>		
							<tr>
							 <th></th>							
							 <th>ID ART.</th>
							 <th>CANT.</th>							 
							 <th>DETALLE</th>		 
							 <th>P. UNIT</th>
							 <th>IMPORTE</th>
							 <th colspan="2"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">Sel. Articulo</button></th>			
							</tr>
						</thead>						
						<tbody id="body-detalle"></tbody>						
					</table>
				  	
				<!-- ****************************** Modal ******************************************* -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					      	<h4 class="modal-title" id="myModalLabel">SELECCIONAR ARTICULO</h4>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>				        
					      </div>				      				       
					      <div class="modal-body">		
					      	<div class="form-group">
  								Buscar: <input type="text" class="form-control pull-right" style="width:30%" id="search" placeholder="Type to search table...">
							</div>		      	 
							
					      	 <table id="tabla-articulos" class="table-bordered table pull-right" cellspacing="0" style="width: 100%;">
								<thead>
									<tr role="row" class="clickable-row">
									 <th>ID</th>
									 <th>NOMBRE</th>
									 <th>CATEGORIA</th>									 
									 <th>PRECIO</th>
									 <th>CANTIDAD</th>
									 <th >Seleccionar</th>								 
									</tr>
								</thead>
								<c:forEach var="art" items="${listaArt}">
									<tbody>
										<tr>
											<td><c:out value="${art.id_articulo}"/></td>							
											<td><c:out value="${art.nombre_articulo}"/></td>
											<td><c:out value="${art.cat.getNombre_categoria()}"/></td>											
											<td><c:out value="${art.precio}"/></td>
											<td><c:out value="${art.cantidad}"/></td>	
											<td ><a class='l1s' title='seleccionar'><img src="recursos/imagenes/accept.png"></a></td>																
										</tr>
									</tbody>
								</c:forEach>
							</table>				      	 
					      	 		        
					      </div>					     
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>					       				        
					      </div><!-- modal-footer -->				      
					    </div><!-- modal-content -->
					  </div><!-- modal-dialog -->
					</div><!-- modal fade -->
				
			
				
				
				
				</div><!-- form-row  -->
		<!-- ****************************** Modal ******************************************* -->
			 
			  	
			  	
			  	
			  	
			  			
				<button type="submit" name="agregar" class="btn btn-lg btn-primary btn-block btn-signin">Guardar</button>
							
			</form>
		</div> <!-- form-principal -->
	</div> <!-- container -->
</body>
</html>
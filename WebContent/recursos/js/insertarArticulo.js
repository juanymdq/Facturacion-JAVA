//Devuelve el id del articulo al seleccionar del form modal emergente
//Se agrega al detalle el articulo seleccionado
$(document).ready(function() {
		$("a.l1s").click(function(){
		
		//Obtiene id articulo seleccionado	
		idart = $(this).parents("tr").find("td").eq(0).html();		
		art_nombre = $(this).parents("tr").find("td").eq(1).html();
		art_precio = $(this).parents("tr").find("td").eq(3).html();	
				
    	//contfila = contfila + 1;
    	
    	//en vartd pongo el id del articulo seleccionado
    	var fila;
    	var findFila = false;    	
    	var varCant = "td-cantidad" + idart;
    	var varImp = "td-importe" + idart;   
    	var varId = "td_idart" + idart;
    	var varPrecio = "td_precio" + idart;
    	varBorrar = "td_borrar" +idart;
    		
    	//obtiene la tabla
		var t_det = document.getElementById("tablaDetalle");		
		//obtiene todos los TD de la tabla
		var eTD=t_det.getElementsByTagName("td");
		//inicializo variable para recorrer las filas de la tabla
		var i=1;
		
		while(i<t_det.rows.length){//recorro por todas las filas
			//si la celda ID es igual al articulos seleccionado
			//emitira un mensaje de alerta informando que el articulo ya fue ingresado
			if(t_det.rows[i].cells[1].innerHTML==idart){
				findFila=true
			}					
			i++;
		}
		
		if(!findFila){			
   		//en var fila creo un fila nueva con los datos del articulo
   			fila="<td id='"+varBorrar+"'><img src='recursos/imagenes/eliminar.png' width:20px height></td><td id='"+varId+"'>"+idart+"</td><td id='"+varCant+"'></td><td>"+art_nombre+"</td><td id='"+varPrecio+"'>"+art_precio+"</td><td id='"+varImp+"'></td>";

   			var btn = document.createElement("TR");
	        //le inserto la info a al elemento
       	   	btn.innerHTML=fila;
	        //inserto en el body del detalle la info creada
       	    document.getElementById("body-detalle").appendChild(btn);
       	    //creo un elemento text
	       	var input = document.createElement("input");
       	    //le agrego propiedades
	 		input.type = "text";
	 		input.id = "textCant"+idart;	 		
	 		input.style = "width:40px";
	 		input.maxlength = "4";
	 		input.onblur = function(){	 
	 			//obtengo la cantidad
	 			var cant = document.getElementById(input.id).value;
	 			if(cant!=""){
    	 			//obtengo el id de la columna importe
	 				var id = document.getElementById(varId).innerHTML;	 				
    	 			var valor = "td-importe" + id;
    	 			//calculo el importe total=importe*cantidad
    	 			var precio = document.getElementById(varPrecio).innerHTML;
    	 			var importe = parseInt(cant) * precio;
    	 			//creo un elemento LABEL
    	 			var node = document.createElement('label');
    	 			//le asigno el importe formateado
    	 			var t = document.createTextNode("$"+importe); 
    	 			//Agrego el nodo al elemento LABEL
    	 			node.appendChild(t);
    	 			//Agrego a la tabla los datos
    	 			if(document.getElementById(valor).innerHTML!=""){    	 				
    	 				document.getElementById(valor).innerHTML = "";
    	 			}
   		 			document.getElementById(valor).appendChild(node);	
	 			}else{
	 				alert(valor);
	 				
	 				document.getElementById(valor).innerHTML = "";
	 			}
	 		};    	 	
			var parent = document.getElementById(varCant);
			//inserto el elemento text al td con id "td-text" mas el id de articulo
			parent.appendChild(input);
		}else{		
       		alert("el articulo ya se ha ingresado");
        } 
		
	});
});
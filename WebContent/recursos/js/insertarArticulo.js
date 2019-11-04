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
    	var varCant = "tdcantidad-" + idart;
    	var varImp = "tdimporte-" + idart;   
    	var varId = "tdidart-" + idart;
    	var varPrecio = "tdprecio-" + idart;
    	varBorrar = "tdborrar-" +idart;
    		
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
   			fila="<td id='"+varBorrar+"'><a class='eliminarArt' href='#'><img src='recursos/imagenes/eliminar.png'></a></td><td id='"+varId+"'>"+idart+"</td><td id='"+varCant+"'></td><td>"+art_nombre+"</td><td id='"+varPrecio+"'>"+art_precio+"</td><td id='"+varImp+"'></td>";
   			
   			var btn = document.createElement("TR");
	        //le inserto la info a al elemento
       	   	btn.innerHTML=fila;
	        //inserto en el body del detalle la info creada
       	    document.getElementById("body-detalle").appendChild(btn);
       	    //creo un elemento text
	       	var input = document.createElement("input");
       	    //le agrego propiedades
	 		input.type = "text";
	 		input.id = "textCant-"+idart;	 		
	 		input.style = "width:40px";	 		
	 		//input.onblur = verificaCantidad(input.id);
	 		//input.onkeypress = limitarCantidadNumeros;
	 		input.addEventListener('keydown', limitarCantidadNumeros);	 		
			var parent = document.getElementById(varCant);
			//inserto el elemento text al td con id "td-text" mas el id de articulo
			parent.appendChild(input);
		}else{		
       		alert("el articulo ya se ha ingresado");
        } 
	
	});
});

function verificaCantidad(val){	
	//alert(val);
	//obtengo la cantidad
	//var cant = document.getElementById(input.id).value;
/*	var cant = e.key.length;
	alert(cant);
	if(cant!=""){
		//obtengo el id de la columna importe
		var id = document.getElementById(varId).innerHTML;	 				
		var valor = "td-importe" + id;
		//calculo el importe total=importe*cantidad
		alert(varPrecio);
		var precio = document.getElementById(varPrecio).innerHTML;
		var importe = parseInt(cant) * precio;
		//creo un elemento LABEL
		var node = document.createElement('label');
		//le asigno el importe formateado
		var t = document.createTextNode("$"+importe); 
		//Agrego el nodo al elemento LABEL
		node.appendChild(t);
		document.getElementById(valor).appendChild(node);
		//Agrego a la tabla los datos
		if(document.getElementById(valor).innerHTML!=""){    	 				
			document.getElementById(valor).innerHTML = "";
		}
			
	}else{
		document.getElementById(valor).innerHTML = "";
	}
	*/
}


//******************************************************************************
//Busqueda de articulos en modal. Busca por todos los campos
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




//Limitar cantidad de caracteres
function limitarCantidadNumeros(e){
	var idfull = $(this).attr('id');	
	var tmp = idfull.split('-');
	var idInput = tmp[1];
	var valor = "tdimporte-" + idInput;
	var precio = document.getElementById('tdprecio-'+idInput).innerHTML;	
	
	if($(this).val()==""){		
		var numInput = e.key;
	}else{
		var numInput = $(this).val() + e.key;
	}	
	
	if(e.code=='Backspace'){	    	
		x = $(this).val();	
		t = x.substring(0,x.length-1);
		if(t==""){
			document.getElementById(valor).innerHTML = "";
		}else{
			var importe = 0;	    	        
	        importe = parseInt(t) * precio;
	        document.getElementById(valor).innerHTML = "$" + importe;
		}
	}

	//Limita a 4 el ingreso de digitos. Verifica que se ingresen solo numeros tmb
	
	if(e.key.length === 1){ // Evaluar si es un solo caracter
	    if(numInput.length <= 4 && !isNaN(parseFloat(e.key))){ 
	    	var importe = 0;	      
	        importe = parseInt(numInput) * precio;	        
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
	    }
	    return false;
	}
	
}

//Funcion para insertar solo numeros en campo cantidad
/*function KeyPressHandler(evt)
{
   evt = (evt) ? evt : window.event
   var charCode = (evt.which) ? evt.which : evt.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      status = "This field accepts numbers only."
      return false;
   }
   status = ""
   return true;
} */
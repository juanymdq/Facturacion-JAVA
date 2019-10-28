$(document).ready(function(){
	$("#btn_guardar").click(function() {		
		var id = document.getElementById("id_categoria").value;
		var nom = document.getElementById("nombre_categoria").value;
		//Este tipo e utiliza para saber si guardo desde el articulo o desde la categoria
		var tipo = 1;
		$.ajax({
			type: "POST",
			url: "adminCategoria?action=register",
			//data: $("#myModal").serialize()
			data: "id_categoria=" + id + "&" + "nombre_categoria=" + nom + "&tipoGuardado=" + tipo			
		});		
		alert("Se gurado la categoria: " + nom);
		$("#myModal").modal('hide');		
	});
})
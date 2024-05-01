				
		
		var urlParams = new URLSearchParams(window.location.search);
			var id = urlParams.get('id');
			var op = urlParams.get('op');
		
		
		function pintarFormulario(datos){
		  let html =`<h1>Edita tus datos:</h1>
        <hr>
       

        <form action="adminUpdate" method="post" id="registro">
        
        		<input type="hidden" id="id" name="id" value="${datos.id}">
        		
             <div class="form-group">
                <label for="nombre"><h3>Nombre:</h3></label>
                <input type="text" id="nombre" name="nombre" value="${datos.nombre}">
            </div>
            
            <div class="form-group">
                <label for="apellido1"><h3>Primer Apellido:</h3></label>
                <input type="text" id="primerApellido" name="primerApellido" value="${datos.apellido1}">
            </div>
            
            <div class="form-group">
                <label for="apellido2"><h3>Segundo Apellido:</h3></label>
                <input type="text" id="segundoApellido" name="segundoApellido" value="${datos.apellido2}">
            </div>
            
            <div class="form-group">
                <label for="email"><h3>Email:</h3></label>
                <input type="text" id="email" name="email" value="${datos.email}">
            </div>
            
            <div class="form-group">
                <label for="fechaBoda"><h3>Fecha de la Boda:</h3></label>
                <input type="text" id="fechaBoda" name="fechaBoda" value="${datos.fechaBoda}">
            </div>
            
            <div class="form-group">
                <label for="contraseña"><h3>Contraseña:</h3></label>
                <input type="text" id="contrasenia" name="contraseña" value="${datos.hashContrasenia}">
            </div>
           
            <button type="submit">Editar</button>

        </form>`
   
       document.getElementById("registro").innerHTML = html;
		}
		
		
		
	function llamada(id,op){
			
			fetch('adminUpdate?id='+id+"&op="+op)
			.then(response => response.json())
			.then(data => pintarFormulario(data))
		}
		

	
	
		
		
	window.onload = function () {
			
			llamada(id,op);
			
		}	
		

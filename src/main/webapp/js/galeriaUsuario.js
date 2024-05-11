
		
		function llamada (){
			fetch('adminRU')
			.then(response => response.json())
			.then(data=> pintarTabla(data))
		}
		
		
		function obtenerPermisoUsuario() {
   		 fetch('checkLogin')
        .then(response => {
            if (!response.ok) {
				
                throw new Error('Error al obtener el permiso del usuario');
            }
            
            return response.text();
        })
        .then(permiso => {
			
			actualizarNavbar(permiso);
			
            console.log('El permiso del usuario es:', permiso);
            // Puedes hacer algo con el permiso aquí
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

		var permiso = obtenerPermisoUsuario()
		
		
	
	
       function actualizarNavbar(permiso) {
    	
    	var elementoAccede = document.getElementById("accede");
    	
    		if (permiso) {
        		console.log("Este es el permiso desde la funcion actualizar Navbar"+permiso)
       			elementoAccede.innerHTML= "admin";
       			elementoAccede.href= "galeriaUsuarios.html"
        
    		} else {
      			elementoAccede.innerHTML= "accede";
      			elementoAccede.href= "Accede.html";
    		}	
		}


	
		
		function eliminar (id){
			if(confirm("¿Seguro que quieres eliminar los datos?")){
				fetch('adminEliminar?id='+id)
				.then(response => response.json())
				.then(data=> pintarTabla(data))
			}
		
			
			
		}
		
		
		
		function pintarTabla(datos){
		
		let html = `
            <table><tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido 1</th>
                    <th>Apellido 2</th>
                    <th>Email</th>
                    <th>Fecha de Boda</th>
                    <th>Permiso</th>
                </tr>`;
			
		for (var i=0; i<datos.length;i++){
			
			html += "<tr><td>"+datos[i].id+"</td>"
			html += "<td>"+datos[i].nombre+"</td>"
			html += "<td>"+datos[i].apellido1+"</td>"
			html += "<td>"+datos[i].apellido2+"</td>"
			html += "<td>"+datos[i].email+"</td>"
			html += "<td>"+datos[i].fechaBoda+"</td>"
			html += "<td>"+datos[i].permiso+"</td>"
			html += "<td><a href='editarUsuario.html?id="+datos[i].id+"'>Editar</a></td><td><a href='javascript:eliminar("+datos[i].id+")'>Eliminar</a></td>"
			
			html += "</tr>"
			
		}
		html +="</table>";
		
		
	
	document.getElementById("listado").innerHTML = html;	
				
		
		}
		
		/*window.onload = function(){
			llamada();
			 obtenerPermisoUsuario()
			
        	
		}*/
		document.addEventListener("DOMContentLoaded",function(){
		
			llamada()
			
		
		})
	

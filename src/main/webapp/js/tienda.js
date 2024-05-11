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

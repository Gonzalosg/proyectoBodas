

var urlParams = new URLSearchParams(window.location.search);
var id = urlParams.get('id');

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

		})
		.catch(error => {
			console.error('Error:', error);
		});
}

var permiso = obtenerPermisoUsuario()

function obtenerServicioUsuario() {
	fetch('checkServicio')
		.then(response => {

			if (!response.ok) {

				throw new Error('Error al obtener el servicio del usuario');
			}
		
			return response.text();
		})
		.then(servicio => {
			
			actualizarNavbarTienda(servicio);
			console.log(servicio);

		})
		.catch(error => {
			console.error('Error:', error);
		});
}

var servicio = obtenerServicioUsuario()

function actualizarNavbarTienda(servicio) {

	var elementoTienda = document.getElementById("tienda");
	
	

	if (servicio==0) {

		elementoTienda.innerHTML = "Shop";
		elementoTienda.href = "tienda.html"
		

	} else  {

		elementoTienda.innerHTML = "Tu Carro";
		elementoTienda.href = "galeriaTienda.html";

	}
}


function actualizarNavbar(permiso) {

	var elementoAccede = document.getElementById("accede");
	var elementoLogOut = document.getElementById("logout");


	if (permiso == 1) {

		elementoAccede.innerHTML = "Admin";
		elementoAccede.href = "galeriaUsuarios.html"
		elementoLogOut.style.display = "inline";

	} else if (permiso == 9) {
		elementoLogOut.style.display = "inline";
		elementoAccede.innerHTML = "Edita";

	} else {
		;
	}
}


function pintarFormulario(datos) {
	let html = `<h1>Edita tus datos:</h1>
        <hr>	
       

        <form action="adminUpdate" method="post" id="editarDatos">
        
        		<input type="hidden" id="id" name="id" value="${datos.id}">
        		
             <div class="form-group">
                <label for="nombre"><h3>Nombre:</h3></label>
                <input type="text" id="nombre" name="nombre" value="${datos.nombre}">
            </div>
            
            <div class="form-group">
                <label for="apellido1"><h3>Primer Apellido:</h3></label>
                <input type="text" id="apellido1" name="apellido1" value="${datos.apellido1}">
            </div>
            
            <div class="form-group">
                <label for="apellido2"><h3>Segundo Apellido:</h3></label>
                <input type="text" id="apellido2" name="apellido2" value="${datos.apellido2}">
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
                <input type="text" id="contrasenia" name="contrasenia" value="${datos.hashContrasenia}">
            </div>
           
           
            <a href='galeriaUsuarios.html?id="+datos[i].id+"'><button id"botonEditar" type="submit">Editar</button></a>

        </form>`

	document.getElementById("editarDatos").innerHTML = html;
}



function llamada(id) {

	fetch('adminUpdate?id=' + id)
		.then(response => response.json())
		.then(data => pintarFormulario(data))
}






window.onload = function() {

	llamada(id);

}


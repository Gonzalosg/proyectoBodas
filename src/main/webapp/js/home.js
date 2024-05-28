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



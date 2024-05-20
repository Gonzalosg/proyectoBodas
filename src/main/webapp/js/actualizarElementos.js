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



function actualizarNavbar(permiso) {

	var elementoAccede = document.getElementById("accede");
	var elementoLogOut = document.getElementById("logout");


	if (permiso == 1) {

		elementoAccede.innerHTML = "admin";
		elementoAccede.href = "galeriaUsuarios.html"
		elementoLogOut.style.display = "inline";

	} else if (permiso == 9) {
		elementoLogOut.style.display = "inline";
		elementoAccede.innerHTML = "Edita";

	} else {
		;
	}
}
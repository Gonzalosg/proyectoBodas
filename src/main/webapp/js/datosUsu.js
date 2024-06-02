function llamada() {
	fetch('listarUsuario')
		.then(response => response.json())
		.then(data => pintarTabla(data))
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
	var elementoLogOut = document.getElementById("logOut");
	elementoLogOut.style.display = "none";

	if (permiso == 1) {

		elementoAccede.innerHTML = "Admin";
		elementoAccede.href = "galeriaUsuarios.html"
		elementoLogOut.style.display = "inline";

	} else if (permiso == 9) {
		elementoLogOut.style.display = "inline";
		elementoAccede.innerHTML = "Edita";
		elementoAccede.href="datosUsu.html"

	} else {
		;
	}
}







function pintarTabla(datos) {

	let html = `
            <table><tr>
                   
                    <th>Nombre</th>
                    <th>Apellido 1</th>
                    <th>Apellido 2</th>
                    <th>Email</th>
                    <th>Fecha de Boda</th>
                   
                </tr>`;



	
	html += "<td>" + datos.nombre + "</td>"
	html += "<td>" + datos.apellido1 + "</td>"
	html += "<td>" + datos.apellido2 + "</td>"
	html += "<td>" + datos.email + "</td>"
	html += "<td>" + datos.fechaBoda + "</td>"
	
	html += "<td><a href='editarUsuario.html?id=" + datos.id + "'>Editar</a></td><td><a href='javascript:eliminar(" + datos.id + ")'>Eliminar</a></td>"

	html += "</tr>"


	html += "</table>";



	document.getElementById("datosUsu").innerHTML = html;


}


document.addEventListener("DOMContentLoaded", function() {

	llamada()


})


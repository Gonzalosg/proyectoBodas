

function llamada() {
	fetch('adminRU')
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

	} else {
		;
	}
}

function eliminar(id) {
	if (confirm("¿Seguro que quieres eliminar los datos?")) {
		fetch('adminEliminar?id=' + id)
			.then(response => response.json())
			.then(data => pintarTabla(data))
	}

}



function pintarTabla(datos) {

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

	for (var i = 0; i < datos.length; i++) {

		html += "<tr><td>" + datos[i].id + "</td>"
		html += "<td>" + datos[i].nombre + "</td>"
		html += "<td>" + datos[i].apellido1 + "</td>"
		html += "<td>" + datos[i].apellido2 + "</td>"
		html += "<td>" + datos[i].email + "</td>"
		html += "<td>" + datos[i].fechaBoda + "</td>"
		html += "<td>" + datos[i].permiso + "</td>"
		html += "<td><a href='editarUsuario.html?id=" + datos[i].id + "'>Editar</a></td><td><a href='javascript:eliminar(" + datos[i].id + ")'>Eliminar</a></td>"

		html += "</tr>"

	}
	html += "</table>";



	document.getElementById("listado").innerHTML = html;


}


document.addEventListener("DOMContentLoaded", function() {

	llamada()


})


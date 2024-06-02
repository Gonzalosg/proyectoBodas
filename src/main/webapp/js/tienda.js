function llamada() {
	fetch('listarServicios')
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




function pintarTabla(datos) {

	let html = `
            <table><tr>
                   
                    <th>Servicio</th>
                    <th>Descripcion 1</th>
                    <th>precio</th>   
                </tr>`;

	

		html += "<tr><td>" + datos.nombreServicio + "</td>"
		html += "<td>" + datos.descripcionServicio + "</td>"
		html += "<td>" + datos.precioServicio + "</td>"
		html += "</tr>"

	
	html += "</table>";



	document.getElementById("listadoTienda").innerHTML = html;


}



var servicio = obtenerServicioUsuario()
var permiso = obtenerPermisoUsuario()

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
		elementoAccede.href="datosUsu.html"

	} else {
		;
	}
}

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

document.addEventListener("DOMContentLoaded", function() {

	llamada()


})
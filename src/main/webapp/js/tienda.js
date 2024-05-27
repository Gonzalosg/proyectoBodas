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
			console.log(response.text)
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
var permiso = obtenerPermisoUsuario()

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

document.addEventListener("DOMContentLoaded", function() {

	llamada()


})
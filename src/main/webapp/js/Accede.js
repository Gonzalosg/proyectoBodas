function validarFormulario(){
		let nombre = document.getElementById('nombre').value;
		let apellido1 = document.getElementById('apellido1').value;
		let apellido2 = document.getElementById('apellido2').value;
		let email = document.getElementById('email').value;
		let fechaBoda = document.getElementById('fechaBoda').value;
		let contrasenia = document.getElementById('contrasenia').value;
		
		let valido = true;
		
		if(nombre==""){
			valido=false;
			document.getElementById('nombre').style.background= 'red';
		}
		
		if(apellido1==""){
			valido=false;
			document.getElementById('apellido1').style.background='red';
		}
		
		if(apellido2==""){
			valido=false;
			document.getElementById('apellido2').style.background='red';
		}
		
		if(email==""){
			valido=false;
			document.getElementById('email').style.background='red';
		}
		
		if(fechaBoda==""){
			valido=false;
			document.getElementById('fechaBoda').style.background='red';
		}
		
		if(contrasenia==""){
			valido=false;
			document.getElementById('contrasenia').style.background='red';
		}
		
		if (valido==true){
			document.getElementById("registro").submit();
		}
		
	}
	
	
	
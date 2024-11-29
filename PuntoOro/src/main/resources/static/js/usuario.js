document.addEventListener("DOMContentLoaded", function() {
    /*ELIMINA LOS 0 predeterminados en los tipos 'number' */
    const inputs = document.querySelectorAll("#estado");
    inputs.forEach(input => {
        if (input.value === "0") {
            input.value = "";
        }
    });


    
});

// Función para filtra la tabla jugadores
function filtrarTabla() {
    const input = document.getElementById('filtro').value.toLowerCase();
    const atributoIndex = document.getElementById('atributo').value;
    const filas = document.querySelectorAll('.tr-usuario');

    filas.forEach(fila => {
        const celda = fila.getElementsByTagName('td')[atributoIndex];
        if (celda && celda.textContent.toLowerCase().includes(input)) {
            fila.style.display = ''; // Mostrar fila
        } else {
            fila.style.display = 'none'; // Ocultar fila
        }
    });
}






// Función para editar el jugador
function editarJugador(email) {

    email = email.replace(/"/g, ''); 

    // Codificar el email para evitar problemas con caracteres especiales
    const encodedEmail = encodeURIComponent(email);


    fetch(`/usuario/editar/${encodedEmail}`)
        .then(response => {

            
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }


            return response.json();
        })

        
        .then(data => {
            document.getElementById('nombreCompletoEditar').value = data.nombreCompleto;
            document.getElementById('emailEditar').value = data.email;
            document.getElementById('telefonoEditar').value = data.telefono;
            document.getElementById('contrasenaEditar').value = data.contrasena; 
            document.getElementById('estadoEditar').value = data.estado;
            document.getElementById('roleEditar').value = data.role;
            
            document.querySelector('.form-overlay').style.display = 'flex';
        })
        .catch(error => console.error('Error:', error));
}


// Función para cerrar el formulario
function cerrarFormulario() {
    document.querySelector('.form-overlay').style.display = 'none';
}

// Función para guardar los cambios
function guardarCambios() {
    const formData = new FormData(document.getElementById('formActualizarJugador'));

    fetch('/usuario/actualizar', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al guardar los cambios');
        }
        return response.json(); // Si esperas una respuesta JSON
    })
    .then(data => {
        console.log(data);
        cerrarFormulario(); // Cierra el formulario después de guardar
    })
    .catch(error => console.error('Error:', error));
}



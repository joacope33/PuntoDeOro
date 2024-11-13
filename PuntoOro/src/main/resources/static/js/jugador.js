document.addEventListener("DOMContentLoaded", function() {
    /*ELIMINA LOS 0 predeterminados en los tipos 'number' */
    const inputs = document.querySelectorAll("#dni, #puntos, #calificacion, #categoria");
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
    const filas = document.querySelectorAll('.tr-jugador');

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
function editarJugador(dni) {
    fetch(`/jugador/editar/${dni}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('nombreCompletoEditar').value = data.nombreCompleto;
            document.getElementById('telefonoEditar').value = data.telefono;
            document.getElementById('categoriaEditar').value = data.categoria;
            document.getElementById('fechaEditar').value = data.fechaDeNacimiento; 
            document.getElementById('dniEditar').value = data.dni;
            document.getElementById('calificacionEditar').value = data.calificacion;
            document.getElementById('puntosEditar').value = data.puntos;
            document.getElementById('comentarioEditar').value = data.comentario;

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

    fetch('/jugador/actualizar', {
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



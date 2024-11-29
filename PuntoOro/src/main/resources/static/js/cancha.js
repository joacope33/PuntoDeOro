function toggleForm(formId) {
    // Ocultar todos los formularios
    const allForms = document.querySelectorAll('.turnos-form');
    allForms.forEach(form => {
        form.style.display = "none";
    });

    // Mostrar u ocultar el formulario seleccionado
    const form = document.getElementById(formId);
    if (form.style.display === "none" || form.style.display === "") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}


// Función para editar el jugador
function editarCancha(id) {
    fetch(`/canchas/editar/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('canchaIdEditar').value = id;
            document.getElementById('horarioAperturaEditar').value = data.horarioApertura;
            document.getElementById('horarioCierreEditar').value = data.horarioCierre;
            document.getElementById('disponibilidadEditar').checked = data.disponibilidad; // Marca el checkbox si es true
            document.getElementById('duracionEditar').value = data.duracion;
            document.getElementById('estadoEditar').value = data.estado;

            // Establecer el título con el ID de la cancha
            document.getElementById('tituloCancha').textContent = `Cancha ${id}`;
            console.log(data); // Agregar un log para ver los datos recibidos
            document.querySelector('#formularioEditar').style.display = 'flex';
        })
        .catch(error => console.error('Error:', error));
}


function cerrarFormularioEditar() {
    // Ocultar el formulario de agregar
    document.getElementById("formularioAgregar").style.display = "none";
}

function abrirFormularioAgregar() {
    // Mostrar el formulario de agregar
    document.getElementById("formularioAgregar").style.display = "flex";
}

function cerrarFormularioAgregar() {
    // Ocultar el formulario de agregar
    document.getElementById("formularioAgregar").style.display = "none";
}



// Función para cerrar el formulario
function cerrarFormulario() {
    document.querySelector('#formularioEditar').style.display = 'none';
}

// Función para guardar los cambios
function guardarCambios() {
    const formData = new FormData(document.getElementById('formActualizarCancha'));

    fetch('/canchas/actualizar', {
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

function eliminarCancha(id) {
    if (confirm("¿Estás seguro de que deseas eliminar esta cancha?")) {
        fetch(`/canchas/eliminar/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert("Cancha eliminada exitosamente.");
                window.location.reload(); // Recarga la página o redirige a otra
            } else {
                alert("Hubo un error al intentar eliminar la cancha.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("No se pudo completar la solicitud.");
        });
    }
}

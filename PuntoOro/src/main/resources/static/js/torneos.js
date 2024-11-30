// Añadir soporte de teclado
document.querySelector('.cruz').addEventListener('keydown', function(event) {
    if (event.key === 'Enter' || event.key === ' ') {
        cerrarFormulario();
    }
});




function mostrarModalCrear() {
    document.querySelector('#formularioAgregar').style.display = 'flex';
}

function mostrarModalEditar() {
    document.querySelector('#formularioEditarTorneo').style.display = 'flex';
}

// Función para editar el torneo
function editarTorneo(id) {
    fetch(`/torneos/editar/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('idTorneo').value = data.id;
            document.getElementById('fechaInicioEditar').value = data.fechaInicio;
            document.getElementById('fechaFinEditar').value = data.fechaFin;
            document.getElementById('formatoEditar').value = data.formato;
            document.getElementById('categoriaEditar').value = data.idCategoria;
            document.getElementById('estadoEditar').value = data.estado;

            // Mostrar el modal de edición
            document.querySelector('#formularioEditarTorneo').style.display = 'flex';
        })
        .catch(error => console.error('Error:', error));
}

    function guardarCambiosTorneo() {
        const form = document.querySelector('#formActualizarTorneo');
        const formData = new FormData(form);

        // Agregar manualmente el ID si no está cargado correctamente
        const idTorneo = document.querySelector('#idTorneo').value;
        if (!idTorneo) {
            console.error('ID de Torneo no definido');
            return;
        }
        formData.set('idTorneo', idTorneo); // Sobrescribe o agrega el valor manualmente

        fetch('/torneos/actualizar', {
            method: 'POST',
            body: formData,
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al guardar los cambios');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            cerrarFormulario();
        })
        .catch(error => console.error('Error:', error));
    }








    function eliminarTorneo(id, event) {
        event.preventDefault(); // Evita que el formulario se envíe de la manera tradicional
        event.stopPropagation(); // Detiene la propagación del evento
    
        if (confirm('¿Está seguro de que desea eliminar este torneo?')) {
            fetch(`/torneos/eliminar/${id}`, {
                method: 'DELETE',  // Usamos el método DELETE
                headers: {
                    'Content-Type': 'application/json', // Asegura que el contenido es JSON
                },
            })
            .then(response => {
                if (response.ok) {
                    location.reload(); // Recarga la página si la eliminación fue exitosa
                } else {
                    alert('No se pudo eliminar el torneo. Intenta nuevamente.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ocurrió un error al eliminar el torneo.');
            });
        }
    }
    


function verDetalles(id) {
  window.location.href = `/torneos/${id}`;
}


// Función para cerrar el formulario
function cerrarFormulario() {
  document.querySelector('#formularioAgregar').style.display = 'none';
  document.querySelector('#formularioEditarTorneo').style.display = 'none';
}


// Función para guardar los cambios
function guardarCambios() {
    const formData = new FormData(document.getElementById('form-guardar'));
 
    fetch('/torneos/actualizar', {  // Corregí la URL aquí
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
 
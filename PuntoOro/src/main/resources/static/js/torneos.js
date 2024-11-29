// Añadir soporte de teclado
document.querySelector('.cruz').addEventListener('keydown', function(event) {
    if (event.key === 'Enter' || event.key === ' ') {
        cerrarFormulario();
    }
});



function mostrarModalCrear() {
    document.querySelector('#formularioAgregar').style.display = 'flex';
}


// Función para editar el torneo
function editarTorneo(id) {
    fetch(`/torneos/editar/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json(); // Asegúrate de que el servidor devuelva un JSON válido
        })
        .then(data => {
            // Actualizamos los campos del formulario con los datos del torneo
            document.getElementById('fechaInicioEditar').value = data.fechaInicio;  // Asegúrate de que el formato de fecha sea compatible
            document.getElementById('fechaFinEditar').value = data.fechaFin;        // Asegúrate de que el formato de fecha sea compatible
            document.getElementById('categoriaEditar').value = data.categoria;
            document.getElementById('estadoEditar').value = data.estado;
            document.getElementById('idTorneo').value = data.id;

            // Mostramos el modal
            document.querySelector('#formularioEditarTorneo').style.display = 'flex';
        })
        .catch(error => console.error('Error:', error));
}

// Función para guardar los cambios del torneo
function guardarCambiosTorneo() {
    const formData = new FormData(document.getElementById('formActualizarTorneo'));

    fetch('/torneos/actualizar', {
        method: 'POST',
        body: formData, // Enviamos los datos del formulario
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al guardar los cambios');
        }
        return response.json(); // Si esperas una respuesta JSON
    })
    .then(data => {
        console.log(data);
        cerrarFormulario(); // Cierra el formulario después de guardar los cambios
    })
    .catch(error => console.error('Error:', error));
}




function eliminarTorneo(id) {
  event.stopPropagation();
  if (confirm('¿Está seguro de que desea eliminar este torneo?')) {
      fetch(`/torneos/eliminar/${id}`, {
          method: 'DELETE'
      }).then(response => {
          if (response.ok) {
              location.reload();
          }
      });
  }
}


function verDetalles(id) {
  window.location.href = `/torneos/${id}`;
}


// Función para cerrar el formulario
function cerrarFormulario() {
  document.querySelector('.form-overlay').style.display = 'none';
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
 
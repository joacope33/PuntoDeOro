function mostrarModalCrear() {
  document.querySelector('.form-overlay').style.display = 'flex';
}

function editarTorneo(id) {
  event.stopPropagation();
  // Cargar datos del torneo
  fetch(`/torneos/${id}`)
      .then(response => response.json())
      .then(torneo => {
          document.getElementById('modalTitle').textContent = 'Editar Torneo';
          document.getElementById('torneoId').value = torneo.id;
          document.getElementById('fechaInicio').value = torneo.fechaInicio;
          document.getElementById('fechaFin').value = torneo.fechaFin;
          document.getElementById('categoria').value = torneo.categoria;
          document.getElementById('estado').value = torneo.estado;
          document.getElementById('torneoModal').style.display = 'block';
      });
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
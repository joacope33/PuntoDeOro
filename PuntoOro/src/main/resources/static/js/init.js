function redireccionIndex() {
    window.location.href = '/home';
  }

  const modal = document.getElementById('modal');
  const btnAbrir = document.getElementById('btn-login-abrir');
  const btnCerrar = document.getElementById('btn-login-cerrar');

  // Función para abrir el modal
  btnAbrir.addEventListener('click', () => {
      modal.showModal();
  });

  // Función para cerrar el modal
  btnCerrar.addEventListener('click', () => {
      modal.close();
  });

  // Cerrar el modal al hacer clic fuera del contenido
  modal.addEventListener('click', (event) => {
      if (event.target === modal) {
          modal.close();
      }
  });

  function actualizarCopyright() {
    const footer = document.getElementById('Copyright');
    const fechaActual = new Date();
    const opciones = { year: 'numeric' };
    footer.innerHTML = `Copyright &copy; ${fechaActual.toLocaleDateString('es-ES', opciones)} Punto de Oro`;
}

// Llamar a la función para que se ejecute al cargar la página
document.addEventListener('DOMContentLoaded', actualizarCopyright);
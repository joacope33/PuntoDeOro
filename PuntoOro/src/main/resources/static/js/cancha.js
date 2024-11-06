// Función para abrir y cerrar el formulario
function toggleForm(formId) {
    const form = document.getElementById(formId);
    const forms = document.querySelectorAll('.turnos-form');

    // Cerrar todos los formularios antes de abrir el seleccionado
    forms.forEach(f => {
        if (f !== form) {
            f.style.display = 'none';
        }
    });

    // Alternar la visibilidad del formulario actual
    form.style.display = (form.style.display === "none" || form.style.display === "") ? "block" : "none";
}

// Evitar que el formulario se cierre cuando se hace clic dentro de él
document.querySelectorAll('.turnos-form').forEach(form => {
    form.addEventListener('click', function(event) {
        event.stopPropagation(); // Detiene la propagación del evento de clic
    });
});

// Cerrar el formulario si el usuario hace clic fuera de él
document.addEventListener('click', function(event) {
    const forms = document.querySelectorAll('.turnos-form');
    forms.forEach(form => {
        if (form.style.display === "block" && !form.contains(event.target) && !event.target.closest('.cancha')) {
            form.style.display = "none"; // Ocultar el formulario si se hace clic fuera de él
        }
    });
});

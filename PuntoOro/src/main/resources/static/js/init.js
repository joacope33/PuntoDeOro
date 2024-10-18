document.addEventListener('DOMContentLoaded', function() {
    function redireccionIndex() {
        window.location.href = '/home';
    }

    
    const btnAbrir = document.getElementById('btn-login-abrir');
    

    // Asegúrate de que los botones existen antes de agregar el listener
    if (btnAbrir) {
        btnAbrir.addEventListener('click', () => {
            ;
        });
    } else {
        console.error("Botón de abrir modal no encontrado");
    }

    
    function actualizarCopyright() {
        const footer = document.getElementById('Copyright');
        const fechaActual = new Date();
        const opciones = { year: 'numeric' };
        footer.innerHTML = `Copyright &copy; ${fechaActual.toLocaleDateString('es-ES', opciones)} Punto de Oro`;
    }

    // Llamar a la función para que se ejecute al cargar la página
    actualizarCopyright();

    // Configuración del calendario
    var calendarEl = document.getElementById('calendar');
    
    // Obtener la fecha actual
    const fechaActual = new Date();
    const cuatroSemanasAntes = new Date(fechaActual);
    cuatroSemanasAntes.setDate(fechaActual.getDate() - 28); // Restar 28 días (4 semanas)
    
    const cuatroSemanasDespues = new Date(fechaActual);
    cuatroSemanasDespues.setDate(fechaActual.getDate() + 28); // Sumar 28 días (4 semanas)

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'timeGridWeek', // Cambiar a 'timeGridWeek' para vista de semana
        locale: 'es', // Configurar el idioma a español
        headerToolbar: {
            left: 'prev,next today', // Botones de navegación
            center: 'Calendario de Turnos', // Título del calendario
            right: 'dayGridMonth,timeGridWeek,timeGridDay ' // Vistas disponibles
        },
        validRange: {
            start: cuatroSemanasAntes, // Establecer el inicio de la vista
            end: cuatroSemanasDespues // Establecer el fin de la vista
        },
        editable: true, // Permitir edición
        selectable: true, // Permitir selección
        slotMinTime: '12:00:00', // Hora mínima que se mostrará en el calendario
        slotMaxTime: '23:59:00', // Hora máxima que se mostrará en el calendario
        events: [
            { title: 'Ana', start: '2024-10-01T12:00:00', end: '2024-10-01T13:00:00' },
            { title: 'Luis', start: '2024-10-01T12:00:00', end: '2024-10-01T13:00:00' },
            { title: 'Marta', start: '2024-10-01T12:00:00', end: '2024-10-01T13:00:00' },
            { title: 'Pedro', start: '2024-10-01T13:00:00', end: '2024-10-01T14:00:00' },
            { title: 'Andrés', start: '2024-10-02T12:00:00', end: '2024-10-02T13:00:00' },
            // Añade el resto de eventos aquí...
        ],
        select: function(info) {
            // Mostrar el modal y prellenar las fechas seleccionadas
            document.getElementById('event-start').value = info.startStr;
            document.getElementById('event-end').value = info.endStr;
            modal.showModal();
        },
        eventClick: function(info) {
            alert('Evento: ' + info.event.title); // Alertar el título del evento
        }
    });

    // Manejar el envío del formulario para agregar un evento
    eventForm.addEventListener('submit', function(e) {
        e.preventDefault(); // Prevenir el comportamiento por defecto del formulario

        const title = document.getElementById('event-title').value;
        const start = document.getElementById('event-start').value;
        const end = document.getElementById('event-end').value;

        calendar.addEvent({
            title: title, // Título del nuevo evento
            start: start, // Fecha de inicio del nuevo evento
            end: end, // Fecha de fin del nuevo evento
            allDay: true // Evento de todo el día
        });

        modal.close(); // Cerrar el modal
        eventForm.reset(); // Reiniciar el formulario
    });

    calendar.render(); // Renderizar el calendario
});
calendar.setOption('locale', 'es');

/*Redirecciones*/ 
function redireccionLogin() {
    window.location.href = '/login';
}
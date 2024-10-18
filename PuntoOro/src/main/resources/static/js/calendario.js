document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('modal');
    const eventForm = document.getElementById('event-form');
    const btnCerrar = document.getElementById('btn-login-cerrar');
    let calendar;

    // Inicialización del calendario
    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        select: function(info) {
            // Mostrar el formulario en el modal al seleccionar una fecha
            modal.showModal();
            document.getElementById('event-start').value = info.startStr;
            document.getElementById('event-end').value = info.endStr;
        },
        eventClick: function(info) {
            alert('Evento: ' + info.event.title); // Muestra alerta al hacer clic en un evento
        }
    });

    calendar.render(); // Renderiza el calendario

    // Manejar el cierre del modal
    btnCerrar.addEventListener('click', function() {
        modal.close();
    });

    // Manejar el envío del formulario para agregar un evento
    eventForm.addEventListener('submit', function(e) {
        e.preventDefault(); // Prevenir el comportamiento por defecto del formulario

        const title = document.getElementById('event-title').value;
        const start = document.getElementById('event-start').value;
        const end = document.getElementById('event-end').value;

        calendar.addEvent({
            title: title,
            start: start,
            end: end,
            allDay: true
        });

        modal.close(); // Cerrar el modal
        eventForm.reset(); // Reiniciar el formulario
    });
});
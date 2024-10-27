document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('modal');
    const eventForm = document.getElementById('event-form');
    const btnCerrar = document.getElementById('btn-login-cerrar');
    let calendar;
    let selectedDate = null; // Variable para almacenar la fecha seleccionada

    // Inicialización del calendario
    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'es', // Configurar el idioma a español
        initialView: 'dayGridMonth',
        selectable: true,
        customButtons: {
            myCustomButton: {
                text: 'Agregar Turno!',
                click: function() {
                    if (selectedDate) {
                        // Mostrar el formulario en el modal con la fecha seleccionada
                        modal.showModal();
                        document.getElementById('event-start').value = selectedDate.startStr;
                        document.getElementById('event-end').value = selectedDate.endStr;
                    } else {
                        alert('Por favor, selecciona una fecha primero.');
                    }
                }
            }
        },
        headerToolbar: {
            left: 'prev,next myCustomButton',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        views: {
            dayGridMonth: { buttonText: 'Mes' },
            timeGridWeek: { buttonText: 'Semana' },
            timeGridDay: { buttonText: 'Día' }
        },
        dateClick: function(info) {
            // Al hacer clic en una fecha, se almacena la fecha seleccionada
            selectedDate = info; // Guardar la fecha seleccionada
            //alert('Fecha seleccionada: ' + info.dateStr); // Notificar al usuario
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
        selectedDate = null; // Reiniciar la fecha seleccionada
    });
});

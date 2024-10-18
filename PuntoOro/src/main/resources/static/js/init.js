document.addEventListener('DOMContentLoaded', function() {
    function redireccionIndex() {
        window.location.href = '/home';
    }

    const modal = document.getElementById('modal');
    const btnAbrir = document.getElementById('btn-login-abrir');
    const btnCerrar = document.getElementById('btn-login-cerrar');
    const eventForm = document.getElementById('event-form'); // Asegúrate de que este ID exista en tu HTML

    // Asegúrate de que los botones existen antes de agregar el listener
    if (btnAbrir) {
        btnAbrir.addEventListener('click', () => {
            modal.showModal();
        });
    } else {
        console.error("Botón de abrir modal no encontrado");
    }

    if (btnCerrar) {
        btnCerrar.addEventListener('click', () => {
            modal.close();
        });
    } else {
        console.error("Botón de cerrar modal no encontrado");
    }

    // Cerrar el modal al hacer clic fuera del contenido
    if (modal) {
        modal.addEventListener('click', (event) => {
            if (event.target === modal) {
                modal.close();
            }
        });
    } else {
        console.error("Modal no encontrado");
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

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        events: [
            {
                title: 'Evento 1',
                start: '2024-10-20',
                end: '2024-10-20'
            },
            {
                title: 'Evento 2',
                start: '2024-10-25',
                allDay: true
            },
            {
                title: 'Evento 3',
                start: '2024-10-29T10:00:00',
                end: '2024-10-29T12:00:00'
            }
        ],
        editable: true,
        selectable: true,
        select: function(info) {
            // Mostrar el modal y prellenar las fechas seleccionadas
            document.getElementById('event-start').value = info.startStr;
            document.getElementById('event-end').value = info.endStr;
            modal.showModal();
        },
        eventClick: function(info) {
            alert('Evento: ' + info.event.title);
        }
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

    calendar.render();
});

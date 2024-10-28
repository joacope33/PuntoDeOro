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
        initialView: 'timeGridWeek', // Cambiar la vista inicial a la vista semanal
        selectable: true,
        customButtons: {
            myCustomButton: {
                text: 'Agregar Turno!',
                click: function() {
                    if (selectedDate) {
                        modal.showModal();
                        // Mostrar la fecha y hora de inicio en un campo de solo lectura
                        document.getElementById('event-start').value = selectedDate.date.toLocaleString(); // Formato local
                    } else {
                        alert('Por favor, selecciona una fecha primero.');
                    }
                }
            }
        },
        headerToolbar: {
            left: 'prev,next myCustomButton',
            center: 'title',
            right: 'timeGridWeek' // Eliminar otros botones y dejar solo "Semana"
        },
        views: {
            timeGridWeek: {
                buttonText: 'Semana',
                slotDuration: '01:00:00', // Duración de los intervalos de tiempo (1 hora)
                slotLabelInterval: '01:00', // Intervalo de etiquetas
                minTime: '07:00:00', // Hora de inicio
                maxTime: '02:00:00'  // Hora de fin (del día siguiente)
            }
        },
        dateClick: function(info) {
            // Al hacer clic en una fecha, se almacena la fecha seleccionada
            selectedDate = info; // Guardar la fecha seleccionada
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

        if (!selectedDate) {
            alert('Por favor, selecciona una fecha primero.');
            return; // Si no hay fecha seleccionada, salir de la función
        }

        const title = document.getElementById('event-title').value;
        const startDateTime = new Date(selectedDate.date); // Obtener la fecha y hora seleccionadas

        // Calcular la fecha de fin sumando 1 hora al inicio
        const endDateTime = new Date(startDateTime);
        endDateTime.setHours(endDateTime.getHours() + 1);

        calendar.addEvent({
            title: title,
            start: startDateTime.toISOString(),
            end: endDateTime.toISOString(),
            allDay: false // Marcar como evento de tiempo específico
        });

        modal.close(); // Cerrar el modal
        eventForm.reset(); // Reiniciar el formulario
        selectedDate = null; // Reiniciar la fecha seleccionada
    });
});

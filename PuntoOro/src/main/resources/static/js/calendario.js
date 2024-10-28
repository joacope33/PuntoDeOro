document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('modal');
    const eventForm = document.getElementById('event-form');
    const btnCerrar = document.getElementById('btn-login-cerrar');
    let calendar;
    let selectedDate = null;

    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'es',
        initialView: 'timeGridWeek',
        selectable: true,
        customButtons: {
            myCustomButton: {
                text: 'Agregar Turno!',
                click: function() {
                    if (selectedDate) {
                        modal.showModal();
                        document.getElementById('event-start').value = selectedDate.date.toLocaleString();
                    } else {
                        alert('Por favor, selecciona una fecha primero.');
                    }
                }
            }
        },
        headerToolbar: {
            left: 'prev,next myCustomButton',
            center: 'title',
            right: 'timeGridWeek'
        },
        views: {
            timeGridWeek: {
                buttonText: 'Semana',
                slotDuration: '01:30:00', // Duración de los intervalos de tiempo (1 hora y 30 minutos)
                slotLabelInterval: '01:30', // Intervalo de etiquetas de hora
                slotLabelFormat: { 
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false // Formato de 24 horas
                },
                slotMinTime: '07:00:00', // Cambiado
                //slotMaxTime: '22:00:00', // Cambiado
                dayHeaderFormat: { 
                    weekday: 'long' // Nombre del día completo
                },
                allDaySlot: false // Desactivar el slot de todo el día
            }
        },
        dateClick: function(info) {
            selectedDate = info; // Guardar la fecha seleccionada
        },
        eventClick: function(info) {
            alert('Evento: ' + info.event.title);
        }
    });

    calendar.render(); // Renderiza el calendario

    btnCerrar.addEventListener('click', function() {
        modal.close();
    });

    eventForm.addEventListener('submit', function(e) {
        e.preventDefault();

        if (!selectedDate) {
            alert('Por favor, selecciona una fecha primero.');
            return;
        }

        const title = document.getElementById('event-title').value;
        const startDateTime = new Date(selectedDate.date);
        const endDateTime = new Date(startDateTime);
        endDateTime.setHours(endDateTime.getHours() + 1); // Sumar 1 hora
        endDateTime.setMinutes(endDateTime.getMinutes() + 30); // Sumar 30 minutos

        // Verificar solapamiento
        const isOverlap = calendar.getEvents().some(event => {
            return (startDateTime < event.end && endDateTime > event.start);
        });

        if (isOverlap) {
            alert('Este evento se solapa con otro evento existente.');
            return;
        }

        calendar.addEvent({
            title: title,
            start: startDateTime.toISOString(),
            end: endDateTime.toISOString(),
            allDay: false
        });

        modal.close();
        eventForm.reset();
        selectedDate = null;
    });
});

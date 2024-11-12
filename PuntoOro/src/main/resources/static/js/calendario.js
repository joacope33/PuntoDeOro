document.addEventListener('DOMContentLoaded', async function() {
    const modal = document.getElementById('modal');
    const eventForm = document.getElementById('event-form');
    const btnCerrar = document.getElementById('btn-login-cerrar');
    let calendar;
    let selectedDate = null;
    let canchaData = null;
    modal.close();

    // Obtener los detalles de la cancha al cargar la página
    const canchaId = 1; // Reemplaza con el ID de la cancha que necesitas
    try {
        const response = await fetch(`/cancha/${canchaId}`);
        if (response.ok) {
            canchaData = await response.json();
            console.log('Datos de la cancha:', canchaData);
        } else {
            console.error('Error al obtener los datos de la cancha:', response.status);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }

    const calendarEl = document.getElementById('calendar');
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
                slotDuration: canchaData ? canchaData.duracion : '01:30:00', // Duración de los intervalos de tiempo usando el valor de cancha
                slotLabelInterval: canchaData ? canchaData.duracion : '01:30',
                slotLabelFormat: {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                },
                slotMinTime: canchaData ? canchaData.horarioApertura : '07:00:00',
                //slotMaxTime: canchaData ? canchaData.horarioCierre : '23:00:00',
                dayHeaderFormat: {
                    weekday: 'long'
                },
                allDaySlot: false
            }
        },
        dateClick: function(info) {
            selectedDate = info; // Guardar la fecha seleccionada
        },
        eventClick: function(info) {
            alert('Evento: ' + info.event.title);
        },
        events: function(info, successCallback, failureCallback) {
            // Hacer una solicitud a la API para obtener los turnos
            fetch(`/calendario/${canchaId}`)
                .then(response => response.json())
                .then(data => {
                    const events = data.map(turno => {
                        const startDate = turno.dia + 'T' + turno.hora;
                        const endDate = new Date(turno.dia + 'T' + turno.hora);
                        endDate.setHours(endDate.getHours() + parseInt(canchaData.duracion.split(':')[0]));
                        endDate.setMinutes(endDate.getMinutes() + parseInt(canchaData.duracion.split(':')[1]));
                        const endDateString = endDate.toISOString();

                        return {
                            title: ` ${turno.jugadores && turno.jugadores[0] ? turno.jugadores[0].nombreCompleto : 'Sin nombre'}`,
                            start: startDate,
                            end: endDateString,
                            description: `Asistencia: ${turno.asistencia} | Estado: ${turno.estado}`,
                            extendedProps: {
                                tipoTurno: turno.tipoTurno,
                                partido: turno.partido
                            }
                        };
                    });
                    successCallback(events);
                })
                .catch(error => {
                    console.error('Error fetching events:', error);
                    failureCallback(error);
                });
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
        endDateTime.setHours(endDateTime.getHours() + parseInt(canchaData.duracion.split(':')[0]));
        endDateTime.setMinutes(endDateTime.getMinutes() + parseInt(canchaData.duracion.split(':')[1]));

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

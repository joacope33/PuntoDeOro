document.addEventListener('DOMContentLoaded', async function() {
    /*CONSTANTES*/
    //Constante para ventana modal de agregar
    const playerModal = document.getElementById('player-modal');
    //Constante para agergar jugadores
    const btnAddPlayer = document.getElementById('btn-add-player');

    //Constante de ventana modal para dar de alta turno.
    const modal = document.getElementById('modal');
    //Constante de eventos de formularios.
    const eventForm = document.getElementById('event-form');
    //Constante de cerrar ventana modal.
    const btnCerrar = document.getElementById('btn-close-modal');
    //constante para agregarjugadores
    const addPlayerBtn = document.getElementById('add-player-btn');
    //constante para seleccionar jugadores
    const playerSelect = document.getElementById('select-player');




    /*VARIABLES*/
    //Variable calendario.
    let calendar;
    //Variable que guarda la seleccion de fecha segun el grid elegido.
    let selectedDate = null;
    //Variable que guarda los valores de la cancha.
    let canchaData = null;

    modal.close();

    // Cargar jugadores al abrir el modal
    async function loadPlayers() {
        try {
            const response = await fetch('/jugador/todos');
            if (response.ok) {
                const players = await response.json();
                console.log('Jugadores cargados:', players);  // Asegúrate de que los datos lleguen aquí
                playerSelect.innerHTML = '';  // Limpia las opciones previas
                players.forEach(player => {
                    const option = document.createElement('option');
                    option.value = player.id;  // Usa el id del jugador
                    option.text = player.nombreCompleto;  // Usa el nombre completo del jugador
                    playerSelect.appendChild(option);
                });
            } else {
                console.error('Error al obtener jugadores:', response.status);
            }
        } catch (error) {
            console.error('Error al cargar jugadores:', error);
        }
    }
    loadPlayers();

    // Evento de botón para agregar jugador rápidamente
    addPlayerBtn.addEventListener('click', async () => {
        const newPlayerName = prompt("Nombre completo del jugador:");
        if (newPlayerName) {
            try {
                // Hacemos una solicitud POST para agregar un nuevo jugador.
                const response = await fetch('/jugador/guardar', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'jugador.nombreCompleto': newPlayerName,
                        'jugador.telefono': '', // Si se requiere más información, agregar los campos necesarios
                        'jugador.categoria': '', 
                        'jugador.fechaDeNacimiento': '',
                        'jugador.dni': '',
                        'jugador.calificacion': '',
                        'jugador.puntos': '',
                        'jugador.comentario': ''
                    })
                });
    
                if (response.ok) {
                    alert("Jugador agregado con éxito");
                    loadPlayers();  // Recargar la lista de jugadores tras agregar
                } else {
                    alert("Error al agregar el jugador");
                }
            } catch (error) {
                console.error('Error al agregar el jugador:', error);
                alert("Hubo un error al agregar el jugador.");
            }
        }
    });











    // Obtener los detalles de la cancha al cargar la página.
    const canchaId = 1; // Reemplaza con el ID de la cancha que necesitas
    try {
        // 1. Define una constante y realiza una solicitud
        const response = await fetch(`/cancha/${canchaId}`);
        
        if (response.ok) {
            //Si la solicitud fue exitosa convierte la respuesta en un formato json
            canchaData = await response.json();
            //E imprime los valores de la cancha en consola.
            console.log('Datos de la cancha:', canchaData);

        } else {
            console.error('Error al obtener los datos de la cancha:', response.status);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
    //trae del html calendario, el objeto calendar por su id="calendar"
    const calendarEl = document.getElementById('calendar');
    
    // Crea e inicia un objeto de calendario usando la biblioteca FullCalendar y le asigna configuraciones.
    calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'es',  // Configura el idioma del calendario a español.
        initialView: 'timeGridWeek', // Vista inicial en formato de cuadrícula semanal.
        selectable: true, // Permite seleccionar rangos de tiempo en el calendario.

         // Agrega un botón personalizado para agregar un turno.
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
        //Metodos para botones y titulo
        headerToolbar: {
            left: 'prev,next myCustomButton',
            center: 'title',
            right: 'timeGridWeek'
        },
        //ajusta la vista views
        views: {
            timeGridWeek: {
                buttonText: 'Semana',
                slotDuration: canchaData ? canchaData.duracion : '01:00:00',
                slotLabelInterval: canchaData ? canchaData.duracion : '01:00',
                slotLabelFormat: {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                },
                slotMinTime: canchaData ? canchaData.horarioApertura : '07:00:00',
                dayHeaderFormat: {
                    weekday: 'long'
                },
                allDaySlot: false
            }
        },
    
        dateClick: function(info) {
            selectedDate = info;
        },
        eventClick: function(info) {
            alert('Evento: ' + info.event.title);
        },
        events: function(info, successCallback, failureCallback) {
            fetch(`/calendario/${canchaId}`)
                .then(response => response.json())
                .then(data => {
                    const events = data.map(turno => {
                        const startDate = turno.dia + 'T' + turno.hora;
                        const endDate = new Date(turno.dia + 'T' + turno.hora);
                        endDate.setHours(endDate.getHours() + parseInt(canchaData.duracion.split(':')[0]));
                        endDate.setMinutes(endDate.getMinutes() + parseInt(canchaData.duracion.split(':')[1]));
                        const endDateString = endDate.toISOString();

                        // Asignar color según el estado del turno
                        let color;
                        switch (turno.estado) {
                            case 'DISPONIBLE':
                                color = 'green';
                                break;
                            case 'OCUPADA':
                                color = 'red';
                                break;
                            case 'TURNO_FIJO':
                                color = 'yellow';
                                break;
                            case 'RESERVADA_PARA_TORNEO':
                                color = 'gray';
                                break;
                            default:
                                color = 'blue'; // Color por defecto si el estado es desconocido
                        }

                        return {
                            title: ` ${turno.jugadores && turno.jugadores[0] ? turno.jugadores[0].nombreCompleto : 'Sin nombre'}`,
                            start: startDate,
                            end: endDateString,
                            description: `Asistencia: ${turno.asistencia} | Estado: ${turno.estado}`,
                            extendedProps: {
                                tipoTurno: turno.tipoTurno,
                                partido: turno.partido
                            },
                            backgroundColor: color, // Asigna el color al evento
                            borderColor: color // Opcional: establece el mismo color para el borde
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

    calendar.render();

    btnCerrar.addEventListener('click', function() {
        modal.close();
    });

    btnCloseModal.addEventListener('click', () => {
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

    eventForm.addEventListener('submit', async function(e) {
        e.preventDefault();

        const selectedPlayerId = playerSelect.value;
        const startDateTime = document.getElementById('event-start').value;
        const turnType = document.getElementById('turn-type').value;

        // Guardar el turno en la DB
        try {
            const response = await fetch('/turnos/todos', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    idjugador: selectedPlayerId,
                    dia: startDateTime,
                    tipo_turno: turnType
                })
            });
            if (response.ok) {
                alert("Turno agregado con éxito");
                modal.close();
                eventForm.reset();
            } else {
                alert("Error al agregar el turno");
            }
        } catch (error) {
            console.error('Error al guardar el turno:', error);
        }
    });

});

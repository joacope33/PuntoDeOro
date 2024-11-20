document.addEventListener('DOMContentLoaded', async function() {
    /*CONSTANTES*/
    //Constante para ventana modal de agregar
    const playerModal = document.getElementById('player-modal');
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
    //cargar los jugadores en la opcion
    loadPlayers();


    //mostrar formulario para
    addPlayerBtn.addEventListener('click', () => {
        playerModal.showModal(); // Abre el modal cuando se hace clic en el botón
    });

    // Evento para manejar el envío del formulario en el modal
    document.getElementById('add-player-form').addEventListener('submit', async (event) => {
        event.preventDefault();
    
        // Captura los valores de los campos del formulario dentro del modal
        const newPlayerName = document.getElementById('player-name').value;
        const playerPhone = document.getElementById('player-phone').value;
        const playerCategory = document.getElementById('player-category').value;
        const playerBirthdate = document.getElementById('player-birthday').value;
        const playerDni = document.getElementById('player-dni').value;
        const playerRating = document.getElementById('player-rating').value;
        const playerPoints = document.getElementById('player-points').value;
        const playerComment = document.getElementById('player-comment').value;
        const csrfToken = document.querySelector('input[name="_csrf"]').value;
        // Verifica si algún campo está vacío antes de enviarlo


    
        // Verifica que el token CSRF también esté presente
        if (!csrfToken) {
            console.error("El token CSRF no se encontró");
        }
        try {

            const formData = new URLSearchParams();
            formData.append("nombreCompleto", newPlayerName);
            formData.append("telefono", playerPhone);
            formData.append("categoria", playerCategory);
            formData.append("fechaDeNacimiento", playerBirthdate);
            formData.append("dni", playerDni);
            formData.append("calificacion", playerRating);
            formData.append("puntos", playerPoints);
            formData.append("comentario", playerComment);

            const response = await fetch('/jugador/guardar', {
                method: 'POST',
                headers: {  
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-CSRF-TOKEN': csrfToken
                },
                body: formData
            });
        
    
            if (response.ok) {
                alert("Jugador agregado con éxito");
                document.getElementById('player-modal').close();
                document.getElementById('add-player-form').reset();

            } else {
                const errorData = await response.json();
                console.error('Error al agregar jugador:', errorData);
                alert("Error al agregar el jugador: " + (errorData.message || response.statusText));
            }
        } catch (error) {
            console.error('Error al agregar el jugador:', error);
            alert("Hubo un error al agregar el jugador.");
        }
    });
    const btnClosePlayerModal = document.getElementById('btn-close-player-modal');
    btnClosePlayerModal.addEventListener('click', () => {
        playerModal.close();
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
        events: async function(info, successCallback, failureCallback) {
            try {
                const response = await fetch(`/calendario/${canchaId}`);
                if (response.ok) {
                    const data = await response.json();
                    const events = data.map(turno => {
                        const startDate = turno.dia + 'T' + turno.hora;
                        const endDate = new Date(turno.dia + 'T' + turno.hora);
                        endDate.setHours(endDate.getHours() + parseInt(canchaData.duracion.split(':')[0]));
                        endDate.setMinutes(endDate.getMinutes() + parseInt(canchaData.duracion.split(':')[1]));
                        const color = turno.estado === 'DISPONIBLE' ? 'green' : turno.estado === 'OCUPADA' ? 'red' : turno.estado === 'TURNO_FIJO' ? 'yellow' : 'blue';

                        return {
                            title: turno.jugadores?.[0]?.nombreCompleto || 'Sin nombre',
                            start: startDate,
                            end: endDate.toISOString(),
                            description: `Asistencia: ${turno.asistencia} | Estado: ${turno.estado}`,
                            backgroundColor: color,
                            borderColor: color
                        };
                    });
                    successCallback(events);
                }
            } catch (error) {
                console.error('Error fetching events:', error);
                failureCallback(error);
            }
        }
    });

    calendar.render();

    btnCerrar.addEventListener('click', function() {
        modal.close();
    });

    evento
    eventForm.addEventListener('submit', async function(e) {
        e.preventDefault();

        const selectedPlayerId = playerSelect.value;
        const startDateTime = document.getElementById('event-start').value;
        const turnType = document.getElementById('turn-type').value;

        console.log('Jugador',selectedPlayerId);
        console.log('Hora de turno',startDateTime);
        console.log('Tipo de turno',turnType);
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
                loadPlayers(); // Refresca los jugadores
            } else {
                alert("Error al agregar el turno");
            }
        } catch (error) {
            console.error('Error al guardar el turno:', error);
        }
    });
});

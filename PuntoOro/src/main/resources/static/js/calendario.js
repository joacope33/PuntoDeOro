document.addEventListener('DOMContentLoaded', async function () {

    /* CONSTANTES */
    const playerModal = document.getElementById('player-modal');
    const btnAddPlayer = document.getElementById('btn-add-player');
    const modal = document.getElementById('modal');
    const eventForm = document.getElementById('event-form');
    const btnCerrar = document.getElementById('btn-close-modal');
    const addPlayerBtn = document.getElementById('add-player-btn');
    const playerSelect = document.getElementById('select-player');
    let calendar;
    let selectedDate = null;
    let canchaData = null;

    modal.close();

    /* FUNCIONES */
    // Función para cargar los jugadores al abrir el modal
    async function loadPlayers() {
        try {
            const response = await fetch('/jugador/todos');
            if (response.ok) {
                const players = await response.json();
                console.log('Jugadores cargados:', players);
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

    //funcion para cargar cancha:
    async function cargarCanchas() {    
        const canchaSelect = document.getElementById('select-cancha'); // Seleccionamos el <select>
    
        try {
            // Realizar la solicitud GET para obtener las canchas
            const response = await fetch('/canchas/todas'); // Asegúrate de que esta ruta sea correcta en tu API
            console.log('canchas', response)
            if (!response.ok) {
                throw new Error('Error al obtener las canchas');
            }
    
            const canchas = await response.json(); // Suponemos que el servidor devuelve un array de objetos JSON con las canchas
    
            // Limpiar las opciones anteriores (si las hubiera)
            canchaSelect.innerHTML = '';
    
            // Crear una opción por defecto
            const opcionDefault = document.createElement('option');
            opcionDefault.textContent = 'Selecciona una cancha';
            opcionDefault.value = ''; // Sin valor por defecto
            canchaSelect.appendChild(opcionDefault);
    
            // Agregar las canchas al <select>
            canchas.forEach(cancha => {
                const option = document.createElement('option');
                option.value = cancha.id; // ID de la cancha
                // Aquí mostramos, por ejemplo, el ID y el estado de la cancha
                option.textContent = `Cancha ${cancha.id} - ${cancha.estado}`;
                canchaSelect.appendChild(option); // Añadir la opción al <select>
            });
    
        } catch (error) {
            console.error('Error al cargar las canchas:', error);
            alert('Hubo un problema al cargar las canchas. Intenta de nuevo más tarde.');
        }
    }
    //funcion que genera los botones de la cancha
    async function cargarBotonesDeCancha() {
        const canchaButtonsContainer = document.getElementById('cancha-buttons-container'); 

        try {
            const response = await fetch('/canchas/todas');
            if (!response.ok) {
                throw new Error('Error al obtener las canchas');
            }
            const canchas = await response.json();

            // Limpiar los botones previos (si los hubiera)
            canchaButtonsContainer.innerHTML = '';

            // Crear un botón para cada cancha
            canchas.forEach(cancha => {
                const button = document.createElement('button');
                button.textContent = `Cancha ${cancha.id} - ${cancha.estado}`;
                button.addEventListener('click', () => cambiarCancha(cancha.id));
                canchaButtonsContainer.appendChild(button);
            });

        } catch (error) {
            console.error('Error al cargar las canchas:', error);
            alert('Hubo un problema al cargar las canchas. Intenta de nuevo más tarde.');
        }
    }

    /* funcion cambiar la cancha seleccionada */
    async function cambiarCancha(canchaId) {
        try {
            const response = await fetch(`/canchas/${canchaId}`);
            if (response.ok) {
                canchaData = await response.json();
                console.log('Datos de la cancha seleccionada:', canchaData);
                actualizarCalendario(canchaId);
            } else {
                console.error('Error al obtener los datos de la cancha:', response.status);
            }
        } catch (error) {
            console.error('Error en la solicitud:', error);
        }
    }
/* Actualizar el calendario según la cancha seleccionada */
    async function actualizarCalendario(canchaId) {
        calendar.destroy();  // Destruir el calendario actual

        const calendarEl = document.getElementById('calendar');
        calendar = new FullCalendar.Calendar(calendarEl, {
            locale: 'es',
            initialView: 'timeGridWeek',
            selectable: true,
            customButtons: {
                myCustomButton: {
                    text: 'Agregar Turno!',
                    click: function () {
                        if (selectedDate) {
                            modal.showModal();
                            const formattedDate = selectedDate.date.toLocaleString('es-ES', {
                                hour: '2-digit',
                                minute: '2-digit',
                                second:'2-digit',
                                hour12: false,
                                year: 'numeric',
                                month: '2-digit',
                                day: '2-digit'
                            }).replace(',', ''); // Para eliminar la coma que puede aparecer
                            document.getElementById('event-start').value = formattedDate;
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
                        hour12: false // Esto asegura que el formato de hora sea de 24 horas
                    },
                    slotMinTime: canchaData ? canchaData.horarioApertura : '07:00:00',
                    dayHeaderFormat: {
                        weekday: 'long'
                    },
                    allDaySlot: false
                }
            },
            dateClick: function (info) {
                selectedDate = info;
            },
            eventClick: function (info) {
                alert('Evento: ' + info.event.title);
            },
            events: async function (info, successCallback, failureCallback) {
                try {
                    const response = await fetch(`/calendario/${canchaId}`);
                    if (response.ok) {
                        const data = await response.json();
                        const events = data.map(turno => {
                            const startDate = turno.dia + 'T' + turno.hora;
                            const endDate = new Date(turno.dia + 'T' + turno.hora);
                            endDate.setHours(endDate.getHours() + parseInt(canchaData.duracion.split(':')[0]));
                            endDate.setMinutes(endDate.getMinutes() + parseInt(canchaData.duracion.split(':')[1]));
                            const color = turno.tipoTurno === 'TURNO' ? 'red' : turno.tipoTurno === 'TORNEO' ? 'grey' : turno.tipoTurno === 'TURNO_FIJO' ? 'yellow' : 'blue';
                            console.log('turnos tipo',turno.tipoTurno);
                            return {
                                title: turno.jugadores?.[0]?.nombreCompleto || 'Sin nombre',
                                start: startDate,
                                end: endDate.toISOString(),
                                description: `Asistencia: ${turno.asistencia} | tipo de Turno: ${turno.tipoTurno}`,
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
    }

    
    //llamada de funciones
    cargarCanchas();//cargar canchas
    loadPlayers();  // Carga los jugadores al iniciar
    cargarBotonesDeCancha();


    // Mostrar formulario para agregar jugador
    addPlayerBtn.addEventListener('click', () => {
        playerModal.showModal();
    });

    // Crear alta de usuarios
    document.getElementById('add-player-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        // Captura los valores del formulario
        const newPlayerName = document.getElementById('player-name').value;
        const playerPhone = document.getElementById('player-phone').value;
        const playerCategory = document.getElementById('player-category').value;
        const playerBirthdate = document.getElementById('player-birthday').value;
        const playerDni = document.getElementById('player-dni').value;
        const playerRating = document.getElementById('player-rating').value;
        const playerPoints = document.getElementById('player-points').value;
        const playerComment = document.getElementById('player-comment').value;
        const csrfToken = document.querySelector('input[name="_csrf"]').value;

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
                loadPlayers();  // Recarga la lista de jugadores
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

    // Cerrar modal de jugador
    const btnClosePlayerModal = document.getElementById('btn-close-player-modal');
    btnClosePlayerModal.addEventListener('click', () => {
        playerModal.close();
    });

    /* Cargar los detalles de la cancha */
    const canchaId = 1;  // Reemplaza con el ID de la cancha que necesitas
    try {
        const response = await fetch(`/canchas/${canchaId}`);
        
        if (response.ok) {
            canchaData = await response.json();
            console.log('Datos de la cancha:', canchaData);
        } else {
            console.error('Error al obtener los datos de la cancha:', response.status);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }

    /* INICIALIZACIÓN DEL CALENDARIO */
    const calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'es',
        initialView: 'timeGridWeek',
        selectable: true,
        customButtons: {
            myCustomButton: {
                text: 'Agregar Turno!',
                click: function () {
                    if (selectedDate) {
                        modal.showModal();
                        const formattedDate = selectedDate.date.toLocaleString('es-ES', {
                            hour: '2-digit',
                            minute: '2-digit',
                            second:'2-digit',
                            hour12: false,
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit'
                        }).replace(',', ''); // Para eliminar la coma que puede aparecer
                        document.getElementById('event-start').value = formattedDate;
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
                    hour12: false // Esto asegura que el formato de hora sea de 24 horas
                },
                slotMinTime: canchaData ? canchaData.horarioApertura : '07:00:00',
                dayHeaderFormat: {
                    weekday: 'long'
                },
                allDaySlot: false
            }
        },
        dateClick: function (info) {
            selectedDate = info;
        },
        eventClick: function (info) {
            alert('Evento: ' + info.event.title);
        },
        events: async function (info, successCallback, failureCallback) {
            try {
                const response = await fetch(`/calendario/${canchaId}`);
                if (response.ok) {
                    const data = await response.json();
                    const events = data.map(turno => {
                        const startDate = turno.dia + 'T' + turno.hora;
                        const endDate = new Date(turno.dia + 'T' + turno.hora);
                        endDate.setHours(endDate.getHours() + parseInt(canchaData.duracion.split(':')[0]));
                        endDate.setMinutes(endDate.getMinutes() + parseInt(canchaData.duracion.split(':')[1]));
                        const color = turno.tipoTurno === 'TURNO' ? 'red' : turno.tipoTurno === 'TORNEO' ? 'grey' : turno.tipoTurno === 'TURNO_FIJO' ? 'yellow' : 'blue';
                        console.log('turnos tipo',turno.tipoTurno);
                        return {
                            title: turno.jugadores?.[0]?.nombreCompleto || 'Sin nombre',
                            start: startDate,
                            end: endDate.toISOString(),
                            description: `Asistencia: ${turno.asistencia} | tipo de Turno: ${turno.tipoTurno}`,
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

    // Cerrar modal al hacer clic en el botón de cerrar
    btnCerrar.addEventListener('click', function () {
        modal.close();
    });

    // Evento para agregar el turno
    eventForm.addEventListener('submit', async function (e) {
        e.preventDefault();
    
        // Obtener CSRF token
        const csrfToken = document.querySelector('input[name="_csrf"]').value;
    
        // Obtener los valores del formulario
        const selectedPlayerId = document.getElementById('select-player').value;
        const startDateTime = document.getElementById('event-start').value;
        const turnType = document.getElementById('turn-type').value;
        const selectedCanchaId = document.getElementById('select-cancha').value;
        
        console.log('cancha',selectedCanchaId);
        const dia = startDateTime.split(" ")[0];
        const hora = startDateTime.split(" ")[1];


        // Crear un objeto FormData para enviar el formulario
        const formData = new FormData();
        formData.append('_csrf', csrfToken);  // Incluir el CSRF token
        formData.append('jugadores', selectedPlayerId);
        formData.append('dia', dia); // Solo la fecha
        formData.append('hora', hora); // Solo la hora
        formData.append('tipoTurno', turnType);
        formData.append('Cancha', selectedCanchaId);
        
       
        try {
            // Enviar la solicitud POST con FormData
            const response = await fetch('/turnos/reservar', {
                method: 'POST',
                body: formData
            });
    
            if (response.ok) {
                alert("Turno agregado con éxito");
                modal.close();
                eventForm.reset();
                loadPlayers();  // Refrescar jugadores

            } else {
                alert("Error al agregar el turno");
            }
        } catch (error) {   
            console.error('Error al agregar el turno:', error);
            alert('Hubo un error al agregar el turno.');
        }
    });

});

document.addEventListener('DOMContentLoaded', function() {
    function redireccionIndex() {
        window.location.href = '/index';
    }

    
 




    checkAuthStatus(); // Llamar a la función al cargar la página
    
    function actualizarCopyright() {
        const footer = document.getElementById('Copyright');
        const fechaActual = new Date();
        const opciones = { year: 'numeric' };
        footer.innerHTML = `Copyright &copy; ${fechaActual.toLocaleDateString('es-ES', opciones)} Punto de Oro`;
    }

    // Llamar a la función para que se ejecute al cargar la página
    actualizarCopyright();

   
/*Redirecciones*/ 
function redireccionLogin() {
    window.location.href = '/login';
}

function redireccionIndex() {
    window.location.href = '/index';
}





});
function changeColor() {
    if (document.body.classList.contains("blue")) {
      document.body.classList.remove("blue");
      document.body.classList.add("red");
    } else if (document.body.classList.contains("red")) {
      document.body.classList.remove("red");
      document.body.classList.add("green");
    } else if (document.body.classList.contains("green")) {
      document.body.classList.remove("green");
      document.body.classList.add("--color-crema-claro");
    } else if (document.body.classList.contains("--color-crema-claro")) {
        document.body.classList.remove("--color-crema-claro");
        document.body.classList.add("beige");
    } else {
        document.body.classList.remove("beige");
        document.body.classList.add("blue");
      }

  }
  


  function scrollAppear() {
    const images = document.querySelectorAll('.scroll');
    images.forEach((image) => {
      const imagePosition = image.getBoundingClientRect().top;
      const screenHeight = window.innerHeight;
      if (imagePosition < screenHeight) {
        image.classList.add('visible');
      }
    });
  }
  
  window.addEventListener('scroll', scrollAppear);

 
  // Redireccionamientos
  function redireccionIndex() {
    window.location.href = '/index';
  }

  function redireccionLogin() {
    window.location.href = '/login';
  }

  function redireccionSesion() {
    fetch('/login/cerrar-sesion', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            // Redirigir a la página de inicio de sesión después de cerrar sesión
            window.location.href = "/index";
        } else {
            alert("Error al cerrar sesión");
        }
    })
    .catch(error => {
        console.error('Error al cerrar sesión:', error);
    });
}

  function redireccionRegister() {
    window.location.href = '/register';
  }

  function redireccionForgetPassword() {
    window.location.href = '/forgetPassword';
  }

  



//LOGIN
// Mostrar alerta 


//Guardar en localStorage la informacion en la que se inicio sesion
async function iniciarSesion(nombreUsuario, contrasena) {
  try {
    let response = await fetch('/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ nombreUsuario, contrasena })
    });

    if (!response.ok) {
      throw new Error('Error al iniciar sesión');
    }

    let data = await response.json();
    if (data.exito) {
      localStorage.setItem('usuarioActivo', nombreUsuario);
      window.location.href = '/home';
    } else {
      console.error('Nombre de usuario o contraseña incorrectos');
    }
  } catch (error) {
    console.error('Error al iniciar sesión:', error);
  }
}






















/*
function cargarPalabras() {
  fetch('/palabras/listar')
      .then(response => response.json())
      .then(data => {
          const listaPalabras = document.getElementsByClassName("lista-palabras");
          listaPalabras.innerHTML = "";
          data.forEach(palabra => {
              const li = document.createElement("li");
              li.textContent = palabra;
              listaPalabras.appendChild(li);
          });
      })
      .catch(error => console.error('Error al cargar las palabras:', error));
}

*/




// Validación




 // Autenticación
 // Verifica si el usuario tiene sesión activa al cargar la página del juego
 /*
document.addEventListener('DOMContentLoaded', function() {
  if (!localStorage.getItem('token')) {
      // Si no hay token en localStorage, redirige al usuario al login
      window.location.href = '/login';
  } else {
    // Si hay token, redirige al usuario al inicio-exitoso
    window.location.href = '/inicio-exitoso';
}

});

document.addEventListener('DOMContentLoaded', function() {
  document.querySelector('.formulario-login').addEventListener('submit', async function(event) {
      event.preventDefault();

      const form = event.target;
      const formData = new FormData(form);

      try {
          const response = await fetch('/iniciado', {
              method: 'POST',
              body: new URLSearchParams(formData)
          });

          if (!response.ok) {
              throw new Error('Error al autenticar');
          }

          const credencialesValidas = await response.json();

          if (credencialesValidas) {
              // Si las credenciales son válidas, redirigir al inicio exitoso
              window.location.href = '/inicio-exitoso';
          } else {
              // Si las credenciales no son válidas, mostrar un mensaje de error
              document.querySelector('.alerta-login').textContent = 'Nombre de usuario o contraseña incorrectos';
          }
      } catch (error) {
          console.error('Error:', error);
          // Manejar el error, por ejemplo, mostrar un mensaje de error genérico
          document.querySelector('.alerta-login').textContent = 'Error al intentar iniciar sesión';
      }
  });
});

*/



 // Alertas
 /*
document.addEventListener('DOMContentLoaded', function() {
  // Obtener el formulario y el botón de registro
  const formulario = document.getElementsByClassName('formulario');
  const btnRegistrar = document.getElementById('btn-registrar');
  
  // Agregar el EventListener para el evento submit
  formulario.addEventListener('submit', function(event) {
      event.preventDefault(); // Evitar que el formulario se envíe automáticamente
      
      // Aquí puedes agregar tu lógica para validar los campos antes de enviar el formulario
      let nombreCompleto = formulario.nombreCompleto.value;
      let correoElectronico = formulario.correoElectronico.value;
      let nombreUsuario = formulario.nombreUsuario.value;
      let contrasena = formulario.contrasena.value;
      
      // Ejemplo de validación básica (puedes mejorarla según tus necesidades)
      if (nombreCompleto === '' || correoElectronico === '' || nombreUsuario === '' || contrasena === '') {
          alert('Por favor completa todos los campos.');
          return; // Detener el proceso si hay campos vacíos
      }
      
      // Si llega hasta aquí, significa que los campos están llenos, puedes enviar el formulario
      formulario.submit();
  });
});
*/

/*
$(document).ready(function() {
  // Obtener datos al cargar la página
  $.get("/registro/mensaje", function(data) {
      console.log("Respuesta del servidor: " + data);
      alert("Respuesta del servidor: " + data);
  });

  // Enviar datos al servidor
  $('#btnEnviar').click(function() {
      var mensaje = $('#txtMensaje').val();

      $.ajax({
          url: "/registro/mensaje",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify(mensaje),
          success: function(response) {
              console.log("Respuesta del servidor: " + response);
              alert("Respuesta del servidor: " + response);
          },
          error: function() {
              alert("Error al enviar mensaje");
          }
      });
  });
});

*/
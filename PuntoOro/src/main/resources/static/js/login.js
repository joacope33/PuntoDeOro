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

  




















document.addEventListener("DOMContentLoaded", function() {
    /*ELIMINA LOS 0 predeterminados en los tipos 'number' */
    const inputs = document.querySelectorAll("#dni, #puntos, #calificacion, #categoria");
    inputs.forEach(input => {
        if (input.value === "0") {
            input.value = "";
        }
    });


    
});

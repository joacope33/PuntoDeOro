function toggleEdit(field) {
    const spanElement = document.getElementById(field);
    const inputElement = document.getElementById(field + "Input");

    // Verifica si el input está actualmente visible
    if (inputElement.style.display === "inline") {
        // Actualiza el span con el valor ingresado en el input
        spanElement.innerText = inputElement.value;

        // Compara el valor actual con el valor original
        if (spanElement.getAttribute("data-original") === inputElement.value) {
            spanElement.style.color = "#fff";  // Color original
        } else {
            spanElement.style.color = "#8b8b8b";  // Color para indicar modificación
        }

        // Oculta el input y muestra el span
        inputElement.style.display = "none";
        spanElement.style.display = "inline";
    } else {
        // Si el atributo data-original no está configurado, guárdalo
        if (!spanElement.hasAttribute("data-original")) {
            spanElement.setAttribute("data-original", spanElement.innerText);
        }

        // Muestra el input y oculta el span para que el usuario pueda editar
        spanElement.style.display = "none";
        inputElement.style.display = "inline";

        // Enfoca el input para facilitar la edición
        inputElement.focus();
    }
}





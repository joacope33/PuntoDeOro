function toggleEdit(field) {
    // Esconde el span y muestra el input correspondiente
    const spanElement = document.getElementById(field);
    const inputElement = document.getElementById(field + "Input");
    
    // Toggle visibility
    if (spanElement.style.display === "none") {
        spanElement.style.display = "inline";
        inputElement.style.display = "none";
    } else {
        spanElement.style.display = "none";
        inputElement.style.display = "inline";
    }
}


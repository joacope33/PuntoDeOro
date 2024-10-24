package com.Unsada.Web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



public class RegisterController {

    /* 
    @Autowired
    private UsuarioService usuarioService;

    // Muestra el formulario de registro
    @GetMapping
    public String mostrarFormularioRegister(Model model) {
        model.addAttribute("usuario", new Usuario());  // Añade un objeto usuario vacío al modelo
        return "register";  // Nombre de la vista HTML que mostrará el formulario (register.html)
    }

    // Procesa el formulario de registro
    @PostMapping
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario);  // Registra el usuario usando el objeto completo
            model.addAttribute("successMessage", "Usuario registrado con éxito.");  // Mensaje de éxito
            return "redirect:/login";  // Redirecciona a la página de login después de un registro exitoso
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Error al registrar el usuario: " + e.getMessage());  // Mensaje de error
            return "register";  // Si hay un error, vuelve al formulario de registro
        }
    }
    */



    
}

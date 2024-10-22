package com.Unsada.Web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.service.UsuarioService;

@Controller
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager; // Inyectar AuthenticationManager

    @Autowired
    private UsuarioService usuarioService; // Para acceder a los métodos de servicio


    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        System.out.println("Estado de autenticación: " + isAuthenticated); // Imprimir estado de autenticación

        if (isAuthenticated) {
            return "redirect:/index"; // Redirigir a la página de inicio si ya está autenticado
        }
        return "login"; // Mostrar el formulario de login si no está autenticado
    }



    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getMail(), usuario.getContrasena())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.print("SE AUTENTICO EL USUARIO");
            return "redirect:/index";  // Redirigir a la página de inicio después de iniciar sesión
        } catch (AuthenticationException e) {
            model.addAttribute("errorMessage", "Usuario o contraseña incorrectos.");  // Mensaje de error
            System.out.print("ERROR AL AUTENTICAR EL USUARIO");
            return "login";  // Volver al formulario de login
        }
    }




    // Mostrar el formulario de forget
    @GetMapping("/forgetPassword")
    public String mostrarForgetPassword() {
        return "forgetPassword";  // Asegúrate de tener una vista llamada "login.html" o "login.jsp"
    }


    
}

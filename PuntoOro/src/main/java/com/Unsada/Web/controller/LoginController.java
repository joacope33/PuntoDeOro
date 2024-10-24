package com.Unsada.Web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    public String mostrarFormularioLogin() {
        return "/login";
    }

    /* 
    @Autowired
    private AuthenticationManager authenticationManager; // Inyectar AuthenticationManager

    @Autowired
    private UsuarioService usuarioService; // Para acceder a los métodos de servicio


    
    

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null 
            && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() 
            && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
        return "redirect:/index"; // Si ya está autenticado, redirigir al index
        }
        return "redirect:/login";
    }
    
    


 
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login"; // Redirigir a la página de login después de cerrar sesión
    }



    // Mostrar el formulario de forget
    @GetMapping("/forgetPassword")
    public String mostrarForgetPassword() {
        return "forgetPassword";  // Asegúrate de tener una vista llamada "login.html" o "login.jsp"
    }


    */
}

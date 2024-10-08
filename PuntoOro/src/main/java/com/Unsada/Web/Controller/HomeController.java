package com.Unsada.Web.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// Define la ruta base para todas las solicitudes manejadas por este controlador.
@RequestMapping("/home")
public class HomeController{
    
    
    private static final Logger logger = LogManager.getLogger(HomeController.class);
    
    /**
     * Este metodo GET maneja la solicitud de /home
     * 
     * @return  retorna la pagina index
     */
    @GetMapping("")
        public String mostrarFormularioRegistro() {
            // LOG
            logger.info("[HomeController] mostraInicioExistoso : Solicitud GET recibida en /home, redirigiendo a la página principal (index).");
            return "index"; // Esto devuelve el nombre del archivo HTML sin la extensión (.html)
    }
        
    /**
     * Este metodo GET maneja la solicitud de /home/inicio-exitoso
     * 
     * @return retorna la pagina inicio-exitoso
     */
    @GetMapping("/inicio-exitoso")
        public String mostraInicioExistoso() {
            // LOG
            logger.info("[HomeController] mostraInicioExistoso : Solicitud GET recibida en /home/inicio-exitoso, redirigiendo a la página de inicio exitoso (inicio-exitoso).");
            return "inicio-exitoso"; // Esto devuelve el nombre del archivo HTML sin la extensión (.html)
    }
}

package com.Unsada.Web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebPadel {

	@SuppressWarnings("deprecation")
    public static void main(String[] args) {
		SpringApplication.run(WebPadel.class, args);
		
		
		// Intentar abrir el navegador automáticamente
        try {
            String url = "http://localhost:9090/login";
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                System.out.println("Sistema operativo no soportado. Abre manualmente la URL: " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo abrir el navegador automáticamente.");
        }
    }


}
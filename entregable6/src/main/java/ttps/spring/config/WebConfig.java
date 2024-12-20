package ttps.spring.config;  // El paquete correcto

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Anotación para que Spring la cargue como configuración
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura CORS globalmente para todos los endpoints
        registry.addMapping("/**")  // Configura CORS para todos los endpoints
                .allowedOrigins("http://localhost:4200")  // Permite solicitudes desde localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("Content-Type", "Authorization");  // Cabeceras permitidas
    }
}

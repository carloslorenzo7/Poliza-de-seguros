package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Proyecto de polizas de seguro")
				.version("1.0")
				.description("Backend para proyecto polizas")
				.termsOfService("Link de los terminos y servicios")
				.license(new License().name("Licencia de Ejemplo").url("https://www.ejemplo.com/licencia")));
	}
}

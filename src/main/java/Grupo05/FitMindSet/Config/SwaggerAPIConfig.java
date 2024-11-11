package Grupo05.FitMindSet.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {
    @Value("${FitMindSet.openapi.dev-url}")
    private String devUrl;
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(this.devUrl);
        devServer.setDescription("FitMindSet Server");

        Contact contact = new Contact();
        contact.setEmail("ssaavedraa4@upao.edu.pe");
        contact.setUrl("https://www.fitmindset.com");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");
            Info info= new Info()
                    .title("API FitmindSet Seguimiento de habitos saludables")
                    .version("1.0")
                    .contact(contact)
                    .description("API Restful de Seguimiento de habitos saludables")
                    .termsOfService("https://www.fitmindset.com/terms")
                    .license(mitLicense);

                    return new OpenAPI()
                            .info(info)
                            .addServersItem(devServer);

    }
}

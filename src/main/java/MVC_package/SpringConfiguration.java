package MVC_package;

import entities.SessionDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import services.WordProcessor;

@Configuration
@ComponentScan("data_base")
@ComponentScan("entities")
@EnableMongoRepositories(("data_base"))
public class SpringConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login");
    }

    @Bean
    public SessionDictionary getSessionDictionary() {
        return new SessionDictionary();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WordProcessor getWordProcessor() {
        return new WordProcessor();
    }

}
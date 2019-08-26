package MVC_package;

import data_base.UserRepository;
import data_base.WordTranslateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import services.WordProcessor;

@Configuration
@ComponentScan("data_base")
public class SpringConfiguration implements WebMvcConfigurer {

    @Autowired
    UserRepository userRepository;

    /**
     * By annotating WordTranslateRepository with
     * @Repository, you declare that it should be automatically discovered by Spring component
     * scanning and instantiated as a bean in the Spring application context.
     */
    @Autowired
    WordTranslateRepository wordTranslateRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("table");
        registry.addViewController("/login");
    }

    @Bean
    public UserService getUserService() {
        return new UserService(userRepository, wordTranslateRepository, jdbcTemplate);
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

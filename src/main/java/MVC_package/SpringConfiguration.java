package MVC_package;

import data_base.UserRepository;
import data_base.WordTranslateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import services.UserService;

@Configuration
@ComponentScan("data_base")
public class SpringConfiguration implements WebMvcConfigurer {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WordTranslateRepository wordTranslateRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("table");
    }

    @Bean
    public UserService getUserService(){
        return new UserService(userRepository,wordTranslateRepository);
    }

}

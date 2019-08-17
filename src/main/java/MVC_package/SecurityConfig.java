
package MVC_package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;


    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/create","/table")
                .hasRole("ADMIN")
                //end::authorizeRequests[]

                .and()
                .formLogin()
                .loginPage("/login")
                //end::customLoginPage[]

                // tag::enableLogout[]
                .and()
                .logout()
                .logoutSuccessUrl("/")
                // end::enableLogout[]

                // Make H2-Console non-secured; for debug purposes
                // tag::csrfIgnore[]
                .and()
                .csrf()
                .disable()
        ;
    }
  /*  @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .userDetailsService(userService)
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }
}
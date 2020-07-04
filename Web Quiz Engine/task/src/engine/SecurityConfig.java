package engine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.httpBasic()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(
                    "/h2-console/**",
                    "/actuator/shutdown",
                    "/api/register"
            )
            .permitAll()
            .antMatchers("/api/quizzes/**")
            .authenticated();
        http.headers()
            .frameOptions()
            .sameOrigin();

    }

}
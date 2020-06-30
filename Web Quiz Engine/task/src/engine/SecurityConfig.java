package engine;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.httpBasic()
                    .and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/h2-console/**","/actuator/shutdown","/api/register")
                    .permitAll()
                    .antMatchers("/api/quizzes/**")
                    .authenticated();
    }
}
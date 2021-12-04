package graphium.graphiumteam8.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allows you to secure methods with @PreAuthorize
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // .csrf (adam - needs to be enabled, but you need to know what it means/does and what it can cause, you must research this)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin() // For form based authentication
                .permitAll()
                .defaultSuccessUrl("/", true)
                //.defaultSuccessUrl("/index", true) // Redirect after login
                .and()
                // Adam - configure functionality of remember me (you can store cookies in database instead with .rememberMe().tokenRepository() - research how to do it all
                .logout()
                .logoutUrl("/logout")
                // If CSRF is enabled, deleted logoutRequestMatcher and make sure its POST (including the method="" in html for the logout button (currently get)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // Logs out when on /logout with GET method (hence why it must be changed later or logout wont work)
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login") // Redirects to login after logout
        ;
    }
}



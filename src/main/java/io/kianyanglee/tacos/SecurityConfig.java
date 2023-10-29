package io.kianyanglee.tacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
// import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import io.kianyanglee.tacos.domain.User;
import io.kianyanglee.tacos.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            log.info("User is : {}", user);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .authorizeHttpRequests((authorizehttpRequests) -> {
                    authorizehttpRequests
                            .requestMatchers(toH2Console()).permitAll()
                            .requestMatchers(mvc.pattern("/design"),
                                    mvc.pattern("/orders"))
                            .hasRole("USER")
                            // .requestMatchers(AntPathRequestMatcher.antMatcher( "/api/ingredients/**")).hasRole("USER")
                            .requestMatchers(AntPathRequestMatcher.antMatcher( "/api/ingredients/**")).hasRole("user")
                            .requestMatchers(mvc.pattern("/"), mvc.pattern("/**"),
                                    mvc.pattern("/**/**"))
                        .permitAll();
                })
                .formLogin(
                        // withDefaults()
                        (formLogin) -> {
                            formLogin.loginPage("/login")
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/design");
                        })
                .headers(headers -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()).disable())
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"),
                        mvc.pattern("/api/**")));
        return http.build();
    }

}

package org.nhathuy.monogo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .securityMatcher("/travishuy/notifications/**")
                    .authorizeRequests((authorize) -> authorize
                            .requestMatchers(antMatcher(HttpMethod.DELETE, "/travishuy/notifications/**"))
                            .permitAll()
                            .requestMatchers(antMatcher(HttpMethod.PUT, "/travishuy/notifications/**"))
                            .permitAll()
                            .requestMatchers(antMatcher(HttpMethod.GET, "/travishuy/notifications/**"))
                            .permitAll()
                            .requestMatchers(antMatcher(HttpMethod.POST, "/travishuy/notifications/**"))
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    .cors(cors -> cors.disable())    // Disable CORS if not needed
                    .csrf(csrf -> csrf.disable());

        return http.build();
    }
}

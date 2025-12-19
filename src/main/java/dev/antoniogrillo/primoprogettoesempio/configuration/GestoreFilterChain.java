package dev.antoniogrillo.primoprogettoesempio.configuration;

import dev.antoniogrillo.primoprogettoesempio.entity.Ruolo;
import dev.antoniogrillo.primoprogettoesempio.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class GestoreFilterChain {

    private final JwtFilter filter;
    private final AuthenticationProvider provider;

    public GestoreFilterChain(JwtFilter filter, AuthenticationProvider provider) {
        this.filter = filter;
        this.provider = provider;
    }

    @Bean
    protected SecurityFilterChain getChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a->
                        a.requestMatchers("/utente/registra").permitAll()
                                .requestMatchers("/utente/login").permitAll()
                                .requestMatchers("/utente/aggiungiNoleggio/*").hasAnyRole(Ruolo.UTENTE.name(),Ruolo.ADMIN.name())
                                .requestMatchers("/utente/aggiungiNoleggio/**").hasAnyRole(Ruolo.ADMIN.name())
                                .requestMatchers("/automobile/aggiungi").hasRole(Ruolo.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(provider);
        return http.build();
    }
}

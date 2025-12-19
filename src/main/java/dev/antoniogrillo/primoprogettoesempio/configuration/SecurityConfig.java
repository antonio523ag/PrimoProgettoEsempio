package dev.antoniogrillo.primoprogettoesempio.configuration;

import dev.antoniogrillo.primoprogettoesempio.repository.UtenteRepo;
import dev.antoniogrillo.primoprogettoesempio.service.impljpa.UtenteServiceJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final UtenteRepo repo;

    public SecurityConfig(UtenteRepo repo) {
        this.repo = repo;
    }

    @Bean
    protected UserDetailsService getUserDetailsService(){
        return (email)->repo.findByEmail(email).orElse(null);
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager getAutManger(AuthenticationConfiguration auth){
        return auth.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider getProvider(){
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider(getUserDetailsService());
        dap.setPasswordEncoder(getPasswordEncoder());
        return dap;
    }
}

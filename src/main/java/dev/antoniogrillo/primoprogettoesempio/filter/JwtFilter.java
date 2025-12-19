package dev.antoniogrillo.primoprogettoesempio.filter;

import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.service.def.TokenGranterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenGranterService service;

    public JwtFilter(TokenGranterService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Prendo il token dall'header
        String token= request.getHeader("Authorization");
        //se il token esiste
        if(token!=null &&
                //è un token jwt
                token.startsWith("Bearer ") &&
                //e non ho ancora gestito la security in nessuna chiamata
                SecurityContextHolder.getContext().getAuthentication()==null) {
            //Tolgo il Bearer dal token
            token=token.substring(7);
            //prendo l'utente
            Utente u=service.getUtenteByToken(token);
            //se l'utente è null, o non esiste più o il token è scaduto, o hanno provato a modificarlo
            if(u==null){
                //in questi casi ritorno un errore
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                //il sendError non termina il filtro quindi chiamo il return per bloccare il tutto
                return;
            }
            //creo l'oggetto per la validazione dell'utente
            UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
            //lo aggancio alla request
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //lo carico come autenticazione avvenuta
            SecurityContextHolder.getContext().setAuthentication(upat);

        }
        //proseguo nella chimata
        filterChain.doFilter(request, response);
    }
}

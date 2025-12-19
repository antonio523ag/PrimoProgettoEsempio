package dev.antoniogrillo.primoprogettoesempio.service.impljpa;

import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.repository.UtenteRepo;
import dev.antoniogrillo.primoprogettoesempio.service.def.TokenGranterService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenGranterServiceJpa implements TokenGranterService {

    private final UtenteRepo utenteRepo;

    public TokenGranterServiceJpa(UtenteRepo utenteRepo) {
        this.utenteRepo = utenteRepo;
    }

    @Value("${custom.tag.chiave}")
    private String miaChiave;

    private SecretKey getChiave(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(miaChiave));
    }





    @Override
    public Utente getUtenteByToken(String token) {
        Utente u=null;
        try{
        u = getUtente(token);
        }catch(JwtException e){
            return null;
        }
        if(u==null) return null;
        Date dataScadenza=getClaims(token).getExpiration();
        if(dataScadenza.before(new Date())) return null;
        return u;
    }

    public Object getClaim(String token,String chiave){
    Claims c=getClaims(token);
    return c.get(chiave);
    }

    private Utente getUtente(String token){
        String email=getClaims(token).getSubject();
        return utenteRepo.findByEmail(email).orElse(null);
    }

    private Claims getClaims(String token){
        io.jsonwebtoken.JwtParser p =Jwts.parser()
                .verifyWith(getChiave())
                .build();
        Claims c=(Claims)p.parse(token).getPayload();
        return c;
    }

    @Override
    public String generateToken(Utente u) {
        long durata = 1000l * 60 * 10;
        String token= Jwts.builder()
                .claims()
                    .add("miaChive","mioValore")
                    .add("ruolo",u.getRuolo())
                    .subject(u.getEmail())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis()+durata))
                .and()
                .signWith(getChiave())
                .compact();
        return token;
    }
}

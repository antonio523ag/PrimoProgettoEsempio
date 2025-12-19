package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.entity.Utente;

public interface TokenGranterService {

    Utente getUtenteByToken(String token);
    String generateToken(Utente u);
}

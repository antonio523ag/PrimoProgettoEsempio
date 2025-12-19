package dev.antoniogrillo.primoprogettoesempio.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class UtenteNonTrovatoSupplier implements Supplier<ResponseStatusException> {
    @Override
    public ResponseStatusException get() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,"nessun utente trovato");
    }
}

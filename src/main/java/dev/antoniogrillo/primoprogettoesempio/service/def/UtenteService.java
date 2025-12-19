package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.dto.request.RegistraUtenteRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.LoginResponse;
import dev.antoniogrillo.primoprogettoesempio.dto.response.UtenteResponseDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;

import java.util.List;

public interface UtenteService {

    UtenteResponseDTO registra(RegistraUtenteRequestDTO u);
    void modifica(Utente u);
    void elimina(long id);
    LoginResponse login(String email, String password);
    void aggiungiNoleggio(long idAuto, long idUtente);
    void rimuoviNoleggio(long idAuto, long idUtente);
    List<Automobile> trovaAutomobiliNoleggiate(long idUtente);
}

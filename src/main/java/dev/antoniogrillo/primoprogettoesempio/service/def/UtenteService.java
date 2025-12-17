package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;

import java.util.List;

public interface UtenteService {

    void registra(Utente u);
    void modifica(Utente u);
    void elimina(long id);
    Utente login(String email, String password);
    void aggiungiNoleggio(long idAuto, long idUtente);
    void rimuoviNoleggio(long idAuto, long idUtente);
    List<Automobile> trovaAutomobiliNoleggiate(long idUtente);
}

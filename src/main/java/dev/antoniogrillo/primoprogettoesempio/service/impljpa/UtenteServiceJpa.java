package dev.antoniogrillo.primoprogettoesempio.service.impljpa;

import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.repository.AutomobileRepo;
import dev.antoniogrillo.primoprogettoesempio.repository.UtenteRepo;
import dev.antoniogrillo.primoprogettoesempio.service.def.UtenteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteServiceJpa implements UtenteService {

    private final UtenteRepo repo;
    private final AutomobileRepo automobileRepo;

    public UtenteServiceJpa(UtenteRepo repo,AutomobileRepo automobileRepo) {
        this.repo = repo;
        this.automobileRepo = automobileRepo;
    }

    @Override
    public void registra(Utente u) {
        repo.save(u);
    }

    @Override
    public void modifica(Utente u) {
        repo.save(u);
    }

    @Override
    public void elimina(long id) {
        repo.deleteById(id);
    }

    @Override
    public Utente login(String email, String password) {
        List<Utente> utenti=repo.findAll();
        for(Utente u:utenti){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)) return u;
        }
        return null;
    }

    @Override
    public void aggiungiNoleggio(long idAuto, long idUtente) {
        Utente u=repo.findById(idUtente).orElse(null);
        Automobile a=automobileRepo.findById(idAuto).orElse(null);
        if(u==null || a==null) return;
        if(u.getAutoNoleggiate()==null)u.setAutoNoleggiate(new ArrayList<>());
        u.getAutoNoleggiate().add(a);
        if(a.getNoleggiatori()==null)a.setNoleggiatori(new ArrayList<>());
        a.getNoleggiatori().add(u);
        repo.save(u);
    }

    @Override
    public void rimuoviNoleggio(long idAuto, long idUtente) {
        Utente u=repo.findById(idUtente).orElse(null);
        Automobile a=automobileRepo.findById(idAuto).orElse(null);
        if(u==null || a==null) return;
        if(u.getAutoNoleggiate()==null)u.setAutoNoleggiate(new ArrayList<>());
        u.getAutoNoleggiate().remove(a);
        if(a.getNoleggiatori()==null)a.setNoleggiatori(new ArrayList<>());
        a.getNoleggiatori().remove(u);
        repo.save(u);
    }

    @Override
    public List<Automobile> trovaAutomobiliNoleggiate(long idUtente) {
        Utente u=repo.findById(idUtente).orElse(null);
        if(u==null) return null;
        return u.getAutoNoleggiate();
    }
}

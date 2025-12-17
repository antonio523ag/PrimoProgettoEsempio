package dev.antoniogrillo.primoprogettoesempio.service.impljpa;

import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.repository.AutomobileRepo;
import dev.antoniogrillo.primoprogettoesempio.service.def.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutomobileIServiceJpa implements AutomobileService {

//    @Autowired  //-> Field Injection (prima crea l'instanza dell'oggetto AutomobileServiceJpa e poi ci "inserisce" il campo AutomobileRepo
//    private AutomobileRepo repo;

    private final AutomobileRepo repo;

    //Constructor Injection, in creazione viene già inserito l'oggetto di tipo AutomobileRepo
    public AutomobileIServiceJpa(AutomobileRepo repo) {
        this.repo = repo;
    }

    @Override
    public void salva(Automobile a) {
        //TODO tutti i controlli del caso
        repo.save(a);
    }

    @Override
    public void elimina(long id) {
        repo.deleteById(id);
    }

    @Override
    public Automobile trova(long id) {
        Optional<Automobile> a = repo.findById(id);
        if(a.isPresent()) return a.get();
        if(a.isEmpty()) return null;
        return null;
    }

    @Override
    public List<Automobile> visualizzaTutte() {
        return repo.findAll();
    }

    @Override
    public void modifica(Automobile a) {
        //se a ha un id settato aggiorna la row con quell'id altrimenti ne crea una nuova
        repo.save(a);
    }

    @Override
    public List<Automobile> trovaAutomobiliNoleggiate(long idUtente) {
        List<Automobile> result = new ArrayList<>();
        List<Automobile> automobili = repo.findAll();
        for(Automobile a : automobili){
            if(a.getNoleggiatori()!=null){
                for(Utente u: a.getNoleggiatori()){
                    if(u.getId()==idUtente) result.add(a);
                }
            }
        }
        return result;
    }
}

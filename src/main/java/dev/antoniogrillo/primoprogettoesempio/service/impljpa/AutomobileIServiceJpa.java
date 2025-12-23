package dev.antoniogrillo.primoprogettoesempio.service.impljpa;

import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobileResponseDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobiliPaginateDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.mapper.AutomobileMapper;
import dev.antoniogrillo.primoprogettoesempio.repository.AutomobileRepo;
import dev.antoniogrillo.primoprogettoesempio.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutomobileIServiceJpa implements AutomobileService {

//    @Autowired  //-> Field Injection (prima crea l'instanza dell'oggetto AutomobileServiceJpa e poi ci "inserisce" il campo AutomobileRepo
//    private AutomobileRepo repo;

    private final AutomobileRepo repo;
    private final AutomobileMapper mapper;

    //Constructor Injection, in creazione viene già inserito l'oggetto di tipo AutomobileRepo
    //public AutomobileIServiceJpa(AutomobileRepo repo) {
    //    this.repo = repo;
    //}

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

    @Override
    public AutomobiliPaginateDTO visualizzaTutte(int numeroElementi, int pagina){
        Sort s=Sort.by("marca").ascending()
                .and(Sort.by("modello").ascending())
                .and(Sort.by("anno").ascending())
                .and(Sort.by("colore").ascending());
        Pageable p= PageRequest.of(pagina,numeroElementi,s);
        Page<Automobile> p1=repo.findAll(p);
        List<Automobile> automobili=p1.getContent();
        int numeroPagineTotali=p1.getTotalPages();
        List<AutomobileResponseDTO> lista=mapper.toAutomobileResponseDTO(automobili);
        return new AutomobiliPaginateDTO(pagina,numeroElementi,numeroPagineTotali,lista);
    }
}

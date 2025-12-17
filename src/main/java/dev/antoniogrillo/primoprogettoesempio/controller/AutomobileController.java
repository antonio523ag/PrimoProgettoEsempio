package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.service.def.AutomobileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automobile")
public class AutomobileController {

    private final AutomobileService service;

    public AutomobileController(AutomobileService automobileService) {
        this.service = automobileService;
    }

    @PostMapping("/aggiungi")
    public void aggiungiAutomobile(@RequestBody Automobile a){
        System.out.println("ho preso l'automobile");
        service.salva(a);
    }

    @DeleteMapping("/elimina/{id}")
    public void eliminaAutomobile(@PathVariable long id){
        service.elimina(id);
    }

    @GetMapping("/trova/{id}")
    public Automobile trovaAutomobile(@PathVariable long id){
        return service.trova(id);
    }

    @PutMapping("/modifica")
    public void modificaAutomobile(@RequestBody Automobile a){
        service.modifica(a);
    }

    @GetMapping("/trovaAutomobiliNoleggiate/{idUtente}")
    public List<Automobile> trovaAutomobiliNoleggiate(@PathVariable long idUtente){
        return service.trovaAutomobiliNoleggiate(idUtente);
    }

    @GetMapping("/visualizzaTutte")
    public List<Automobile> visualizzaTutti(){
        return service.visualizzaTutte();
    }


}

package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.service.def.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registra")
    public void registraUtente(@RequestBody Utente u){
        utenteService.registra(u);
    }

    @PutMapping("/modifica")
    public void modificaUtente(@RequestBody Utente u){
        utenteService.modifica(u);
    }

    @DeleteMapping("/elimina/{id}")
    public void eliminaUtente(@PathVariable long id){
        utenteService.elimina(id);
    }

    @PostMapping("/login")
    public Utente login(@RequestParam String email,@RequestParam String password){
        return utenteService.login(email,password);
    }

    @PostMapping("/aggiungiNoleggio/{idAuto}/{idUtente}")
    public void aggiungiNoleggio(@PathVariable long idAuto,@PathVariable long idUtente){
        utenteService.aggiungiNoleggio(idAuto,idUtente);
    }

    @PostMapping("/rimuoviNoleggio/{idAuto}/{idUtente}")
    public void rimuoviNoleggio(@PathVariable long idAuto,@PathVariable long idUtente){
        utenteService.rimuoviNoleggio(idAuto,idUtente);
    }

    @GetMapping("/trovaAutomobiliNoleggiate/{idUtente}")
    public List<Automobile> trovaAutomobiliNoleggiate(@PathVariable long idUtente){
        return utenteService.trovaAutomobiliNoleggiate(idUtente);
    }
}

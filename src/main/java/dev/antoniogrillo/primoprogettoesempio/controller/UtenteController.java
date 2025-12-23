package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.dto.request.RegistraUtenteRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.LoginResponse;
import dev.antoniogrillo.primoprogettoesempio.dto.response.UtenteResponseDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.service.def.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registra")
    public ResponseEntity<UtenteResponseDTO> registraUtente(@RequestBody RegistraUtenteRequestDTO u){
        UtenteResponseDTO u1= utenteService.registra(u);
        if(u1==null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.status(HttpStatus.CREATED).body(u1);
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
    public ResponseEntity<UtenteResponseDTO> login(@RequestParam String email,@RequestParam String password){
        LoginResponse l=utenteService.login(email,password);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization",l.getToken()).body(l.getUtente());
    }

    @PostMapping("/aggiungiNoleggio/{idAuto}")
    public void aggiungiNoleggio(@PathVariable long idAuto, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente) token.getPrincipal();
        utenteService.aggiungiNoleggio(idAuto,u.getId());
    }

    @PostMapping("/aggiungiNoleggio/{idAuto}/{idUtente}")
    public void aggiungiNoleggio(@PathVariable long idAuto, @PathVariable long idUtente ){
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

    @GetMapping("/trovaAutomobiliNoleggiate")
    public List<Automobile> trovaAutomobiliNoleggiate(@AuthenticationPrincipal UserDetails token){
        Utente u=(Utente) token;
        return utenteService.trovaAutomobiliNoleggiate(u.getId());
    }
}

package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class PrimoController {

    //@RequestMapping(method = RequestMethod.GET, value="/primo")
    @GetMapping("/primo")
    public String primoMetodo(){
        return "ciao a tutti";
    }

    //@RequestMapping(method = RequestMethod.POST, value="/primo")
    @PostMapping("/primo")
    public String secondoMetodo(){
        return "metodo che non funziona";
    }

    @GetMapping("/primo/{nome}/{surname}")
    public String esempioUriParam(@PathVariable String nome, @PathVariable("surname") String cognome){
        return "ciao "+nome+" "+cognome;
    }

    @GetMapping("/esempioParametri")
    public String esempioParametri(@RequestParam("name") String nome, @RequestParam int eta){
        return "ciao "+nome+" "+eta;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Utente getUtente(){
        Utente u= new Utente(1,"Antonio","Grillo", LocalDate.of(1989,12,7),"contactme@antoniogrillo.dev","P4ssw0rd!1");
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(u);
//        return json;
        return u;
    }

    @PostMapping
    public void getUtente(@RequestBody Utente u){
        System.out.println(u);
    }
}

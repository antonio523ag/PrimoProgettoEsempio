package dev.antoniogrillo.primoprogettoesempio.service.impljpa;

import dev.antoniogrillo.primoprogettoesempio.dto.request.RegistraUtenteRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.LoginResponse;
import dev.antoniogrillo.primoprogettoesempio.dto.response.UtenteResponseDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.exception.UtenteException;
import dev.antoniogrillo.primoprogettoesempio.mapper.UtenteMapper;
import dev.antoniogrillo.primoprogettoesempio.repository.AutomobileRepo;
import dev.antoniogrillo.primoprogettoesempio.repository.UtenteRepo;
import dev.antoniogrillo.primoprogettoesempio.service.def.TokenGranterService;
import dev.antoniogrillo.primoprogettoesempio.service.def.UtenteService;
import dev.antoniogrillo.primoprogettoesempio.util.UtenteNonTrovatoSupplier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UtenteServiceJpa implements UtenteService {

    private final UtenteRepo repo;
    private final AutomobileRepo automobileRepo;
    private final UtenteMapper mapper;
    private final TokenGranterService tokenGranterService;

    public UtenteServiceJpa(UtenteRepo repo,
                            AutomobileRepo automobileRepo,
                            UtenteMapper mapper,
                            TokenGranterService tokenGranterService) {
        this.repo = repo;
        this.automobileRepo = automobileRepo;
        this.mapper = mapper;
        this.tokenGranterService = tokenGranterService;
    }

    @Override
    public UtenteResponseDTO registra(RegistraUtenteRequestDTO dto) {
        if(dto.getPasswordRipetuta()==null||
                !dto.getPasswordRipetuta().equals(dto.getPassword())) return null;
        Utente u=mapper.toUtente(dto);
        if(u.getNome()==null||u.getNome().isEmpty()) throw new UtenteException("Nome non inserito");
        if(u.getCognome()==null||u.getCognome().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cognome non inserito");
        if(u.getEmail()==null||u.getEmail().isEmpty()) return null;
        if(u.getPassword()==null||u.getPassword().isEmpty()) return null;
        u= repo.save(u);
        return mapper.toUtenteResponseDTO(u);
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
    public LoginResponse login(String email, String password) {
        Optional<Utente> opt=repo.findByEmailAndPassword(email,password);
        Supplier<ResponseStatusException> s=new UtenteNonTrovatoSupplier();
        Utente u = opt.orElseThrow(s);
        s=new Supplier<ResponseStatusException>() {
            @Override
            public ResponseStatusException get() {
                return new ResponseStatusException(HttpStatus.NOT_FOUND,"utente non trovato");
            }
        };
        u=opt.orElseThrow(s);

        s=()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"utente non trovato");
        u=opt.orElseThrow(s);
        UtenteResponseDTO dto= mapper.toUtenteResponseDTO(u);
        String token = tokenGranterService.generateToken(u);
        return new LoginResponse(token,dto);
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

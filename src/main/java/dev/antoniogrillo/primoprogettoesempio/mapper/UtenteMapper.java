package dev.antoniogrillo.primoprogettoesempio.mapper;

import dev.antoniogrillo.primoprogettoesempio.dto.request.RegistraUtenteRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.UtenteResponseDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class UtenteMapper {

    public UtenteResponseDTO toUtenteResponseDTO(Utente u){
        UtenteResponseDTO dto = new UtenteResponseDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCognome(u.getCognome());
        dto.setEmail(u.getEmail());
        dto.setAnni((int)ChronoUnit.YEARS.between(u.getDataNascita(), LocalDate.now()));
        return dto;
    }

    public Utente toUtente(RegistraUtenteRequestDTO dto){
        Utente u = new Utente();
        u.setNome(dto.getNome());
        u.setCognome(dto.getCognome());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        if(dto.getDataDiNascita()==null||dto.getDataDiNascita().isEmpty())return u;
        try{
            u.setDataNascita(LocalDate.parse(dto.getDataDiNascita()));
            return u;
        }catch (DateTimeException ignored){}
        try{
            u.setDataNascita(LocalDate.parse(dto.getDataDiNascita(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return u;
        }catch (DateTimeException ignored){}
        try{
            u.setDataNascita(LocalDate.parse(dto.getDataDiNascita(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            return u;
        }catch (DateTimeException ignored){}
        try{
            u.setDataNascita(LocalDate.parse(dto.getDataDiNascita(), DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            return u;
        }catch (DateTimeException ignored){}
        return u;
    }




}

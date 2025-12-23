package dev.antoniogrillo.primoprogettoesempio.mapper;

import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobileResponseDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutomobileMapper {

    public AutomobileResponseDTO toAutomobileResponseDTO(Automobile a){
        AutomobileResponseDTO dto = new AutomobileResponseDTO();
        dto.setId(a.getId());
        dto.setMarca(a.getMarca());
        dto.setModello(a.getModello());
        dto.setColore(a.getColore());
        dto.setPrezzo(a.getPrezzo());
        dto.setAnnoProduzione(a.getAnno());
        return dto;
    }

    public List<AutomobileResponseDTO> toAutomobileResponseDTO(List<Automobile> a){
        if(a==null||a.isEmpty())return new ArrayList<>();
        return a.stream().map(this::toAutomobileResponseDTO).toList();
    }



    }

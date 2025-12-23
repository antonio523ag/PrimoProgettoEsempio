package dev.antoniogrillo.primoprogettoesempio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomobiliPaginateDTO {
    private int numeroPagina;
    private int numeroElementi;
    private int pagineTotali;
    private List<AutomobileResponseDTO> lista;
}

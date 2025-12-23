package dev.antoniogrillo.primoprogettoesempio.dto.response;

import lombok.Data;

@Data
public class AutomobileResponseDTO {
    private String marca;
    private String modello;
    private int annoProduzione;
    private double prezzo;
    private String colore;
    private long id;
}

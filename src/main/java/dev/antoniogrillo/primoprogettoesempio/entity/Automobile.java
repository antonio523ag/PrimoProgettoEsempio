package dev.antoniogrillo.primoprogettoesempio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String marca;
    private String modello;
    private String colore;
    private int anno;
    private double prezzo;
    @ManyToMany(mappedBy = "autoNoleggiate")
    private List<Utente> noleggiatori;
    @Version
    private long versione;

}

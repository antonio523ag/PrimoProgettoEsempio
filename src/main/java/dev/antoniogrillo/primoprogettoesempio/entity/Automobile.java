package dev.antoniogrillo.primoprogettoesempio.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    public Automobile(){}

    public Automobile(long id, String marca, String modello, String colore, int anno, double prezzo) {
        this.id = id;
        this.marca = marca;
        this.modello = modello;
        this.colore = colore;
        this.anno = anno;
        this.prezzo = prezzo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Utente> getNoleggiatori() {
        return noleggiatori;
    }

    public void setNoleggiatori(List<Utente> noleggiatori) {
        this.noleggiatori = noleggiatori;
    }
}

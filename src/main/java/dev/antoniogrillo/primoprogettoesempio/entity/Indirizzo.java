package dev.antoniogrillo.primoprogettoesempio.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String indirizzo;
    private String citta;
    private String cap;
    private String nazione;

    @OneToMany(mappedBy = "indirizzo")
    private List<Utente> abitanti;

    public Indirizzo(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public List<Utente> getAbitanti() {
        return abitanti;
    }

    public void setAbitanti(List<Utente> abitanti) {
        this.abitanti = abitanti;
    }
}

package dev.antoniogrillo.primoprogettoesempio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Entity
@Table(name = "elenco_utenti_db",uniqueConstraints ={
        @UniqueConstraint(name = "persona_univoca",columnNames = {"nome","cognome"}),
        @UniqueConstraint(name = "email_univoca",columnNames = "email")})
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(name="data_di_nascita")
    private LocalDate dataNascita;
    @Column(nullable = false)
    private String email;
    private String password;
    private String urlFotoProfilo;
    @ManyToMany
    @JoinTable(name="noleggi",joinColumns=@JoinColumn(name="noleggiatore_fk"),inverseJoinColumns=@JoinColumn(name="automobile_noleggiata_fk"))
    private List<Automobile> autoNoleggiate;


    public Utente(){}

    public Utente(long id, String nome, String cognome, LocalDate dataNascita, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Automobile> getAutoNoleggiate() {
        return autoNoleggiate;
    }

    public void setAutoNoleggiate(List<Automobile> autoNoleggiate) {
        this.autoNoleggiate = autoNoleggiate;
    }

    @JsonIgnore
    @Transient //non mappato sul db
    public int getEta(){
        return (int)ChronoUnit.YEARS.between(dataNascita,LocalDate.now());
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}

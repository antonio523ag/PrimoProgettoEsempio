package dev.antoniogrillo.primoprogettoesempio.dto.request;

public class RegistraUtenteRequestDTO {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String passwordRipetuta;
    private String dataDiNascita;

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

    public String getPasswordRipetuta() {
        return passwordRipetuta;
    }

    public void setPasswordRipetuta(String passwordRipetuta) {
        this.passwordRipetuta = passwordRipetuta;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
}

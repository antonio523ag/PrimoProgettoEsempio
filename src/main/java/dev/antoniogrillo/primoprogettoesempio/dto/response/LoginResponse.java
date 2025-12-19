package dev.antoniogrillo.primoprogettoesempio.dto.response;

public class LoginResponse {
    private String token;
    private UtenteResponseDTO utente;

    public String getToken() {
        return token;
    }

    public UtenteResponseDTO getUtente() {
        return utente;
    }

    public LoginResponse(String token, UtenteResponseDTO utente) {
        this.token = token;
        this.utente = utente;
    }
}

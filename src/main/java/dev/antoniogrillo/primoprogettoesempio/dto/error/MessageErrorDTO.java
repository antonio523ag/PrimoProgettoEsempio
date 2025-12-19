package dev.antoniogrillo.primoprogettoesempio.dto.error;

import java.time.LocalDateTime;

public class MessageErrorDTO {
    private String message;
    private String path;
    private LocalDateTime dataOraChiamata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getDataOraChiamata() {
        return dataOraChiamata;
    }

    public void setDataOraChiamata(LocalDateTime dataOraChiamata) {
        this.dataOraChiamata = dataOraChiamata;
    }
}

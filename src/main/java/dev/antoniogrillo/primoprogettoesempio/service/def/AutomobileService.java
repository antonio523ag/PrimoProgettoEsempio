package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobiliPaginateDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;

import java.util.List;

public interface AutomobileService {

    public void salva(Automobile a);
    public void elimina(long id);
    public Automobile trova(long id);
    AutomobiliPaginateDTO visualizzaTutte(int numeroElementi, int pagina);
    void modifica(Automobile a);
    List<Automobile> trovaAutomobiliNoleggiate(long idUtente);
}

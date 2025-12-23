package dev.antoniogrillo.primoprogettoesempio.service.implinmemory;

import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobiliPaginateDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import dev.antoniogrillo.primoprogettoesempio.service.def.AutomobileService;

import java.util.ArrayList;
import java.util.List;

public class AutomobileServiceInMemory implements AutomobileService {

    private List<Automobile> automobili = new ArrayList<>();
    private static long idGen=1;

    @Override
    public void salva(Automobile a) {
        a.setId(idGen++);
        automobili.add(a);
    }

    @Override
    public void elimina(long id) {
        Automobile a = trova(id);
        if(a==null) return;
        automobili.remove(a);
    }

    @Override
    public Automobile trova(long id) {
        for(Automobile a : automobili){
            if(a.getId()==id) return a;
        }
        return null;
    }

    @Override
    public AutomobiliPaginateDTO visualizzaTutte(int numeroElementi, int pagina) {
        return null;
    }


    @Override
    public void modifica(Automobile a) {
        int index = automobili.indexOf(trova(a.getId()));
        automobili.set(index, a);
    }

    @Override
    public List<Automobile> trovaAutomobiliNoleggiate(long idUtente) {
        List<Automobile> result = new ArrayList<>();
        for(Automobile a : automobili){
            if(a.getNoleggiatori()==null) continue;
            for(Utente u : a.getNoleggiatori()){
                if(u.getId()==idUtente) result.add(a);
            }
        }
        return result;
    }
}

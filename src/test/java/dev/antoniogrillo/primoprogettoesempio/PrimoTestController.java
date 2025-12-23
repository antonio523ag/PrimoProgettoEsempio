package dev.antoniogrillo.primoprogettoesempio;

import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobiliPaginateDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.LoginResponse;
import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import dev.antoniogrillo.primoprogettoesempio.service.def.UtenteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PrimoTestController {

    @Autowired
    private UtenteService utenteService;

    @Test
    public void primoTest(){
        LoginResponse l=utenteService.login("m.verdisdzfs@gmail.com","123456");
        assertEquals(l.getUtente().getNome(),"Giovanni");
    }
}

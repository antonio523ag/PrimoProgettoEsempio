package dev.antoniogrillo.primoprogettoesempio;

import dev.antoniogrillo.primoprogettoesempio.dto.request.RegistraUtenteRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
@SpringBootTest
public class TestRegistrazione implements GeneralTest {

    @Autowired
    private MockMvc mock;
    private ObjectMapper mapper;

    @Override
    public ObjectMapper getMapper() {
        return mapper;
    }

    @Test
    public void testRegistrazioneOK() throws Exception {
        RegistraUtenteRequestDTO r=new RegistraUtenteRequestDTO();
        r.setNome("Antonio");
        r.setCognome("Grillo");
        r.setEmail("contactme@antoniogrillo.dev");
        r.setPassword("P4ssw0rd!1");
        r.setPasswordRipetuta("P4ssw0rd!1");
        r.setDataDiNascita("1989-12-07");

        mock.perform(createGet(r,"/utente/registra"))
                .andExpect(checkStatus(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(checkContentValue("id","4"))
                .andReturn();
    }
}

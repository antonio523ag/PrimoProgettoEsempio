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
@SpringBootTest
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
public class RegistrazioneUtenteTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegistrazioneOK() throws Exception {
        RegistraUtenteRequestDTO r=new RegistraUtenteRequestDTO();
        r.setNome("Antonio");
        r.setCognome("Grillo");
        r.setEmail("contactme@antoniogrillo.dev");
        r.setPassword("P4ssw0rd!1");
        r.setPasswordRipetuta("P4ssw0rd!1");
        r.setDataDiNascita("1989-12-07");
        String json=objectMapper.writeValueAsString(r);
        mockMvc.perform(MockMvcRequestBuilders.post("/utente/registra")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andReturn();
    }
}

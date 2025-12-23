package dev.antoniogrillo.primoprogettoesempio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
public class TestTrovaAutomobiliNoleggiate {

    @Autowired
    MockMvc mock;

    @Test
    public void testTrovaAutomobiliNoleggiate() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/utente/trovaAutomobiliNoleggiate")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .header("Authorization","Bearer "+getToken())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @WithUserDetails("m.rossi@gmail.com")
    public void testTrovaAutomobiliNoleggiate2() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/utente/trovaAutomobiliNoleggiate")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    private String getToken() throws Exception {
        String path="/utente/login?email=m.rossi@gmail.com&password=123456";
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultMatcher controlloRisultato1=MockMvcResultMatchers.status().is2xxSuccessful();

        return mock.perform(builder)
                .andExpect(controlloRisultato1)
                .andReturn().getResponse().getHeader("Authorization");
    }


}

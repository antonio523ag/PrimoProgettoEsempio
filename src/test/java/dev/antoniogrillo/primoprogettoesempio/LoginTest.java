package dev.antoniogrillo.primoprogettoesempio;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    public void testTuttoOK() throws Exception {
        String path="/utente/login?email=m.rossi@gmail.com&password=123456";
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultMatcher controlloRisultato1=MockMvcResultMatchers.status().is2xxSuccessful();
        ResultMatcher controlloRisultato2=MockMvcResultMatchers.jsonPath("$.nome").value("Mario");
        ResultMatcher controlloRisultato3=MockMvcResultMatchers.jsonPath("$.nome").exists();

        mockMvc.perform(builder)
                .andExpect(controlloRisultato1)
                .andExpect(controlloRisultato2)
                .andExpect(controlloRisultato3)
                .andReturn();

    }

    @Order(2)
    @Test
    public void testUtenteNonTrovato() throws Exception {
        String path="/utente/login?email=s.castellucci@gmail.com&password=123456";
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultMatcher controlloRisultato1=MockMvcResultMatchers.status().isNotFound();

        mockMvc.perform(builder)
                .andExpect(controlloRisultato1)
                .andReturn();

    }


    @Order(1)
    @Test
    public void testTuttiIParametri() throws Exception {
        for(TestMode tm: TestMode.arrayValori()){
            testUtenteGenerico(tm.email,tm.password,tm.codiceRisposta);
        }
    }

    private void testUtenteGenerico(String email,String password, int codice) throws Exception {

        String path="/utente/login?email="+email+"&password="+password;
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultMatcher controlloRisultato1=MockMvcResultMatchers.status().is(codice);

        mockMvc.perform(builder)
                .andExpect(controlloRisultato1)
                .andReturn();

    }

    private static class TestMode{
        private String email;
        private String password;
        private int codiceRisposta;

        public static TestMode crea(String email, String password, int codiceRisposta){
            TestMode tm=new TestMode();
            tm.email=email;
            tm.password=password;
            tm.codiceRisposta=codiceRisposta;
            return tm;
        }

        public static TestMode[] arrayValori(){
            return new TestMode[]{
                    crea("m.rossi@gmail.com","123456",200),
                    crea("s.castellucci@gmail.com","123456",404)};
        }
    }
}

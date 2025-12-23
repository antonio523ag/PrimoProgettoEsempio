package dev.antoniogrillo.primoprogettoesempio;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

public interface GeneralTest {

    ObjectMapper getMapper();

    default <P> MockHttpServletRequestBuilder createGet(P p,String path){
        String Json= getMapper().writeValueAsString(p);
        return MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(Json).
                contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    default <P> MockHttpServletRequestBuilder createPost(P p,String path){
        String Json= getMapper().writeValueAsString(p);
        return MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(Json).
                contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    default ResultMatcher checkStatus(int status){
        return MockMvcResultMatchers.status().is(status);
    }

    default ResultMatcher checkContentValue(String nome,String parametro){
        return MockMvcResultMatchers.jsonPath("$."+nome).value(parametro);
    }
}

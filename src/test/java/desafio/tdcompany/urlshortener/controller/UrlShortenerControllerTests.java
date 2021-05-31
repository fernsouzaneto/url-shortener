package desafio.tdcompany.urlshortener.controller;

import desafio.tdcompany.urlshortener.dao.UrlDAO;
import desafio.tdcompany.urlshortener.exception.NotFoundURLException;
import desafio.tdcompany.urlshortener.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UrlShortenerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UrlShortenerService service;

    @Test
    public void get_generateShortURL_andReturnsAShortedURL() throws Exception {
        String shortedURL = "localhost:8080/r/nnrYGz";
        UrlDAO urlToBeReturned = UrlDAO.builder()
                .generatedId("nnrYGz")
                .longUrl("https://stackoverflow.com")
                .shortedUrl(shortedURL)
                .QtdAcessos(0)
                .id(1)
                .build();

        Mockito.when(service.generateShortURL(anyString()))
                .thenReturn(urlToBeReturned);

        mockMvc.perform(MockMvcRequestBuilders.get("/generateShortURL?longUrl=" + urlToBeReturned.getLongUrl())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortedUrl", is(shortedURL)))
                .andExpect(jsonPath("$.longUrl", is(urlToBeReturned.getLongUrl())));
    }

    @Test
    public void when_AccessShortedURL_then_RedirectToOriginalURL() throws Exception {
        String shortedURL = "localhost:8080/r/nnrYGz";
        UrlDAO urlToBeReturned = UrlDAO.builder()
                .generatedId("nnrYGz")
                .longUrl("https://stackoverflow.com")
                .shortedUrl(shortedURL)
                .QtdAcessos(0)
                .id(1)
                .build();

        Mockito.when(service.getShortedURLByGeneratedID(anyString()))
                .thenReturn(urlToBeReturned);

        Mockito.doNothing().when(service).updateQtdAcessosURL(anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/r/" + urlToBeReturned.getGeneratedId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void when_AccessAnNotExistingShortedURL_then_ReturnsNotFound() throws Exception {
        String shortedURL = "localhost:8080/r/nnrYGz";
        UrlDAO urlToBeReturned = UrlDAO.builder()
                .generatedId("nnrYGz")
                .longUrl("https://stackoverflow.com")
                .shortedUrl(shortedURL)
                .QtdAcessos(0)
                .id(1)
                .build();

        Mockito.when(service.getShortedURLByGeneratedID(anyString()))
                .thenThrow(NotFoundURLException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/r/" + urlToBeReturned.getGeneratedId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void get_statsURLs_then_ReturnsAllUrlsInfo() throws Exception {

        Mockito.when(service.getAllURLs())
                .thenReturn(Collections.singletonList(new UrlDAO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/statsURLs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

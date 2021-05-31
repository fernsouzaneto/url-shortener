package desafio.tdcompany.urlshortener.controller;

import desafio.tdcompany.urlshortener.dao.UrlDAO;
import desafio.tdcompany.urlshortener.exception.NotFoundURLException;
import desafio.tdcompany.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerService service;

    @GetMapping("/r/{generetedID}")
    public ModelAndView redirectToOriginalUrl(@PathVariable String generetedID) throws URISyntaxException, NotFoundURLException {
        UrlDAO shortedUrlDAO = service.getShortedURLByGeneratedID(generetedID);
        service.updateQtdAcessosURL(shortedUrlDAO.getGeneratedId());
        return new ModelAndView("redirect:" + shortedUrlDAO.getLongUrl());
    }

    @GetMapping("/generateShortURL")
    public UrlDAO shortUrl(@RequestParam String longUrl) {
        return service.generateShortURL(longUrl);
    }

    @GetMapping("/statsURLs")
    public List<UrlDAO> getStatsURLs() {
        return service.getAllURLs();
    }
}

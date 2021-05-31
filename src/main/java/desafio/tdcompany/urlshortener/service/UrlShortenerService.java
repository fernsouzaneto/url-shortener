package desafio.tdcompany.urlshortener.service;

import desafio.tdcompany.urlshortener.dao.UrlDAO;
import desafio.tdcompany.urlshortener.exception.NotFoundURLException;
import desafio.tdcompany.urlshortener.repository.IURLShortenerRepository;
import desafio.tdcompany.urlshortener.util.GeneratorID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UrlShortenerService {

    @Autowired
    private IURLShortenerRepository repository;

    private String URL_HOST = "localhost:8080/r/";

    public UrlDAO generateShortURL(String urlInput) {
        String generatedID = GeneratorID.generate();
        UrlDAO urlDAO = UrlDAO.builder()
                .generatedId(generatedID)
                .shortedUrl(URL_HOST.concat(generatedID))
                .longUrl(urlInput)
                .build();

        insertURL(urlDAO);

        return urlDAO;
    }

    public void insertURL(UrlDAO urlDAO) {
        repository.save(urlDAO);
    }

    public List<UrlDAO> getAllURLs() {
        return repository.findAll();
    }

    @Transactional
    public void updateQtdAcessosURL(String generatedId) {
        repository.updateQtdAcessos(generatedId);
    }

    public UrlDAO getShortedURLByGeneratedID(String generatedID) throws NotFoundURLException {

        UrlDAO urlDAO = repository.getShortedURLByGeneratedID(generatedID);
        if (urlDAO != null) {
            return urlDAO;
        }
        throw new NotFoundURLException("Shorted URL not found.");
    }

}
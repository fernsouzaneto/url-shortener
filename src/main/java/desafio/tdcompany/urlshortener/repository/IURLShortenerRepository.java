package desafio.tdcompany.urlshortener.repository;

import desafio.tdcompany.urlshortener.dao.UrlDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IURLShortenerRepository extends JpaRepository<UrlDAO, Long> {

    @Query(value = "SELECT * from TB_URL where generatedID = :generatedID", nativeQuery = true)
    UrlDAO getShortedURLByGeneratedID(@Param("generatedID") String generatedID);

    @Modifying
    @Query(value = "UPDATE TB_URL SET QTD_ACESSOS = QTD_ACESSOS+1 WHERE GENERATEDID = :generatedId", nativeQuery = true)
    void updateQtdAcessos(@Param("generatedId") String generatedId);
}

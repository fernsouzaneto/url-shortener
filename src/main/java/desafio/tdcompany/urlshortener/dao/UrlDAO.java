package desafio.tdcompany.urlshortener.dao;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_URL")
public class UrlDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "GeneratedID", length = 6, nullable = false)
    private String generatedId;

    @Column(name = "LongURL", length = 200, nullable = false)
    private String longUrl;

    @Column(name = "ShortedUrl", length = 200, nullable = false)
    private String shortedUrl;

    private int QtdAcessos;
}

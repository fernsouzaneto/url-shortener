package desafio.tdcompany.urlshortener.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
public class NotFoundURLException extends RuntimeException{
    private String errorMessage;
}

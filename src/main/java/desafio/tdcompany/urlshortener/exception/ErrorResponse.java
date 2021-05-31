package desafio.tdcompany.urlshortener.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    String message;
    int code;
}

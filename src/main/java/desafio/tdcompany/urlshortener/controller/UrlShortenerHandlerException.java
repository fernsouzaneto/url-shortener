package desafio.tdcompany.urlshortener.controller;

import desafio.tdcompany.urlshortener.exception.ErrorResponse;
import desafio.tdcompany.urlshortener.exception.NotFoundURLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class UrlShortenerHandlerException {

    @ExceptionHandler(NotFoundURLException.class)
    public ResponseEntity<ErrorResponse> notFoundURLExceptionHandler(HttpServletRequest request, NotFoundURLException ex){
        log.error("Request: " + request.getRequestURL() + " raised " + ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode( HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getErrorMessage());
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }
}

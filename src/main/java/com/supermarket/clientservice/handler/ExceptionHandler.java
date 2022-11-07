package com.supermarket.clientservice.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class ExceptionHandler {
    public Exception manageException(Exception exception) {
        if (exception instanceof HttpClientErrorException || exception instanceof HttpServerErrorException) {
            System.out.println("Hola soy una excepcion" + exception.getCause());
        }
        return exception;
    }

}

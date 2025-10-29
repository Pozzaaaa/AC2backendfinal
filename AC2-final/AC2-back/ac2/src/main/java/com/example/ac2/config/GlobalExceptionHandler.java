package com.example.ac2.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ac2.dtos.ApiErrorDTO;
import com.example.ac2.dtos.RegraNegocioException;

/**
 * Classe responsável por tratar exceções lançadas pelos controladores REST.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo RegraNegocioException e retorna uma resposta padronizada.
     *
     * @param exception exceção capturada
     * @return objeto ApiErrorDTO contendo a mensagem de erro
     */
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDTO handleBusinessRuleException(RegraNegocioException exception) {
        return new ApiErrorDTO(exception.getMessage());
    }
}

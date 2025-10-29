package com.example.ac2.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ac2.dtos.ApiErrorDTO;
import com.example.ac2.dtos.RegraNegocioException;

import org.springframework.http.HttpStatus;

// Classe de configuração para tratamento global de exceções na aplicação
@RestControllerAdvice // Indica que esta classe fornece conselhos para controladores REST
@ResponseStatus(HttpStatus.BAD_REQUEST) // Define o status HTTP padrão para respostas de erro
public class ApplicationControllerAdvice {
    @ExceptionHandler(RegraNegocioException.class) // Intercepta exceções do tipo RegraNegocioException
    public ApiErrorDTO handleRegraNegocioException(RegraNegocioException ex) {
        String msg = ex.getMessage(); // Obtém a mensagem de erro da exceção
        return new ApiErrorDTO(msg); // Retorna um DTO de erro com a mensagem
    }
}

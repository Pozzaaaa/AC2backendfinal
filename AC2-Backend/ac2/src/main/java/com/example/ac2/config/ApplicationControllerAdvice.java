package com.example.ac2.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ac2.dtos.ApiErrorDTO;
import com.example.ac2.dtos.RegraNegocioException;

// Classe que gerencia o tratamento global de exceções para todos os controladores REST
@RestControllerAdvice // Marca a classe como um "advice" para controladores REST
@ResponseStatus(HttpStatus.BAD_REQUEST) // Define o código HTTP padrão para respostas de erro como 400
public class ApplicationControllerAdvice {

    // Método que captura exceções do tipo RegraNegocioException lançadas nos controladores
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrorDTO handleRegraNegocioException(RegraNegocioException ex) {
        // Recupera a mensagem da exceção
        String msg = ex.getMessage(); 
        // Retorna um DTO contendo a mensagem de erro para o cliente
        return new ApiErrorDTO(msg); 
    }
}

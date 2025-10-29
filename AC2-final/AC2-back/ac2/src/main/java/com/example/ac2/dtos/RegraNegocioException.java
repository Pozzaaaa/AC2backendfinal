package com.example.ac2.dtos;

// Exceção personalizada para regras de negócio
public class RegraNegocioException extends RuntimeException { 
    // Construtor que aceita uma mensagem de erro
    public RegraNegocioException(String mensagem) { 
        super(mensagem); 
    }
    
}

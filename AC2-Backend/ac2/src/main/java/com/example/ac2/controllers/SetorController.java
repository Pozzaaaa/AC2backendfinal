package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.DadosSetorDTO;
import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.services.SetorService;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/setor") // Define a URL base para todos os endpoints deste controlador
public class SetorController {

    private final SetorService setorService; // Serviço responsável pela lógica de negócio de Setor

    // Construtor para injeção de dependência do serviço de Setor
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    // Endpoint para adicionar um novo setor
    // Recebe os dados via corpo da requisição (JSON) no formato SetorDTO
    @PostMapping
    public void adicionar(@RequestBody SetorDTO setorDTO) {
        setorService.adicionar(setorDTO);
    }

    // Endpoint para buscar um setor pelo seu ID
    // Retorna os dados do setor no formato DadosSetorDTO
    @GetMapping("/{id}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer id) {
        return setorService.buscarSetorPorId(id);
    }
}

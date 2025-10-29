package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.DadosSetorDTO;
import com.example.ac2.services.SetorService;


  //Controlador responsável por gerenciar operações relacionadas a setores.

@RestController
@RequestMapping("/setor")
public class SetorController {

    private final SetorService service;

    public SetorController(SetorService service) {
        this.service = service;
    }

    /**
     * Adiciona um novo setor ao sistema.
     *
     * @param dto dados do setor a ser cadastrado
     */
    @PostMapping
    public void criarSetor(@RequestBody SetorDTO dto) {
        service.adicionar(dto);
    }

    /**
     * Busca um setor pelo seu identificador.
     *
     * @param id identificador do setor
     * @return dados detalhados do setor
     */
    @GetMapping("/{id}")
    public DadosSetorDTO obterSetorPorId(@PathVariable Integer id) {
        return service.buscarSetorPorId(id);
    }
}

package com.example.ac2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.services.FuncionarioService;

/**
 * Controlador responsável por gerenciar as operações relacionadas a funcionários.
 */
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    /**
     * Cadastra um novo funcionário no sistema.
     *
     * @param dto dados do funcionário a serem cadastrados
     */
    @PostMapping
    public void criarFuncionario(@RequestBody FuncionarioDTO dto) {
        service.adicionar(dto);
    }

    /**
     * Retorna a lista de projetos vinculados a um funcionário específico.
     *
     * @param id identificador do funcionário
     * @return lista de projetos
     */
    @GetMapping("/{id}/projetos")
    public List<DadosProjetoDTO> listarProjetosPorFuncionario(@PathVariable Integer id) {
        return service.buscarProjetos(id);
    }
}

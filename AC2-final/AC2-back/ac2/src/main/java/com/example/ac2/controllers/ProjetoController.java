package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.services.ProjetoService;

/**
 * Controlador para gerenciar operações relacionadas a projetos.
 */
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    /**
     * Adiciona um novo projeto ao sistema.
     *
     * @param dto dados do projeto a ser adicionado
     */
    @PostMapping
    public void criarProjeto(@RequestBody ProjetoDTO dto) {
        service.adicionar(dto);
    }

    /**
     * Busca um projeto pelo seu identificador.
     *
     * @param id identificador do projeto
     * @return dados do projeto
     */
    @GetMapping("/{id}")
    public DadosProjetoDTO obterProjetoPorId(@PathVariable Integer id) {
        return service.buscarProjetoPorId(id);
    }

    /**
     * Vincula um funcionário a um projeto específico.
     *
     * @param idProjeto identificador do projeto
     * @param idFuncionario identificador do funcionário
     */
    @PostMapping("/{idProjeto}/vincular/{idFuncionario}")
    public void associarFuncionario(@PathVariable Integer idProjeto,
                                    @PathVariable Integer idFuncionario) {
        service.vincularFuncionario(idProjeto, idFuncionario);
    }
}
